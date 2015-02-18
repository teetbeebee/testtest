package com.newbee.tmf.core;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


public class ActionExceptionHandler extends ExceptionHandler {

  Log log = LogFactory.getLog(ActionExceptionHandler.class);

  public ActionForward execute(Exception ex, ExceptionConfig config,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ActionForward forward = null;
	    String message = ex.getMessage();
//	    if (log.isDebugEnabled()) {
	      StringWriter sw = new StringWriter();
	      ex.printStackTrace(new PrintWriter(sw));
	      String detail = sw.toString();
	      log.debug(detail);
//	    }
	    if (ex instanceof LoginException) {
	      request.setAttribute("loginwarn", "您没有登录，请登录后使用系统!");
	      forward = mapping.findForward("login");
	    }
	    else if (ex instanceof BaseException) {
	      request.setAttribute("FAILURE_MESSAGE", message);
	      request.setAttribute("DETAIL_MESSAGE", detail);
	      forward = mapping.findForward("error");
	    }
	    else {
	      request.setAttribute("ERROR_MESSAGE", message);
	      request.setAttribute("DETAIL_MESSAGE", detail);
	      forward = mapping.findForward("error");
	    }

	    return forward;
	  }
  
}
