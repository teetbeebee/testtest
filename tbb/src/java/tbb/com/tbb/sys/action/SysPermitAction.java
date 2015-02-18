package com.tbb.sys.action;

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
import com.tbb.sys.domain.SysPermit;
import com.tbb.sys.service.SysPermitService;

public class SysPermitAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();		
		
		SysPermitService srs = SysPermitService.getInstance();
		PageList sysPermitList = srs
				.querySysPermitForPageList(params, pageIndex, pageSize);

		request.setAttribute("sysPermitList", sysPermitList);

		return mapping.findForward("query");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		String permit_id = request.getParameter("permit_id");

		if (null == permit_id || "".equals(permit_id.trim())) {
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		SysPermitService srs = SysPermitService.getInstance();
		SysPermit sysPermit = srs.retrieveSysPermit(permit_id);
		if (null == sysPermit) {
			throw new BaseException("查看编号为“" + permit_id + "”的对象为空或不存在，不能修改！");
		}
		

		request.setAttribute("sysPermit", sysPermit);

		return mapping.findForward("view");
	}
}
