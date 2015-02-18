package com.newbee.tmf.core;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.util.StringUtils;
import com.newbee.tmf.util.ValueUtils;
import com.tbb.member.domain.Web_member;
import com.tbb.member.service.Web_memberService;
import com.tbb.sys.domain.SysPermit;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysGrantService;
import com.tbb.sys.service.SysUserService;

public class BaseAction extends Action
{

	protected static Log log = LogFactory.getLog(BaseAction.class);

	/**
     * execute
     * 
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ActionContext context = (ActionContext)request
				.getAttribute(ActionContext.WMF_ACTION_CONTEXT_KEY);
		if (context == null)
		{
			context = loadContext(mapping, form, request, response);
		}

		ActionForward preForward = preAction(mapping, form, request, context);
		if (preForward != null)
		{
			return preForward;
		}

		// 调用扩展的execute方法
		ActionForward forward = execute(mapping, form, request, response,
				context);

		ActionForward postForward = postAction(mapping, form, request, context);
		if (postForward != null)
		{
			return postForward;
		}
		return forward;
	}

	/**
     * struts的action扩展，添加了一个用于同service间传递参数的context
     * 
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param context Context
     * @throws Exception
     * @return ActionForward
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		throw new Exception(this.getClass() + "没有重载方法execute");
	}

	/**
     * 用于预处理,不可以处理response对象
     * 
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param context ActionContext
     * @throws Exception
     * @return ActionForward
     */
	public ActionForward preAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, ActionContext context) throws Exception
	{
		return null;
	}

	/**
     * 用于后处理，不可以处理response对象
     * 
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param context ActionContext
     * @throws Exception
     * @return ActionForward
     */
	public ActionForward postAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, ActionContext context) throws Exception
	{
		return null;
	}

	protected ActionContext loadContext(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ActionContext context = new ActionContext();

		// 从request中取出参数
		/** @done 改用专用的QueryParams类，处理好数组的参数值 */
		QueryParams params = new QueryParams();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements())
		{
			String name = (String)names.nextElement();
			if (params.isQueryParameterName(name))
			{
				// 取回原始名称
				String new_name = params.getParameterName(name);
				// 添加到QueryParams中去
				String value = request.getParameter(name);
				params.addParameter(new_name, value);

				String[] values = request.getParameterValues(name);
				params.addParameter(new_name, values);

			}
		}
		context.setQueryParams(params);

		context.put(ActionContext.WMF_CTX_REQUEST, request);
		context.put(ActionContext.WMF_CTX_RESPONSE, response);
		context.put(ActionContext.WMF_CTX_FORM, form);
		context.put(ActionContext.WMF_CTX_MAPPING, mapping);

		context.put(ActionContext.WMF_USER, getLogin(request));

		// 在request中放置系统自动处理对象
		request.setAttribute(ActionContext.WMF_ACTION_CONTEXT_KEY, context);
		request.setAttribute(ActionContext.WMF_QUERY_PARAMS_KEY, params);
		return context;

	}

	protected ActionForward redirect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context, String name, Map params) throws Exception
	{

		ActionForward forward = mapping.findForward(name);
		boolean isRedirect = forward.getRedirect();
		String path = forward.getPath();
		if (params != null)
		{
			path = ValueUtils.appendUrlParameter(path, params);
		}

		return new ActionForward(path, isRedirect);
	}

	protected ActionForward redirect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context, String name, QueryParams params)
			throws Exception
	{

		ActionForward forward = mapping.findForward(name);
		boolean isRedirect = forward.getRedirect();
		String path = forward.getPath();
		if (params != null)
		{
			path = ValueUtils.appendUrlParameter(path, params);
		}

		return new ActionForward(path, isRedirect);
	}

	public int getPageIndex(HttpServletRequest request)
	{
		// 读取页码参数
		String pageIndex = (String)request.getParameter("pageIndex");
		if (pageIndex == null || pageIndex.trim().equals(""))
		{
			return 1;
		}
		else
		{
			try
			{
				return Integer.parseInt(pageIndex);
			}
			catch (Exception e)
			{
				return 1;
			}
		}
	}

	public int getPageSize(HttpServletRequest request)
	{
		// 读取页码参数
		String pageIndex = (String)request.getParameter("pageSize");
		if (pageIndex == null || pageIndex.trim().equals(""))
		{
			return 10;
		}
		else
		{
			try
			{
				return Integer.parseInt(pageIndex);
			}
			catch (Exception e)
			{
				return 10;
			}
		}
	}

	public SysUser getLogin(HttpServletRequest request) throws Exception
	{
		HttpSession sess = (HttpSession)request.getSession(true);
		SysUser login = (SysUser)sess.getAttribute("user");
		return login;
	}
	
	public SysUser getLoginP(HttpServletRequest request) throws Exception
	{
		String session_id = request.getParameter("sid");
		SysUser user = SysUserService.getInstance().retrieveSysUserBySid(session_id);

		return user;
	}

	public void checkLogin(HttpServletRequest request) throws Exception
	{

		SysUser user = getLogin(request);
		if (user == null)
		{
			// throw new LoginException("没有登录!");

			// 制造一个匿名用户登录
			SysUser user1 = SysUserService.getInstance().retrieveSysUser(
					"anonymous");
			if (user1 == null)
			{
				throw new LoginException("匿名用户账号丢失!");
			}
			request.getSession(true).setAttribute("user", user1);

			log.info("Login success:ANONYMOUS has logined.");
		}

	}
	
	public boolean checkLoginP(HttpServletRequest request) throws Exception
	{
		String method = request.getParameter("method");
		if(method != null && method.equals("login")) return true;
		
		String session_id = request.getParameter("sid");
		if (null == session_id || "".equals(session_id.trim())) return false;
		
		SysUser user = SysUserService.getInstance().retrieveSysUserBySid(session_id);
		if(user == null ) return false;
		
		return true;

	}
	
	public boolean checkMemberLogin(HttpServletRequest request) throws Exception
	{
		String uid = request.getParameter("uid");
		if(!StringUtils.isValid(uid)) {
			return false;
		}
		
		Web_member member = Web_memberService.getInstance().retrieveByUid(uid);
		if(member == null){
			return false;
		}
		
		request.setAttribute("uid", uid);
		return true;

	}

	public void checkPermit(HttpServletRequest request, ActionMapping mapping,
			String permit_id) throws Exception
	{

		SysUser user = getLogin(request);

		// 检测是否是有权限
		SysGrantService sgs = SysGrantService.getInstance();
		SysPermit sysPermit = sgs.sysUserSysPermitCheck(user.getUser_id(),
				permit_id);
		if (sysPermit == null)
		{
			throw new BaseException("您无权使用该功能或者您的登录已过期!");
		}
	}
	
	public boolean checkPermitP(HttpServletRequest request, ActionMapping mapping,
			String permit_id) throws Exception
	{
		String method = request.getParameter("method");
		if(method != null && method.equals("login")) return true;
		SysUser user = getLoginP(request);
		
		// 检测是否是有权限
		SysGrantService sgs = SysGrantService.getInstance();
		SysPermit sysPermit = sgs.sysUserSysPermitCheck(user.getUser_id(),
				permit_id);
		if (sysPermit == null) return false;
		else return true;
	}

	public int checkPermitCs(HttpServletRequest request, ActionMapping mapping,
			String permit_id) throws Exception
	{

		// 因为cs端的参数必须包含user_id、organ_id
		// ,terminal_id只在firehouse、center中才有，gis、gps是在web端登录的，所以也会有权限处理
		String user_id = request.getParameter("user_id");

		if (permit_id.indexOf("login") == -1)
		{
			if (user_id == null)
			{
				// 用户未登录，不能执行此操作!
				return -1;
			}

			// 当用户登录过期后直接可用，以后出现无权限问题
			if (user_id.equals(""))
			{
				return 1;
			}

			// 检测是否是有权限
			SysGrantService sgs = SysGrantService.getInstance();
			SysPermit sysPermit = sgs.sysUserSysPermitCheck(user_id, permit_id);
			if (sysPermit == null)
			{
				// 您无权使用该功能!
				return 0;
			}
		}

		return 1;
	}
}
