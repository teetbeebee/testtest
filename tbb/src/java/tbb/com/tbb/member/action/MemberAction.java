package com.tbb.member.action;

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
import com.tbb.member.domain.Web_member;
import com.tbb.member.service.Web_memberService;

public class MemberAction extends BaseDispatchAction
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
		Web_member web_member = new Web_member();
		web_member.setId(GUID.generate());
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(web_member, map);
		
		Web_memberService service = Web_memberService.getInstance();
		
		
		try{
			service.createWeb_member(web_member);
		} catch(Exception ex){
			throw ex;
//			throw new BaseException("新增的ID：“" + web_member.getId()
//					+ "”失败，不能新增！");
		}

		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String web_member_id = request.getParameter("id");
		if (null == web_member_id || "".equals(web_member_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		Web_memberService os = Web_memberService.getInstance();

		Web_member web_member = os.retrieveWeb_member(web_member_id);
		if (null == web_member)
		{
			throw new BaseException("修改编号为“" + web_member_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("web_member", web_member);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Web_member web_member = new Web_member();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(web_member, map);

		Web_memberService service = Web_memberService.getInstance();
		service.updateWeb_member(web_member);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] web_member_ids = request.getParameterValues("pk");
		if (web_member_ids == null || web_member_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		Web_memberService os = Web_memberService.getInstance();
		for (String web_member_id : web_member_ids)
		{
			os.deleteWeb_member(web_member_id);
		}
		String web_member_id = request.getParameter("web_member_id");
		
		os.deleteWeb_member(web_member_id);
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

		Web_memberService web_members = Web_memberService.getInstance();
		PageList web_memberList = web_members.queryWeb_memberForPageList(params, pageIndex, pageSize);

		request.setAttribute("web_memberList", web_memberList);
		return mapping.findForward("query");
	}
	
	public ActionForward fti(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return mapping.findForward("memberftindex");
	}
	
	public ActionForward fth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return mapping.findForward("memberfthead");
	}
	
	public ActionForward fts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		return mapping.findForward("memberftbet");
	}
	
	public ActionForward ftzp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{

		QueryParams queryParams = context.getQueryParams();
		Map params = queryParams.getParameterMap();
		int pageIndex = queryParams.getPageIndex();
		int pageSize = queryParams.getPageSize();

		Web_memberService web_members = Web_memberService.getInstance();
		PageList web_memberList = web_members.queryWeb_memberForPageList(params, pageIndex, pageSize);

		request.setAttribute("web_memberList", web_memberList);
		return mapping.findForward("memberftzaopan");
	}


}
