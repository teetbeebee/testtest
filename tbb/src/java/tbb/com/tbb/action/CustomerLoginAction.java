package com.tbb.action;


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
import com.newbee.tmf.util.StringUtils;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysUserService;
import com.tbb.tools.GUID;
import com.tbb.tools.JsonUtil;

public class CustomerLoginAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
//		ZrqgkAction act = new ZrqgkAction();
//		return act.query(mapping, form, request, response, context);
		return mapping.findForward("view");
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception 
	{
		response.setContentType("text/html;charset=UTF-8"); 
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String msg = "";
		Map result = new HashMap();
		String resultString = "";
		boolean islogin = false;
		

		if (user_id == null || password == null){
			msg = "invalid user";
			result.put("error", msg);
			resultString = JsonUtil.object2json(result);
			response.getWriter().write(resultString);
			return null;
		} 

		SysUser user = new SysUser();

		SysUserService us = SysUserService.getInstance();

		user = us.retrieveSysUser(user_id);

		if (user == null) msg = "无效的用户";	

		if (!user.getPassword().equals(StringUtils.strChange(password)))
			msg = "用户密码不符!";
		else
			islogin = true;

		if (user.getState().intValue() == 1) msg = "用户被禁用!";

		if(islogin){
			result.put("status", "success");
			String session_id = GUID.generate();
			result.put("session_id",session_id);
			user.setSession_id(session_id);
			user.setOperate_time(new Timestamp(System.currentTimeMillis()));
			us.updateSysUser(user);
		} else {
			result.put("status", "fail:" + msg);
		}
		
		resultString = JsonUtil.object2json(result);
		response.getWriter().write(resultString);
		return null;
	}
	
	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception 
	{
		Map result = new HashMap();
		result.put("status", "success");
		
		String resultString = JsonUtil.object2json(result);
		response.getWriter().write(resultString);
		return null;
	}

}
