package com.tbb.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.config.Config;
import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;
import com.newbee.tmf.util.RequestUtils;
import com.newbee.tmf.util.RuntimeUtils;
import com.newbee.tmf.util.StringUtils;
import com.newbee.tmf.util.ValueUtils;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysUserService;
import com.tbb.vpn.domain.Vpnuser;
import com.tbb.vpn.service.VpnuserService;

public class RegisteAction extends BaseDispatchAction
{
	/* 进入注册页面 */
	public ActionForward r(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		if(RuntimeUtils.getSysParam(request, "currentsystem").equalsIgnoreCase("vpn"))
			return mapping.findForward("vpnregiste");
		else
			return mapping.findForward("registe");
	}
	
	/* 注册保存 */
	public ActionForward rs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		if(RuntimeUtils.getSysParam(request, "currentsystem").equalsIgnoreCase("vpn")){
			return this.rs_vpn(mapping, form, request, response, context);
		}
		
		Config config = (Config) request.getSession().getServletContext().getAttribute("config");
		String currentsystem = config.getCurrentSystem();
		
		SysUser user = new SysUser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(user, map);

		SysUserService us = SysUserService.getInstance();
		user.setPassword(StringUtils.strChange(user.getPassword()));
		user.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setState(0);
		us.createSysUser(user);
		us.sendRegisteMail(user.getEmail(), user.getUser_id());
		
		return redirect(mapping, form, request, response, context, "r",
				context.getQueryParams().getQueryMap());
	}
	
	/* vpn用户注册保存 */
	public ActionForward rs_vpn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Vpnuser vpnuser = new Vpnuser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnuser, map);
		
		VpnuserService vus = VpnuserService.getInstance();
		
		if(vpnuser.getUser_name() == null){
			vpnuser.setUser_name(vpnuser.getEmail());
		}
		vpnuser.setPassword(StringUtils.strChange(vpnuser.getPassword()));
		vpnuser.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
		vpnuser.setState(0);
		
		vus.createVpnuser(vpnuser);
		vus.sendRegisteEmail(vpnuser.getUser_id(), vpnuser.getEmail());
//		us.sendRegisteMail(user.getEmail(), user.getUser_id());
		
		return mapping.findForward("vpnok");
	}
	
	/* 检查邮件是否已存在 */
	public ActionForward c(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		if(RuntimeUtils.getSysParam(request, "currentsystem").equalsIgnoreCase("vpn")){
			return this.c_vpn(mapping, form, request, response, context);
		}
		
		String email = request.getParameter("email");
		SysUserService us = SysUserService.getInstance();
		Map params = new HashMap();
		params.put("email", email);
    	Map model = new HashMap();
    	
    	List userList =  us.querySysUserByEmail(params);
    	if(userList.size() > 0)
    		model.put("results", userList);
    	
    	// 将查询结果封装成JSon对象
    	response.getWriter().write(JSONObject.fromObject(model).toString());

        return null;
    }
	
	/* 检查vpn邮件是否已存在 */
	public ActionForward c_vpn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String email = request.getParameter("email");
		VpnuserService vus = VpnuserService.getInstance();
		Map params = new HashMap();
		params.put("email", email);
    	Map model = new HashMap();
    	
    	List vpnuserList =  vus.queryVpnuserForList(params);
    	if(vpnuserList.size() > 0)
    		model.put("results", vpnuserList);
    	
    	// 将查询结果封装成JSon对象
    	response.getWriter().write(JSONObject.fromObject(model).toString());

        return null;
    }
	
	public ActionForward checkActivate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
			{
		String email = request.getParameter("email");
		SysUserService us = SysUserService.getInstance();
		Map params = new HashMap();
		params.put("email", email);
    	Map model = new HashMap();
    	
    	List userList =  us.querySysUserByEmail(params);
    	
    	if(userList.size() == 0)
    		model.put("results", "notExist");
    	else{
    		SysUser user = (SysUser)userList.get(0);
    		if(user.getState() == 0)
    			model.put("results", "notRegiste");
    		else
    			model.put("results", "registed");
    	}
    	
    	// 将查询结果封装成JSon对象
    	response.getWriter().write(JSONObject.fromObject(model).toString());

        return null;
    }
}
