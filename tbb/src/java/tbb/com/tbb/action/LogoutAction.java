package com.tbb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tbb.sys.domain.SysUser;

public class LogoutAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 取操作人账号
		SysUser user = (SysUser) request.getSession(true).getAttribute("user");
		// 判断是否为模拟训练注销
		String isSimulate = request.getParameter("isSimulate");
		/*
		 * if (user != null) { UserLog userlog = new UserLog(); CommonService cs =
		 * CommonService.getInstance();
		 * userlog.setUser_log_sn(cs.getNextSequence("S_USER_LOG"));
		 * userlog.setUser_id(user.getUser_id());
		 * 
		 * userlog.setTerminal_id(""); userlog.setIs_manual(new Integer(0));
		 * userlog.setLog_time(new Date(System.currentTimeMillis()));
		 * userlog.setUser_log_state("1");
		 * userlog.setNote(request.getRemoteAddr());
		 * UserLogService.getInstance().createUserLog(userlog); }
		 */

		HttpSession session = request.getSession();
		if (session != null) {
			session.setAttribute("user", null);
			session.invalidate();
		}

		if (isSimulate != null && !isSimulate.equals("")) {
			return mapping.findForward("imiation");
		}
		return mapping.findForward("login");
	}
}
