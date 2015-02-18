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
import com.tbb.basedata.domain.TBB_PROJ_LIST;
import com.tbb.basedata.service.TBB_PROJ_LISTService;

public class TBB_PROJ_LISTAction extends BaseDispatchAction
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
		TBB_PROJ_LIST TBB_PROJ_LIST = new TBB_PROJ_LIST();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(TBB_PROJ_LIST, map);
		
		TBB_PROJ_LISTService ds = TBB_PROJ_LISTService.getInstance();
		ds.createTBB_PROJ_LIST(TBB_PROJ_LIST);
		
		return this.query(mapping, form, request, response, context);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String TBB_PROJ_LIST_id = request.getParameter("TBB_PROJ_LIST_id");
		if (null == TBB_PROJ_LIST_id || "".equals(TBB_PROJ_LIST_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		TBB_PROJ_LISTService ds = TBB_PROJ_LISTService.getInstance();
		TBB_PROJ_LIST TBB_PROJ_LIST = ds.retrieveTBB_PROJ_LIST(Integer.parseInt(TBB_PROJ_LIST_id));
		
		request.setAttribute("TBB_PROJ_LIST", TBB_PROJ_LIST);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		TBB_PROJ_LIST TBB_PROJ_LIST = new TBB_PROJ_LIST();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(TBB_PROJ_LIST, map);

		TBB_PROJ_LISTService ds = TBB_PROJ_LISTService.getInstance();
		ds.updateTBB_PROJ_LIST(TBB_PROJ_LIST);

		return this.query(mapping, form, request, response, context);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String TBB_PROJ_LIST_id = request.getParameter("TBB_PROJ_LIST_id");
		if (null == TBB_PROJ_LIST_id || "".equals(TBB_PROJ_LIST_id.trim()))
		{
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		TBB_PROJ_LISTService ds = TBB_PROJ_LISTService.getInstance();
		TBB_PROJ_LIST TBB_PROJ_LIST = ds.retrieveTBB_PROJ_LIST(Integer.parseInt(TBB_PROJ_LIST_id));
		
		request.setAttribute("TBB_PROJ_LIST", TBB_PROJ_LIST);
		return mapping.findForward("view");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] TBB_PROJ_LIST_ids = request.getParameterValues("pk");
		if (TBB_PROJ_LIST_ids == null || TBB_PROJ_LIST_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		TBB_PROJ_LISTService ds = TBB_PROJ_LISTService.getInstance();
		for (String TBB_PROJ_LIST_id : TBB_PROJ_LIST_ids)
		{
			ds.deleteTBB_PROJ_LIST(Integer.parseInt(TBB_PROJ_LIST_id));
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

		TBB_PROJ_LISTService ds = TBB_PROJ_LISTService.getInstance();
		PageList TBB_PROJ_LISTList = ds.queryTBB_PROJ_LISTForPageList(params, pageIndex,
				pageSize);

		request.setAttribute("TBB_PROJ_LISTList", TBB_PROJ_LISTList);
		return mapping.findForward("query");
	}
}


