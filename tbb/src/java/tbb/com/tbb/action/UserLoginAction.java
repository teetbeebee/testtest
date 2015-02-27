package com.tbb.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.newbee.tmf.util.StringUtils;
import com.newbee.tmf.util.ValueUtils;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysUserService;
import com.tbb.tools.GUID;
import com.tbb.tools.email.MailTool;
import com.tbb.vpn.domain.Vpnuser;
import com.tbb.vpn.service.VpnuserService;

public class UserLoginAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward d(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Config config = (Config) request.getSession().getServletContext().getAttribute("config");
		String currentsystem = config.getCurrentSystem();
		
		if(currentsystem.equalsIgnoreCase("vpn")){
			return this.vpnLogin(mapping, form, request, response, context);
		}

        return null;
    }
	
	public ActionForward vpnLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Vpnuser vpnuser = new Vpnuser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnuser, map);
		VpnuserService vs = VpnuserService.getInstance();
    	
		String msg = "";
		String sess_id = "";
		String result = "";
    	Vpnuser vpnuser2 = vs.retrieveVpnuserByEmail(vpnuser.getEmail());
    	if(vpnuser2 != null && vpnuser2.getPassword().endsWith(StringUtils.md5x(vpnuser.getPassword()))
    			&& vpnuser2.getEmail().equals(vpnuser.getEmail())
    			){
    		msg = "欢迎回来，" + vpnuser.getEmail();
    		sess_id = GUID.generate();
    		vpnuser2.setSession_id(sess_id);
    		vs.updateVpnuser(vpnuser2);
    		result = "success";
    	} else {
    		msg = "登录失败，用户名密码错误";
    		sess_id = "";
    		result = "fail";
    	}
    	
    	Map model = new HashMap();
    	model.put("msg", msg);
    	model.put("sess_id", sess_id);
    	model.put("result", result);
    	
    	// 将查询结果封装成JSon对象
    	response.setContentType("text/html;charset=utf-8");
    	response.getWriter().write(JSONObject.fromObject(model).toString());
    	
    	return null;

    }

}
