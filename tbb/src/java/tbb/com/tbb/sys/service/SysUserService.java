package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysUserDao;
import com.tbb.sys.domain.SysUser;
import com.tbb.tools.email.MailSenderInfo;
import com.tbb.tools.email.SimpleMailSender;

/**
 * SysUser Service
 */
public class SysUserService extends BaseService {
	private static SysUserService instance = new SysUserService();

	private SysUserService() {
		// empty
		// 防止直接创建对象
	}

	public static SysUserService getInstance() {
		return instance;
	}

	/**
	 * 创建SysUser对象
	 * 
	 * @param sysUser
	 * @throws Exception
	 */
	public void createSysUser(SysUser sysUser) throws Exception {
		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			dao.create(sysUser);
		} catch (Exception e) {
			throw new BaseException("创建新SysUser失败！", e);
		}
	}

	/**
	 * 根据SysUser主关键字获取SysUser信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public SysUser retrieveSysUser(java.lang.String domainPK) throws Exception {
		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取SysUser信息失败！", e);
		}
	}
	
	public SysUser retrieveSysUserBySid(java.lang.String session_id) throws Exception {
		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			return dao.retrieveBySid(session_id);
		} catch (Exception e) {
			throw new BaseException("获取SysUser信息失败！", e);
		}
	}

	/**
	 * 更新SysUser信息
	 * 
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	public int updateSysUser(SysUser sysUser) throws Exception {
		int effectRows = 0;

		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			effectRows = dao.update(sysUser);
		} catch (Exception e) {
			throw new BaseException("修改SysUser信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据SysUser主关键字删除SysUser
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteSysUser(java.lang.String domainPK) throws Exception {
		int effectRows = 0;

		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除SysUser失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询SysUser
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySysUserForList(Map params) throws Exception {
		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询SysUser失败!", e);
		}
	}
	
	public List querySysUserByEmail(Map params) throws Exception {
		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			return dao.querySysUserByEmail(params);
		} catch (Exception e) {
			throw new BaseException("查询SysUser失败!", e);
		}
	}

	/**
	 * 查询SysUser
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySysUserForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			SysUserDao dao = (SysUserDao) getDao(SysUserDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询SysUser失败!", e);
		}
	}
	
	public void sendRegisteMail(String email, String user_id){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("admin@beehii.com");
		mailInfo.setPassword("hust4400");// 您的邮箱密码
		mailInfo.setFromAddress("admin@beehii.com");
		mailInfo.setToAddress(email);
		mailInfo.setSubject("[beehii] email帐户激活");

		String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
			"<html>" +
		  "<head>" +
		  "  <title>[beehii] email帐户激活</title>" +
		  "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
		  "</head>" +
		  "<body>" +
		"	<p style='margin-top: 20px;margin-left: 20px;'>您好，<b>"+ email +"</b> ：<br/><br/>" +

		"欢迎加入<b>beehii</b>！请点击下面的链接来激活您的帐号。<br/><br/>" +

		"<a target='_blank' href='http://192.168.101.242:8080/tbb/sys/RegisteAction.do?method=activate&code="+user_id+"&email="+ email +"'>" +
		"http://192.168.101.242:8080/tbb/sys/RegisteAction.do?method=activate&code=" + user_id +
		"&email="+email+"</a><br/><br/>" +

		"如果您的邮箱不支持链接点击，请将以上链接地址复制到你的浏览器地址栏中认证。<br/><br/>" +

		"beehii致力于整理您的生活经历，为您提供便利。<br/><br/>" +

		"请来体验吧！<br/><br/>" +

		"</p>" +

		"  </body>" +
		"</html>";
		mailInfo.setContent(content);
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
	
}

