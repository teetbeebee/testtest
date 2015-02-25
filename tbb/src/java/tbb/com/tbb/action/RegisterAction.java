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

public class RegisterAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward d(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Config config = (Config) request.getSession().getServletContext().getAttribute("config");
		String currentsystem = config.getCurrentSystem();
		
		if(currentsystem.equalsIgnoreCase("vpn")){
			return this.vpnRegister(mapping, form, request, response, context);
		}

        return null;
    }
	
	public ActionForward a(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Config config = (Config) request.getSession().getServletContext().getAttribute("config");
		String currentsystem = config.getCurrentSystem();
		
		if(currentsystem.equalsIgnoreCase("vpn")){
			return this.vpnActive(mapping, form, request, response, context);
		}

        return null;
    }
	
	public ActionForward checkActivate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String email = request.getParameter("email");
		VpnuserService vs = VpnuserService.getInstance();
		
		Map params = new HashMap();
		params.put("email", email);
    	Map model = new HashMap();
    	
    	List vpnuserList =  vs.queryVpnuserForList(params);
    	
    	if(vpnuserList.size() == 0)
    		model.put("results", "notExist");
    	else{
    		Vpnuser vpnuser = (Vpnuser)vpnuserList.get(0);
    		if(vpnuser.getState() == VpnuserService._notActive)
    			model.put("results", "notActivate");
    		else if (vpnuser.getState() == VpnuserService._active)
    			model.put("results", "activated");
    		else if (vpnuser.getState() == VpnuserService._forbidden)
    			model.put("results", "forbidden");
    		else 
    			model.put("results", "exception");
    	}
    	
    	// 将查询结果封装成JSon对象
    	response.getWriter().write(JSONObject.fromObject(model).toString());

        return null;
    }
	
	public ActionForward vpnRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		Vpnuser vpnuser = new Vpnuser();
		Map<String, String> map = new HashMap<String, String>();
		// 取页面参数
		RequestUtils.getParameter(request, map);
		// 一次性赋值
		ValueUtils.populate(vpnuser, map);
		vpnuser.setUser_id(GUID.generate());
		vpnuser.setPassword(StringUtils.md5x(vpnuser.getPassword()));
		VpnuserService vs = VpnuserService.getInstance();
		vpnuser.setState(vs._notActive);
		
		vpnuser.setUser_name(vpnuser.getEmail());
		
    	Map model = new HashMap();
    	
    	Vpnuser vpnuser2 = vs.retrieveVpnuserByEmail(vpnuser.getEmail());
    	if(vpnuser2 == null){
    		vs.createVpnuser(vpnuser);
    	} else {
    		vpnuser2.setPassword(vpnuser.getPassword());
    		vs.updateVpnuser(vpnuser2);
    	}
    	
    	
    	this.sendVpnRegisterMail(vpnuser.getEmail(), vpnuser.getUser_id());

    	model.put("results", "success");
    	
    	// 将查询结果封装成JSon对象
    	response.getWriter().write(JSONObject.fromObject(model).toString());

        return null;
    }
	
	public ActionForward vpnActive(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		Map params = new HashMap();
		params.put("email", email);
		params.put("user_id", code);
		
		VpnuserService vs = VpnuserService.getInstance();
		List vpnuserList =  vs.queryVpnuserForList(params);
		Vpnuser vpnuser;
		String msg = "";
		if(vpnuserList.size() > 0){
			vpnuser = (Vpnuser)vpnuserList.get(0);
			vpnuser.setState(vs._active);
			vs.updateVpnuser(vpnuser);
			msg = "恭喜您激活成功，" + vpnuser.getEmail() + "!";
		}

		request.setAttribute("msg", msg);
		return mapping.findForward("vpn");
    }
	
	private void sendVpnRegisterMail(String receiveAddress, String regCode){
		
		String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
				"<html>" +
			  "<head>" +
			  "  <title>[vpn] email帐户激活</title>" +
			  "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
			  "</head>" +
			  "<body>" +
			"	<p style='margin-top: 20px;margin-left: 20px;'>您好，<b>"+ receiveAddress +"</b> ：<br/><br/>" +

			"欢迎加入<b>demavpn</b>！请点击下面的链接来激活您的帐号。<br/><br/>" +

			"<a target='_blank' href='http://localhost:8080/member/register.php?s=a&code="+regCode+"&email="+ receiveAddress +"'>" +
			"http://localhost:8080/member/register.php?s=a&code=" + regCode + "&email="+receiveAddress+"</a><br/><br/>" +

			"如果您的邮箱不支持链接点击，请将以上链接地址复制到你的浏览器地址栏中认证。<br/><br/>" +

			"请来体验吧！<br/><br/>" +

			"</p>" +

			"  </body>" +
			"</html>";
		MailTool.sendmail(receiveAddress, content);
		
	}

}
