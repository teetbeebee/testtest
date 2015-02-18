package com.tbb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.util.StringUtils;
import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysUserService;

public class LoginAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{

		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		if (user_id == null || password == null)
		{
			throw new BaseException("用户非法!");
		}

		SysUser user = new SysUser();

		SysUserService us = SysUserService.getInstance();

		user = us.retrieveSysUser(user_id);

		if (user == null)
		{
			throw new BaseException("无效的用户");
		}

		if (!user.getPassword().equals(StringUtils.strChange(password)))
		{
			throw new BaseException("用户密码不符!");
		}

		if (user.getState().intValue() == 1)
		{
			throw new BaseException("用户被禁用!");
		}

		request.getSession(true).setAttribute("user", user);

		/*
         * UserLog userlog = new UserLog(); CommonService cs =
         * CommonService.getInstance();
         * userlog.setUser_log_sn(cs.getNextSequence("S_USER_LOG"));
         * userlog.setUser_id(user_id); userlog.setTerminal_id("");
         * userlog.setIs_manual(new Integer(0)); userlog.setLog_time(new
         * Date(System.currentTimeMillis())); userlog.setUser_log_state("0");
         * userlog.setNote(request.getRemoteAddr());
         * UserLogService.getInstance().createUserLog(userlog);
         */

		/*
         * List<Test> tests = (List<Test>)TestService.getInstance().queryTestForList(null);
         * if (tests != null) { System.out.println(tests.size()); } if
         * (isSimulate != null && !isSimulate.equals("")) { return
         * mapping.findForward("redoMnIndex"); }
         */
		return mapping.findForward("redoIndex");
	}
}
