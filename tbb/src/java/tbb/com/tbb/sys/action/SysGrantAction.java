package com.tbb.sys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;
import com.tbb.sys.domain.SysRole;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysGrantService;
import com.tbb.sys.service.SysRoleService;
import com.tbb.sys.service.SysUserService;

public class SysGrantAction extends BaseDispatchAction {

	public ActionForward edit_user_role(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		SysUserService sus = SysUserService.getInstance();

		// 用户基本信息
		String user_id = request.getParameter("user_id");
		// if(user_id != null && "admin".equals(user_id.trim())){
		// throw new BaseException("系统管理员用户所具有的角色不能修改！");
		// }

		SysUser user = sus.retrieveSysUser(user_id);
		request.setAttribute("user", user);

		// 用户所具有的角色
		SysGrantService sgs = SysGrantService.getInstance();
		List sysRolesSelected = sgs.sysUserHaveSysRoles(user_id);
		request.setAttribute("sysRolesSelected", sysRolesSelected);

		// 尚未分配的角色
		List sysRolesSelectedNo = sgs.sysUserHaveNotSysRoles(user_id);
		request.setAttribute("sysRolesSelectedNo", sysRolesSelectedNo);

		return mapping.findForward("edit_user_role");
	}

	public ActionForward edit_user_role_save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionContext context)
			throws Exception {
		SysGrantService sgs = SysGrantService.getInstance();
		String user_id = request.getParameter("user_id");
		String[] role_ids = request.getParameterValues("assign");

		sgs.sysUserAddSysRoles(user_id, role_ids);

		return this.redirect(mapping, form, request, response, context,
				"redoQuerySysUser", context.getQueryParams().getQueryMap());
	}

	public ActionForward edit_role_permit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionContext context)
			throws Exception {

		SysRoleService rs = SysRoleService.getInstance();
		// 角色基本信息
		String role_id = request.getParameter("role_id");
		// if (role_id != null && "r100".equals(role_id.trim())) {
		// throw new BaseException("系统管理员角色所具有的权限不可修改！");
		// }

		SysRole sysRole = rs.retrieveSysRole(role_id);
		request.setAttribute("sysRole", sysRole);

		// 角色所具有的权限
		SysGrantService sgs = SysGrantService.getInstance();
		List sysPermitsSelected = sgs.sysRoleHaveSysPermits(role_id);
		request.setAttribute("sysPermitsSelected", sysPermitsSelected);

		// 尚未分配的权限
		List sysPermitsSelectedNo = sgs.sysRoleHaveNotSysPermits(role_id);
		request.setAttribute("sysPermitsSelectedNo", sysPermitsSelectedNo);

		return mapping.findForward("edit_role_permit");
	}

	public ActionForward edit_role_permit_save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, ActionContext context)
			throws Exception {
		SysGrantService sgs = SysGrantService.getInstance();
		String role_id = request.getParameter("role_id");
		String[] permit_ids = request.getParameterValues("assign");

		sgs.sysRoleAddSysPermits(role_id, permit_ids);

		return this.redirect(mapping, form, request, response, context,
				"redoQuerySysRole", context.getQueryParams().getQueryMap());
	}
}