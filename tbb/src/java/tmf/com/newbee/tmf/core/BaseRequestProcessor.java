package com.newbee.tmf.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;

public class BaseRequestProcessor extends TilesRequestProcessor {
  /**
   * Ask the specified <code>Action</code> instance to handle this request.
   * 
   * @param request
   *          The servlet request we are processing
   * @param response
   *          The servlet response we are creating
   * @param action
   *          The Action instance to be used
   * @param form
   *          The ActionForm instance to pass to this Action
   * @param mapping
   *          The ActionMapping instance to pass to this Action
   * @throws IOException
   *           if an input/output error occurs
   * @throws ServletException
   *           if a servlet exception occurs
   * @return ActionForward
   */
  protected ActionForward processActionPerform(HttpServletRequest request,
      HttpServletResponse response, Action action, ActionForm form,
      ActionMapping mapping) throws IOException, ServletException {

    /** @todo 修改,用于特殊处理BaseAction和BaseDispatchAction */
    // Log log = LogFactory.getLog(this.getClass());
    try {
      if (action instanceof BaseDispatchAction) {
        // 为BaseDispatchAction作特殊处理
      }
      if (action instanceof BaseAction) {
        // 为BaseAction作特殊处理
      }

      return (action.execute(mapping, form, request, response));
    } catch (Exception e) {
      return (processException(request, response, e, form, mapping));
    }
  }
}
