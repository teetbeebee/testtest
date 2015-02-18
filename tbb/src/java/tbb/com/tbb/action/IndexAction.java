package com.tbb.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;

public class IndexAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
//		ZrqgkAction act = new ZrqgkAction();
//		return act.query(mapping, form, request, response, context);
		return mapping.findForward("view");
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
//		ZrqgkAction act = new ZrqgkAction();
//		return act.query(mapping, form, request, response, context);
		return mapping.findForward("query");
	}

	public ActionForward imiation_view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		return mapping.findForward("imiation_view");
	}
	
	public ActionForward left(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception {
		
		return mapping.findForward("left");
	}

}
