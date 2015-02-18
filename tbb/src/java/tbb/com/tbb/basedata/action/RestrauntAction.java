package com.tbb.basedata.action;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import com.tbb.basedata.domain.Restraunt;
import com.tbb.basedata.service.RestrauntService;

public class RestrauntAction extends BaseDispatchAction
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
		Restraunt restraunt = new Restraunt();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(restraunt, map);
		
		RestrauntService service = RestrauntService.getInstance();
		String id = UUID.randomUUID().toString();
		restraunt.setRestraunt_id(id);
		
		// 检测编码是否重复
		if (service.retrieveRestraunt(restraunt.getRestraunt_id()) != null)
		{
			throw new BaseException("新增的ID：“" + restraunt.getRestraunt_id()
					+ "”已存在，不能新增！");
		}

		service.createRestraunt(restraunt);
		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String restraunt_id = request.getParameter("id");
		if (null == restraunt_id || "".equals(restraunt_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		RestrauntService os = RestrauntService.getInstance();

		Restraunt restraunt = os.retrieveRestraunt(restraunt_id);
		if (null == restraunt)
		{
			throw new BaseException("修改编号为“" + restraunt_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("restraunt", restraunt);
		return mapping.findForward("edit");
	}
	
	public ActionForward edit_recipe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String restraunt_id = request.getParameter("id");
		if (null == restraunt_id || "".equals(restraunt_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		RestrauntService os = RestrauntService.getInstance();

		Restraunt restraunt = os.retrieveRestraunt(restraunt_id);
		if (null == restraunt)
		{
			throw new BaseException("修改编号为“" + restraunt_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("restraunt", restraunt);
		return mapping.findForward("recipeList");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Restraunt restraunt = new Restraunt();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(restraunt, map);

		RestrauntService service = RestrauntService.getInstance();
		service.updateRestraunt(restraunt);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] restraunt_ids = request.getParameterValues("pk");
		if (restraunt_ids == null || restraunt_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		RestrauntService os = RestrauntService.getInstance();
		for (String restraunt_id : restraunt_ids)
		{
			os.deleteRestraunt(restraunt_id);
		}
		String restraunt_id = request.getParameter("restraunt_id");
		
		os.deleteRestraunt(restraunt_id);
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

		RestrauntService restraunts = RestrauntService.getInstance();
		PageList restrauntList = restraunts.queryRestrauntForPageList(params, pageIndex, pageSize);

		request.setAttribute("restrauntList", restrauntList);
		return mapping.findForward("query");
	}


}


