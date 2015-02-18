package com.newbee.tmf.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.drools.util.StringUtils;

import com.newbee.tmf.config.Config;
import com.tbb.sys.domain.SysUser;
import com.tbb.tools.JsonUtil;
import com.tbb.tools.RemoteProcUtil;
import com.tbb.tools.UpfileForm;

/**
 * <p>
 * An abstract <strong>Action</strong> that dispatches to a public method that
 * is named by the request parameter whose name is specified by the
 * <code>parameter</code> property of the corresponding ActionMapping. This
 * Action is useful for developers who prefer to combine many similar actions
 * into a single Action class, in order to simplify their application design.
 * </p>
 * <p>
 * To configure the use of this action in your <code>struts-config.xml</code>
 * file, create an entry like this:
 * </p>
 * <code>
 *   &lt;action path="/saveSubscription"
 *           type="org.apache.struts.actions.DispatchAction"
 *           name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *      parameter="method"/&gt;
 * </code>
 * <p>
 * which will use the value of the request parameter named "method" to pick the
 * appropriate "execute" method, which must have the same signature (other than
 * method name) of the standard Action.execute method. For example, you might
 * have the following three methods in the same action:
 * </p>
 * <ul>
 * <li>public ActionForward delete(ActionMapping mapping, ActionForm form,
 * HttpServletRequest request, HttpServletResponse response) throws Exception</li>
 * <li>public ActionForward insert(ActionMapping mapping, ActionForm form,
 * HttpServletRequest request, HttpServletResponse response) throws Exception</li>
 * <li>public ActionForward update(ActionMapping mapping, ActionForm form,
 * HttpServletRequest request, HttpServletResponse response) throws Exception</li>
 * </ul>
 * <p>
 * and call one of the methods with a URL like this:
 * </p>
 * <code>
 *   http://localhost:8080/myapp/saveSubscription.do?method=update
 * </code>
 * <p>
 * <strong>NOTE</strong> - All of the other mapping characteristics of this
 * action must be shared by the various handlers. This places some constraints
 * over what types of handlers may reasonably be packaged into the same
 * <code>DispatchAction</code> subclass.
 * </p>
 * 
 * @author Niall Pemberton <niall.pemberton@btInternet.com>
 * @author Craig R. McClanahan
 * @author Ted Husted
 */

public class BaseDispatchAction extends BaseAction
{

	// ----------------------------------------------------- Instance Variables

	/**
     * The Class instance of this <code>DispatchAction</code> class.
     */
	protected Class clazz = this.getClass();

	/**
     * Commons Logging instance.
     */
	protected static Log log = LogFactory.getLog(BaseDispatchAction.class);

	/**
     * The message resources for this package.
     */
	protected static MessageResources messages = MessageResources
			.getMessageResources("org.apache.struts.actions.LocalStrings");

	/**
     * The set of Method objects we have introspected for this class, keyed by
     * method name. This collection is populated as different methods are
     * called, so that introspection needs to occur only once per method name.
     */
	protected HashMap methods = new HashMap();

	/**
     * The set of argument type classes for the reflected method call. These are
     * the same for all calls, so calculate them only once.
     */
	protected Class types[] = {ActionMapping.class, ActionForm.class,
			HttpServletRequest.class, HttpServletResponse.class,
			ActionContext.class};

	// --------------------------------------------------------- Public Methods

	/**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     * 
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @exception Exception if an exception occurs
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		
        UpfileForm upfileForm = (UpfileForm) form;
        if(upfileForm != null){
        	FormFile file = upfileForm.getFile();
        	file.destroy();
        }

		// 写入应用程序根目录供layout中的vm使用
		ServletContext application = request.getSession(true)
				.getServletContext();
		if (application.getAttribute("CONTEXT") == null)
		{
			String context = request.getRequestURI().substring(0,
					request.getRequestURI().indexOf(request.getServletPath()));
			application.setAttribute("CONTEXT", context);
			Config.CONTEXT = context;
			System.out
					.println("===============================================系统虚拟路径:"
							+ context + "=====================================");

			application.setAttribute("gisServer", Config.getInstance()
					.getGisServer());
		}

		// 针对不同的系统类型进行不同的权限处理，以及未登录用户可访问的系统处理。
		if (this.getClass().getName().equals("com.tbb.member.action.MemberAction")){
			// 检查会员登录
			if(!checkMemberLogin(request)){
				request.setAttribute("msg", "请登录后再使用系统!");
				return mapping.findForward("kickmember");
			}
		} else if (this.getClass().getName().equals("com.tbb.action.PAction")){
			// 检查手机端登录
			if(!checkLoginP(request)) {
				Map result = new HashMap();
				result.put("fail", "not login");
				String resultString = JsonUtil.object2json(result);
				response.getWriter().write(resultString);
				return null;
			}		
		} else if ( this.getClass().getName().indexOf("RegisteAction") < 0
				 && this.getClass().getName().indexOf("WeixinAction") < 0
				 && this.getClass().getName().indexOf("CustomerLoginAction") < 0
				 && this.getClass().getName().indexOf("RegisterAction") < 0
				 && (!this.getClass().getName().equals("com.tbb.action.PrepareAction"))
				)
		{
			// 检查登录
			checkLogin(request);
		} 

		// Identify the request parameter ontaining the method name
		String parameter = mapping.getParameter();
		if (parameter == null)
		{
			parameter = getDefaultParameterName();
		}

		// Identify the method name to be dispatched to. d:default
		String name = request.getParameter(parameter);
		if(StringUtils.isEmpty(name)){
			name = "d";
		}

		// 如果没有指定的方法名，缺省调用execute方法
		/** @todo 是否将没有方法名作为异常抛出，强制使用？ */
		if(this.getClass().getName().indexOf("WeixinAction") >= 0)
			name = "weixin";
		
		if (name == null || name.trim().equals(""))
		{
			BaseException ex = new BaseException("方法名参数 " + parameter
					+ "的值为空  !");
			log.warn("未提供正确的方法名", ex);
			ex.printStackTrace();
			throw ex;
		}

		ActionContext context = loadContext(mapping, form, request, response);

		// 检查操作权限
		if(this.getClass().getName().equals("com.tbb.action.PAction")){
			String permit_id = this.getClass().getName() + "@" + name;
			
			if(!checkPermitP(request, mapping, permit_id)) {
				Map result = new HashMap();
				result.put("fail", "permission denied");
				String resultString = JsonUtil.object2json(result);
				response.getWriter().write(resultString);
				return null;
			}
		} else if(this.getClass().getName().equals("com.tbb.member.action.MemberAction")) {
			//todo
		} else if ( this.getClass().getName().indexOf("RegisteAction") < 0
				&& this.getClass().getName().indexOf("WeixinAction") < 0
				&& this.getClass().getName().indexOf("CustomerLoginAction") < 0
				&& this.getClass().getName().indexOf("RegisterAction") < 0
				&& (!this.getClass().getName().equals("com.tbb.action.PrepareAction"))
				)
		{
			String permit_id = this.getClass().getName() + "@" + name;
			checkPermit(request, mapping, permit_id);
		}

		ActionForward preForward = preAction(mapping, form, request, context);
		if (preForward != null)
		{
			return preForward;
		}

		// Invoke the named method, and return the result
		ActionForward forward = dispatchMethod(mapping, form, request,
				response, context, name);

		ActionForward postForward = postAction(mapping, form, request, context);
		if (postForward != null)
		{
			return postForward;
		}
		// 设置调试信息
		// log.debug(RequestUtils.requestInfo(request));

		return forward;

	}

	// ----------------------------------------------------- Protected Methods

	/**
     * Dispatch to the specified method.
     * 
     * @since Struts 1.1
     */
	protected ActionForward dispatchMethod(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionContext context, String name)
			throws Exception
	{

		// Identify the method object to be dispatched to
		Method method = null;
		try
		{
			method = getMethod(name);
		}
		catch (NoSuchMethodException e)
		{
			String message = messages.getMessage("dispatch.method", mapping
					.getPath(), name);
			log.error(message, e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		}

		ActionForward forward = null;
		try
		{
			Object args[] = {mapping, form, request, response, context};
			forward = (ActionForward)method.invoke(this, args);
		}
		catch (ClassCastException e)
		{
			String message = messages.getMessage("dispatch.return", mapping
					.getPath(), name);
			log.error(message, e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		}
		catch (IllegalAccessException e)
		{
			String message = messages.getMessage("dispatch.error", mapping
					.getPath(), name);
			log.error(message, e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		}
		catch (InvocationTargetException e)
		{
			// Rethrow the target exception if possible so that the
			// exception handling machinery can deal with it
			Throwable t = e.getTargetException();
			if (t instanceof Exception)
			{
				throw ((Exception)t);
			}
			else
			{
				String message = messages.getMessage("dispatch.error", mapping
						.getPath(), name);
				log.error(message, e);
				response.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
				return (null);
			}
		}

		// Return the returned ActionForward instance
		return (forward);
	}

	/**
     * Introspect the current class to identify a method of the specified name
     * that accepts the same parameter types as the <code>execute</code>
     * method does.
     * 
     * @param name Name of the method to be introspected
     * @exception NoSuchMethodException if no such method can be found
     */
	@SuppressWarnings ("unchecked")
	protected Method getMethod(String name) throws NoSuchMethodException
	{

		synchronized (methods)
		{
			Method method = (Method)methods.get(name);
			if (method == null)
			{
				method = clazz.getMethod(name, types);
				methods.put(name, method);
			}
			return (method);
		}

	}

	protected String getDefaultParameterName()
	{
		return "s";
	}

	/**
     * 获取当前登录用户的类，如果为null则抛错，如果组织机构id为null也抛错
     * 
     * @param request
     * @return
     * @throws BaseException
     */
	protected SysUser getUser(HttpServletRequest request) throws BaseException
	{
		SysUser user = (SysUser)request.getSession(true).getAttribute("user");
		String organ_id = user.getOrgan_id();
		if (null == organ_id || "".equals(organ_id.trim()))
		{
			throw new BaseException("用户组织机构编号为空！");
		}
		return user;
	}

	/**
     * 获取当前登录用户的NodeId
     * 
     * @return nodeid 的值
     */
	protected String getNodeId()
	{
		Object nodeId = getServlet().getServletContext()
				.getAttribute("NODE_ID");
		if (nodeId != null)
		{
			return String.valueOf(nodeId);
		}
		else
		{
			return "";
		}
	}
}
