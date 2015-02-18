package com.tbb.vpn.action;

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
import com.tbb.vpn.domain.Vpnuser;
import com.tbb.vpn.service.VpnuserService;

public class VpnuserAction extends BaseDispatchAction
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
		Vpnuser vpnuser = new Vpnuser();
		vpnuser.setUser_id(GUID.generate());
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnuser, map);
		
		VpnuserService service = VpnuserService.getInstance();
		
		
		try{
			service.createVpnuser(vpnuser);
		} catch(Exception ex){
			throw ex;
//			throw new BaseException("新增的ID：“" + vpnuser.getUser_id()
//					+ "”失败，不能新增！");
		}

		return this.query(mapping, form, request, response, context);
//		return mapping.findForward("query");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String vpnuser_id = request.getParameter("user_id");
		if (null == vpnuser_id || "".equals(vpnuser_id.trim()))
		{
			throw new BaseException("修改的对象编号为空，不能修改！");
		}

		VpnuserService os = VpnuserService.getInstance();

		Vpnuser vpnuser = os.retrieveVpnuser(vpnuser_id);
		if (null == vpnuser)
		{
			throw new BaseException("修改编号为“" + vpnuser_id + "”的对象为空或不存在，不能修改！");
		}

		request.setAttribute("vpnuser", vpnuser);
		return mapping.findForward("edit");
	}

	public ActionForward edit_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Vpnuser vpnuser = new Vpnuser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnuser, map);

		VpnuserService service = VpnuserService.getInstance();
		service.updateVpnuser(vpnuser);

		return this.query(mapping, form, request, response, context);
	}

	@SuppressWarnings ("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String[] vpnuser_ids = request.getParameterValues("pk");
		if (vpnuser_ids == null || vpnuser_ids.length < 1)
		{
			throw new BaseException("请选择要删除的记录");
		}

		VpnuserService os = VpnuserService.getInstance();
		for (String vpnuser_id : vpnuser_ids)
		{
			os.deleteVpnuser(vpnuser_id);
		}
		String vpnuser_id = request.getParameter("vpnuser_id");
		
		os.deleteVpnuser(vpnuser_id);
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

		VpnuserService vpnusers = VpnuserService.getInstance();
		PageList vpnuserList = vpnusers.queryVpnuserForPageList(params, pageIndex, pageSize);

		request.setAttribute("vpnuserList", vpnuserList);
		return mapping.findForward("query");
	}


}


