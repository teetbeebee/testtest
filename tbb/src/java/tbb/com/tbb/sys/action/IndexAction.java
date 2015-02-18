package com.tbb.sys.action;


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
		return mapping.findForward("view");
	}
}
