package com.tbb.basedata.action;

import java.util.HashMap;
import java.util.List;
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
import com.tbb.basedata.XTree.OrganTree;
import com.tbb.basedata.domain.Organ;
import com.tbb.basedata.service.OrganService;

public class OrganAction extends BaseDispatchAction
{
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		OrganService os = OrganService.getInstance();
		List organList = os.getOrganTree("0");
		request.setAttribute("organList", organList);

		return mapping.findForward("add");
	}

	public ActionForward add_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Organ organ = new Organ();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(organ, map);
		if (organ.getParent_id() == null || organ.getParent_id().equals(""))
		{
			organ.setParent_id("0");
		}
		
		Organ parent = new Organ();
		OrganService os = OrganService.getInstance();
		parent = os.retrieveOrgan(organ.getParent_id());
		int level = Integer.parseInt(parent.getOrgan_level()) + 1;
		organ.setOrgan_level(String.valueOf(level));
		
		// 检测编码是否重复
		if (os.retrieveOrgan(organ.getOrgan_id()) != null)
		{
			throw new BaseException("新增的组织机构ID：“" + organ.getOrgan_id()
					+ "”已存在，不能新增！");
		}

		os.createOrgan(organ);
		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String organ_id = request.getParameter("organ_id");
		if (null == organ_id || "".equals(organ_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		OrganService os = OrganService.getInstance();

		Organ organ = os.retrieveOrgan(organ_id);
		if (null == organ)
		{
			throw new BaseException("修改编号为“" + organ_id + "”的对象为空或不存在，不能修改！");
		}

		List organList = os.getOrganTree("0");
		request.setAttribute("organList", organList);

		request.setAttribute("organ", organ);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Organ organ = new Organ();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(organ, map);

		Organ parent = new Organ();
		OrganService os = OrganService.getInstance();
		parent = os.retrieveOrgan(organ.getParent_id());
		int level = Integer.parseInt(parent.getOrgan_level()) + 1;
		organ.setOrgan_level(String.valueOf(level));
		os.updateOrgan(organ);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] organ_ids = request.getParameterValues("pk");
		if (organ_ids == null || organ_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		OrganService os = OrganService.getInstance();
		for (String organ_id : organ_ids)
		{
			os.deleteOrgan(organ_id);
		}
		String organ_id = request.getParameter("organ_id");
		
		os.deleteOrgan(organ_id);
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

		OrganService os = OrganService.getInstance();
		PageList organList = os.queryOrganForPageList(params, pageIndex,
				pageSize);

		request.setAttribute("organList", organList);
		return mapping.findForward("query");
	}
	
	public ActionForward showOrganTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		OrganTree organTree = new OrganTree();
		String tree = organTree.ShowOrganTree();
		request.setAttribute("tree", tree);
		String target = request.getParameter("target");
		request.setAttribute("target", target);
		return mapping.findForward("organTree");
	}
}
