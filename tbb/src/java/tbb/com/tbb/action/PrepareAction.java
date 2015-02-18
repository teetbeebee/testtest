package com.tbb.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.config.Config;
import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;
import com.newbee.tmf.util.MemberUtils;
import com.newbee.tmf.util.RequestUtils;
import com.newbee.tmf.util.StringUtils;
import com.tbb.member.domain.Web_member;
import com.tbb.member.service.Web_memberService;
import com.tbb.tools.GUID;

public class PrepareAction extends BaseDispatchAction {

	//s select index
	public ActionForward d(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		Config config = (Config) request.getSession().getServletContext().getAttribute("config");
		String currentsystem = config.getCurrentSystem();
		
		if(currentsystem.equalsIgnoreCase("vpn")){
			return mapping.findForward("vpnindex");
		}
		
		return mapping.findForward("memberloginframe");
	}

	//ft_member index
	public ActionForward mlf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		return mapping.findForward("memberloginframe");
	}
	
	public ActionForward ml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		return mapping.findForward("memberlogin");
	}
	
	public ActionForward mo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		return mapping.findForward("memberok");
	}
	
	public ActionForward mli(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		RequestUtils.getParameter(request, map);
		if((!StringUtils.isValid(map.get("username"))) || (!StringUtils.isValid(map.get("passwd")))){
			request.setAttribute("msg", "用户名、密码不可为空！");
			return mapping.findForward("memberloginframe");
		}
		
		Web_memberService ms = Web_memberService.getInstance();
		
		List<Web_member> memberlist = ms.queryWeb_memberForList(map);
		if(memberlist.size() == 0){
			request.setAttribute("msg", "用户名、密码不正确！");
			return mapping.findForward("memberloginframe");
		}
		
		Web_member member = memberlist.get(0);
		member.setOid(MemberUtils.genUid());
		ms.updateWeb_member(member);
		
		request.setAttribute("uid", member.getOid());
		
		return mapping.findForward("memberindexframe");
	}

}
