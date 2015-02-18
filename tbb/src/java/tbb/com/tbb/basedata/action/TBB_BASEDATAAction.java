package com.tbb.basedata.action;

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
import com.tbb.basedata.domain.TBB_BASEDATA;
import com.tbb.basedata.service.TBB_BASEDATAService;

public class TBB_BASEDATAAction extends BaseDispatchAction
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
		TBB_BASEDATA TBB_BASEDATA = new TBB_BASEDATA();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(TBB_BASEDATA, map);
		
		TBB_BASEDATAService ds = TBB_BASEDATAService.getInstance();
		ds.createTBB_BASEDATA(TBB_BASEDATA);
		
		return this.query(mapping, form, request, response, context);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String TBB_BASEDATA_id = request.getParameter("TBB_BASEDATA_id");
		if (null == TBB_BASEDATA_id || "".equals(TBB_BASEDATA_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		TBB_BASEDATAService ds = TBB_BASEDATAService.getInstance();
		TBB_BASEDATA TBB_BASEDATA = ds.retrieveTBB_BASEDATA(Integer.parseInt(TBB_BASEDATA_id));
		
		request.setAttribute("TBB_BASEDATA", TBB_BASEDATA);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		TBB_BASEDATA TBB_BASEDATA = new TBB_BASEDATA();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(TBB_BASEDATA, map);

		TBB_BASEDATAService ds = TBB_BASEDATAService.getInstance();
		ds.updateTBB_BASEDATA(TBB_BASEDATA);

		return this.query(mapping, form, request, response, context);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String TBB_BASEDATA_id = request.getParameter("TBB_BASEDATA_id");
		if (null == TBB_BASEDATA_id || "".equals(TBB_BASEDATA_id.trim()))
		{
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		TBB_BASEDATAService ds = TBB_BASEDATAService.getInstance();
		TBB_BASEDATA TBB_BASEDATA = ds.retrieveTBB_BASEDATA(Integer.parseInt(TBB_BASEDATA_id));
		
		request.setAttribute("TBB_BASEDATA", TBB_BASEDATA);
		return mapping.findForward("view");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] TBB_BASEDATA_ids = request.getParameterValues("pk");
		if (TBB_BASEDATA_ids == null || TBB_BASEDATA_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		TBB_BASEDATAService ds = TBB_BASEDATAService.getInstance();
		for (String TBB_BASEDATA_id : TBB_BASEDATA_ids)
		{
			ds.deleteTBB_BASEDATA(Integer.parseInt(TBB_BASEDATA_id));
		}

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

		TBB_BASEDATAService ds = TBB_BASEDATAService.getInstance();
		PageList TBB_BASEDATAList = ds.queryTBB_BASEDATAForPageList(params, pageIndex,
				pageSize);

		request.setAttribute("TBB_BASEDATAList", TBB_BASEDATAList);
		return mapping.findForward("query");
	}
}


