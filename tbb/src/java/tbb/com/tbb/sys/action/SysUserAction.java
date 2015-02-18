package com.tbb.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;
import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.PageList;
import com.newbee.tmf.core.QueryParams;
import com.newbee.tmf.util.RequestUtils;
import com.newbee.tmf.util.StringUtils;
import com.newbee.tmf.util.ValueUtils;
import com.tbb.basedata.service.OrganService;
import com.tbb.manage.SysUserManage;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysUserService;

public class SysUserAction extends BaseDispatchAction
{

	@SuppressWarnings ("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();

		SysUserService us = SysUserService.getInstance();
		PageList userList = us.querySysUserForPageList(params, pageIndex,
				pageSize);

		request.setAttribute("userList", userList);

		return mapping.findForward("query");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String user_id = request.getParameter("user_id");

		if (null == user_id || "".equals(user_id.trim()))
		{
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		SysUserService us = SysUserService.getInstance();
		SysUser user = us.retrieveSysUser(user_id);
		if (null == user)
		{
			throw new BaseException("查看编号为“" + user_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("user", user);

		OrganService os = OrganService.getInstance();
		List organList = os.getOrganTree("0");
		request.setAttribute("organList", organList);

		return mapping.findForward("view");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

//		OrganService os = OrganService.getInstance();
//		List organList = os.getOrganTree("0");
//		request.setAttribute("organList", organList);

		return mapping.findForward("add");
	}

	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		SysUser user = new SysUser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(user, map);

		SysUserService us = SysUserService.getInstance();
		// 检测编码是否重复
		if (us.retrieveSysUser(user.getUser_id()) != null)
		{
			throw new BaseException("新增的用户帐号“" + user.getUser_id()
					+ "”已存在，不能新增！");
		}

		user.setPassword(StringUtils.strChange(user.getPassword()));

		us.createSysUser(user);

		javax.servlet.ServletContext application = this.servlet
				.getServletContext();
		SysUserManage sum = SysUserManage.getInstance();
		sum.reloadSysUser();
		application.setAttribute("sysUserList", sum.getSysUserList());

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	@SuppressWarnings ("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String user_id = request.getParameter("user_id");

		if (null == user_id || "".equals(user_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		SysUserService us = SysUserService.getInstance();
		SysUser user = us.retrieveSysUser(user_id);
		if (null == user)
		{
			throw new BaseException("修改编号为“" + user_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("user", user);

//		OrganService os = OrganService.getInstance();
//		List organList = os.getOrganTree("0");
//		request.setAttribute("organList", organList);

		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		SysUser user = new SysUser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(user, map);

		SysUserService us = SysUserService.getInstance();

		us.updateSysUser(user);

		javax.servlet.ServletContext application = this.servlet
				.getServletContext();
		SysUserManage sum = SysUserManage.getInstance();
		sum.reloadSysUser();
		application.setAttribute("sysUserList", sum.getSysUserList());

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	// 系统管理员设置密码
	public ActionForward edit_pwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String user_id = request.getParameter("user_id");

		if (null == user_id || "".equals(user_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		SysUserService us = SysUserService.getInstance();
		SysUser user = us.retrieveSysUser(user_id);
		if (null == user)
		{
			throw new BaseException("修改编号为“" + user_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("user", user);

		return mapping.findForward("edit_pwd");
	}

	public ActionForward edit_pwd_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		SysUser user = new SysUser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(user, map);

		SysUserService us = SysUserService.getInstance();

		user.setPassword(StringUtils.strChange(user.getPassword()));

		us.updateSysUser(user);

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	// 个人设置密码
	public ActionForward edit_my_pwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		// 取操作人账号
		SysUser user = this.getUser(request);
		String user_id = user.getUser_id();
		String isSimulate = request.getParameter("isSimulate");

		if (null == user_id || "".equals(user_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		request.setAttribute("user", user);

		request.setAttribute("msg", request.getAttribute("msg"));

		if (isSimulate != null && !isSimulate.equals(""))
		{
			return mapping.findForward("edit_imi_pwd");
		}

		return mapping.findForward("edit_my_pwd");
	}

	public ActionForward edit_my_pwd_save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionContext context)
			throws Exception
	{

		SysUserService us = SysUserService.getInstance();

		String password_old = request.getParameter("password_old");
		String user_id = request.getParameter("user_id");
		SysUser user = us.retrieveSysUser(user_id);
		if (user == null)
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		if (!user.getPassword().equals(StringUtils.strChange(password_old)))
		{
			throw new BaseException("原密码不正确，修改失败！");
		}

		user = new SysUser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(user, map);

		user.setPassword(StringUtils.strChange(user.getPassword()));

		us.updateSysUser(user);

		request.setAttribute("msg", "修改成功");

		return edit_my_pwd(mapping, form, request, response, context);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] user_ids = request.getParameterValues("pk");
		if (user_ids == null || user_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		SysUserService us = SysUserService.getInstance();

		for (String user_id : user_ids)
		{

			SysUser user = us.retrieveSysUser(user_id);
			if (user != null)
			{
				if (user.getIs_sys() != null
						&& user.getIs_sys().intValue() == 1)
				{
					continue;
				}
			}

			us.deleteSysUser(user_id);
		}

		javax.servlet.ServletContext application = this.servlet
				.getServletContext();
		SysUserManage sum = SysUserManage.getInstance();
		sum.reloadSysUser();
		application.setAttribute("sysUserList", sum.getSysUserList());

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}
	
	public ActionForward registe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return redirect(mapping, form, request, response, context, "registe",
				context.getQueryParams().getQueryMap());
	}
}
