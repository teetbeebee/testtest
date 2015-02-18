package com.tbb.vpn.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.tools.email.MailTool;
import com.tbb.vpn.dao.VpnuserDao;
import com.tbb.vpn.domain.Vpnuser;

/**
 * Vpnuser Service
 */
public class VpnuserService extends BaseService
{
	private static VpnuserService instance = new VpnuserService();
	
	public static int _notActive = 0;
	public static int _active = 1;
	public static int _forbidden = -1; 

	private VpnuserService()
	{
		// empty
		// 防止直接创建对象
	}

	public static VpnuserService getInstance()
	{
		return instance;
	}

	/**
	 * 创建Vpnuser对象
	 * 
	 * @param vpnuser
	 * @throws Exception
	 */
	public void createVpnuser(Vpnuser vpnuser) throws Exception
	{
		try
		{
			VpnuserDao dao = (VpnuserDao)getDao(VpnuserDao.class);
			dao.create(vpnuser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new BaseException("创建新Vpnuser失败！", e);
		}
	}

	/**
	 * 根据Vpnuser主关键字获取Vpnuser信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Vpnuser retrieveVpnuser(java.lang.String domainPK) throws Exception
	{
		try
		{
			VpnuserDao dao = (VpnuserDao)getDao(VpnuserDao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Vpnuser信息失败！", e);
		}
	}

	/**
	 * 更新Vpnuser信息
	 * 
	 * @param vpnuser
	 * @return
	 * @throws Exception
	 */
	public int updateVpnuser(Vpnuser vpnuser) throws Exception
	{
		int effectRows = 0;

		try
		{
			VpnuserDao dao = (VpnuserDao)getDao(VpnuserDao.class);
			effectRows = dao.update(vpnuser);
		}
		catch (Exception e)
		{
			throw new BaseException("修改Vpnuser信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Vpnuser主关键字删除Vpnuser
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteVpnuser(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			VpnuserDao dao = (VpnuserDao)getDao(VpnuserDao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除Vpnuser失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Vpnuser
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryVpnuserForList(Map params) throws Exception
	{
		try
		{
			VpnuserDao dao = (VpnuserDao)getDao(VpnuserDao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Vpnuser失败!", e);
		}
	}

	/**
	 * 查询Vpnuser
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryVpnuserForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
	{
		try
		{
			VpnuserDao dao = (VpnuserDao)getDao(VpnuserDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Vpnuser失败!", e);
		}
	}
	
	public void sendRegisteEmail(String userid, String receiveAddress){
		String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
				"<html>" +
			  "<head>" +
			  "  <title>[vpn] email帐户激活</title>" +
			  "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
			  "</head>" +
			  "<body>" +
			"	<p style='margin-top: 20px;margin-left: 20px;'>您好，<b>"+ receiveAddress +"</b> ：<br/><br/>" +

			"欢迎加入<b>beehii</b>！请点击下面的链接来激活您的帐号。<br/><br/>" +

			"<a target='_blank' href='http://localhost/tbb/sys/RegisteAction.do?method=activate&code="+userid+"&email="+ receiveAddress +"'>" +
			"http://localhost/tbb/sys/RegisteAction.do?method=activate&code=" + userid +
			"&email="+receiveAddress+"</a><br/><br/>" +

			"如果您的邮箱不支持链接点击，请将以上链接地址复制到你的浏览器地址栏中认证。<br/><br/>" +

			"请来体验吧！<br/><br/>" +

			"</p>" +

			"  </body>" +
			"</html>";
		MailTool.sendmail(receiveAddress, content);
	}

}

