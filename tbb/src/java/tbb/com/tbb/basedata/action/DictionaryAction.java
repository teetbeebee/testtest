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
import com.tbb.basedata.domain.Dictionary;
import com.tbb.basedata.service.DictionaryService;

public class DictionaryAction extends BaseDispatchAction
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
		Dictionary dictionary = new Dictionary();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(dictionary, map);
		
		DictionaryService ds = DictionaryService.getInstance();
		ds.createDictionary(dictionary);
		
		return this.query(mapping, form, request, response, context);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String dictionary_id = request.getParameter("dictionary_id");
		if (null == dictionary_id || "".equals(dictionary_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		DictionaryService ds = DictionaryService.getInstance();
		Dictionary dictionary = ds.retrieveDictionary(Integer.parseInt(dictionary_id));
		
		request.setAttribute("dictionary", dictionary);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Dictionary dictionary = new Dictionary();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(dictionary, map);

		DictionaryService ds = DictionaryService.getInstance();
		ds.updateDictionary(dictionary);

		return this.query(mapping, form, request, response, context);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String dictionary_id = request.getParameter("dictionary_id");
		if (null == dictionary_id || "".equals(dictionary_id.trim()))
		{
			throw new BaseException("查看的对象编号为空，不能修改！");
		}

		DictionaryService ds = DictionaryService.getInstance();
		Dictionary dictionary = ds.retrieveDictionary(Integer.parseInt(dictionary_id));
		
		request.setAttribute("dictionary", dictionary);
		return mapping.findForward("view");
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] dictionary_ids = request.getParameterValues("pk");
		if (dictionary_ids == null || dictionary_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		DictionaryService ds = DictionaryService.getInstance();
		for (String dictionary_id : dictionary_ids)
		{
			ds.deleteDictionary(Integer.parseInt(dictionary_id));
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

		DictionaryService ds = DictionaryService.getInstance();
		PageList dictionaryList = ds.queryDictionaryForPageList(params, pageIndex,
				pageSize);

		request.setAttribute("dictionaryList", dictionaryList);
		return mapping.findForward("query");
	}
}
