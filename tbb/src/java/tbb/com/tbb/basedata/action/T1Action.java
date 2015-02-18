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
import com.tbb.basedata.domain.T1;
import com.tbb.basedata.service.T1Service;

public class T1Action extends BaseDispatchAction
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
		T1 t1 = new T1();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(t1, map);
		
		T1Service service = T1Service.getInstance();
		
		// 检测编码是否重复
		if (service.retrieveT1(t1.getId()) != null)
		{
			throw new BaseException("新增的ID：“" + t1.getId()
					+ "”已存在，不能新增！");
		}

		service.createT1(t1);
		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String t1_id = request.getParameter("id");
		if (null == t1_id || "".equals(t1_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		T1Service os = T1Service.getInstance();

		T1 t1 = os.retrieveT1(t1_id);
		if (null == t1)
		{
			throw new BaseException("修改编号为“" + t1_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("t1", t1);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		T1 t1 = new T1();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(t1, map);

		T1Service service = T1Service.getInstance();
		service.updateT1(t1);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] t1_ids = request.getParameterValues("pk");
		if (t1_ids == null || t1_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		T1Service os = T1Service.getInstance();
		for (String t1_id : t1_ids)
		{
			os.deleteT1(t1_id);
		}
		String t1_id = request.getParameter("t1_id");
		
		os.deleteT1(t1_id);
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

		T1Service t1s = T1Service.getInstance();
		PageList t1List = t1s.queryT1ForPageList(params, pageIndex, pageSize);

		request.setAttribute("t1List", t1List);
		return mapping.findForward("query");
	}


}
