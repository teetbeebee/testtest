package com.tbb.sys.action;

import java.util.HashMap;
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
import com.newbee.tmf.util.ValueUtils;
import com.tbb.sys.domain.SysRole;
import com.tbb.sys.service.SysRoleService;

public class SysRoleAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();		
		
		SysRoleService srs = SysRoleService.getInstance();
		PageList sysRoleList = srs
				.querySysRoleForPageList(params, pageIndex, pageSize);

		request.setAttribute("sysRoleList", sysRoleList);

		return mapping.findForward("query");
	}

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		String role_id = request.getParameter("role_id");

		if (null == role_id || "".equals(role_id.trim())) {
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		SysRoleService srs = SysRoleService.getInstance();
		SysRole sysRole = srs.retrieveSysRole(role_id);
		if (null == sysRole) {
			throw new BaseException("查看编号为“" + role_id + "”的对象为空或不存在，不能修改！");
		}
		

		request.setAttribute("sysRole", sysRole);

		return mapping.findForward("view");
	}

	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {

		return mapping.findForward("add");
	}

	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		SysRole sysRole = new SysRole();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(sysRole, map);

		SysRoleService srs = SysRoleService.getInstance();
		// 检测编码是否重复
		if (srs.retrieveSysRole(sysRole.getRole_id()) != null) {
			throw new BaseException("新增的角色ID“" + sysRole.getRole_id()
					+ "”已存在，不能新增！");
		}
		
		srs.createSysRole(sysRole);

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		String role_id = request.getParameter("role_id");

		if (null == role_id || "".equals(role_id.trim())) {
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		SysRoleService srs = SysRoleService.getInstance();
		SysRole sysRole = srs.retrieveSysRole(role_id);
		if (null == sysRole) {
			throw new BaseException("修改编号为“" + role_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("sysRole", sysRole);
		
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		SysRole sysRole = new SysRole();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(sysRole, map);

		SysRoleService srs = SysRoleService.getInstance();
		
		srs.updateSysRole(sysRole);

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		String[] role_ids = request.getParameterValues("pk");
		if (role_ids == null || role_ids.length < 1) {
			throw new BaseException("请选择要删除的记录");
		}

		SysRoleService srs = SysRoleService.getInstance();

		for (String role_id : role_ids) {
			srs.deleteSysRole(role_id);
		}

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}
}
