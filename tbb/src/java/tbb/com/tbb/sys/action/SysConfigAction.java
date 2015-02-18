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
import com.tbb.manage.SysConfigManage;
import com.tbb.sys.domain.SysConfig;
import com.tbb.sys.service.SysConfigService;

public class SysConfigAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();
		
		params.put("orderBy", "config_id,sys_id desc");

		SysConfigService sds = SysConfigService.getInstance();
		PageList sysConfigList = sds.querySysConfigForPageList(params, pageIndex, pageSize);
		
		request.setAttribute("sysConfigList", sysConfigList);

		return mapping.findForward("query");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		return mapping.findForward("add");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {

		SysConfig sysConfig = new SysConfig();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(sysConfig, map);

		SysConfigService sds = SysConfigService.getInstance();
		sds.createSysConfig(sysConfig);
		
		SysConfigManage.getInstance().reloadSysConfig();
	
		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		
		String config_id = request.getParameter("config_id");
		String sys_id = request.getParameter("sys_id");


		if (null == config_id || "".equals(config_id.trim())) {
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		if (null == sys_id || "".equals(sys_id.trim())) {
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		HashMap map = new HashMap();
		map.put("config_id", config_id);
		map.put("sys_id", sys_id);
				
		SysConfigService sds = SysConfigService.getInstance();

		SysConfig sysConfig = sds.retrieveSysConfig(map);
		if (null == sysConfig) {
			throw new BaseException("修改编号为“" + config_id + "”的对象为空或不存在，不能修改！");
		}
		
		if (sysConfig.getIs_mod()!= null && sysConfig.getIs_mod().intValue() == 0)
		{
			throw new BaseException("编号为“" + config_id + "”的对象不能修改！");
		}
		
		request.setAttribute("sysConfig", sysConfig);

		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		SysConfig sysConfig = new SysConfig();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);

		// 一次性赋值
		ValueUtils.populate(sysConfig, map);

		SysConfigService sds = SysConfigService.getInstance();

		sds.updateSysConfig(sysConfig);
		
		SysConfigManage.getInstance().reloadSysConfig();
				
		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		
		String config_id = request.getParameter("config_id");
		String sys_id = request.getParameter("sys_id");


		if (null == config_id || "".equals(config_id.trim())) {
			throw new BaseException("对象编号为空！");
		}

		if (null == sys_id || "".equals(sys_id.trim())) {
			throw new BaseException("对象编号为空！");
		}

		HashMap map = new HashMap();
		map.put("config_id", config_id);
		map.put("sys_id", sys_id);
				
		SysConfigService sds = SysConfigService.getInstance();

		SysConfig sysConfig = sds.retrieveSysConfig(map);
		if (null == sysConfig) {
			throw new BaseException("编号为“" + config_id + "”的对象为空或不存在！");
		}
		
		request.setAttribute("sysConfig", sysConfig);

		return mapping.findForward("view");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		String[] pks = request.getParameterValues("pk");
		
		if (pks == null || pks.length < 1) {
			throw new BaseException("请选择要删除的记录");
		}

		SysConfigService sds = SysConfigService.getInstance();

		for (String pk : pks) {

			HashMap map = new HashMap();
			map.put("config_id", pk.split("@@")[0]);
			map.put("sys_id", pk.split("@@")[1]);

			sds.deleteSysConfig(map);
		}
		
		SysConfigManage.getInstance().reloadSysConfig();

		return redirect(mapping, form, request, response, context, "redoQuery",
				context.getQueryParams().getQueryMap());
	}
	
}
