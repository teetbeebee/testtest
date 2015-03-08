package com.tbb.vpn.action;

import java.sql.Timestamp;
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
import com.tbb.tools.GUID;
import com.tbb.vpn.domain.Vpnline;
import com.tbb.vpn.service.VpnlineService;

public class VpnlineAction extends BaseDispatchAction
{
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return mapping.findForward("add");
	}

	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Vpnline vpnline = new Vpnline();
		vpnline.setLine_id(GUID.generate());
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnline, map);
		
		VpnlineService service = VpnlineService.getInstance();
		
		
		try{
			service.createVpnline(vpnline);
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
//			throw new BaseException("新增的ID：“" + vpnline.getLine_id()
//					+ "”失败，不能新增！");
		}

		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String vpnline_id = request.getParameter("line_id");
		if (null == vpnline_id || "".equals(vpnline_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		VpnlineService os = VpnlineService.getInstance();

		Vpnline vpnline = os.retrieveVpnline(vpnline_id);
		if (null == vpnline)
		{
			throw new BaseException("修改编号为“" + vpnline_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("vpnline", vpnline);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Vpnline vpnline = new Vpnline();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnline, map);

		VpnlineService service = VpnlineService.getInstance();
		service.updateVpnline(vpnline);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] vpnline_ids = request.getParameterValues("pk");
		if (vpnline_ids == null || vpnline_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		VpnlineService os = VpnlineService.getInstance();
		for (String vpnline_id : vpnline_ids)
		{
			os.deleteVpnline(vpnline_id);
		}
		String vpnline_id = request.getParameter("vpnline_id");
		
		os.deleteVpnline(vpnline_id);
		return this.query(mapping, form, request, response, context);
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();

		VpnlineService vpnlines = VpnlineService.getInstance();
		PageList vpnlineList = vpnlines.queryVpnlineForPageList(params, pageIndex, pageSize);

		request.setAttribute("vpnlineList", vpnlineList);
		return mapping.findForward("query");
	}
	
}

