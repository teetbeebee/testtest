package com.newbee.tmf.core;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.newbee.tmf.config.Config;
import com.tbb.manage.SysDictManage;
import com.tbb.manage.SysUserManage;

/**
 * 功能描述：plugin类，初始化程序 主要用来初始化一些工作路径和工程参数
 */
public class ConfigPlugin implements PlugIn
{

	// 取得log对象
	private static Log log = LogFactory.getLog(ConfigPlugin.class);

	public void destroy()
	{
	// empty

	}

	/*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     */
	/*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     */
	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws ServletException
	{
		// 初始化工程路径
		try
		{
			initPath(actionServlet, moduleConfig);
		}
		catch (Exception ex1)
		{
			log.error("初始化工程路径失败!");
			throw new ServletException("初始化工程路径失败:", ex1);
		}
		// 初始化参数
		try
		{
			initParameter(actionServlet, moduleConfig);
		}
		catch (Exception ex2)
		{
			log.error("初始化参数失败!");
			throw new ServletException("初始化参数失败:", ex2);
		}
		// 初始化系统配置
		try
		{
			initConfig(actionServlet, moduleConfig);
		}
		catch (Exception ex2)
		{
			ex2.printStackTrace();
			log.error("初始化参数失败!");
			throw new ServletException("初始化参数失败:", ex2);
		}

		// 初始化系统用户列表
		try
		{
			initSysUser(actionServlet, moduleConfig);
		}
		catch (Exception ex2)
		{
			log.error("初始化系统用户列表失败!");
			throw new ServletException("初始化系统用户列表失败:", ex2);
		}

	}

	/*
     * 初始化路径
     */
	public void initPath(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws Exception
	{
		String base = actionServlet.getServletContext().getRealPath("/");
		base = base.replace(File.separatorChar, '/');
		if (base.endsWith("/"))
		{
			base = base.substring(0, base.length() - 1);
		}
		Config.ROOT = base;
		if (log.isInfoEnabled())
		{
			log.info("app_dir is:" + Config.ROOT);
		}
	}

	// 初始化参数
	@SuppressWarnings ("unchecked")
	public void initParameter(ActionServlet actionServlet,
			ModuleConfig moduleConfig) throws Exception
	{

	}

	// 初始化系统字典列表
	@SuppressWarnings ("unchecked")
	public void initSysDict(ActionServlet actionServlet,
			ModuleConfig moduleConfig) throws Exception
	{

		// 获取系统字典表，放入application
		ServletContext application = actionServlet.getServletContext();
		application.setAttribute("sysDictList", SysDictManage.getInstance()
				.getSysDictList());
	}

	// 初始化系统用户列表
	@SuppressWarnings ("unchecked")
	public void initSysUser(ActionServlet actionServlet,
			ModuleConfig moduleConfig) throws Exception
	{

		// 获取系统用户表，放入application
		ServletContext application = actionServlet.getServletContext();
		application.setAttribute("sysUserList", SysUserManage.getInstance()
				.getSysUserList());
	}
	
	// 初始化配置
	@SuppressWarnings ("unchecked")
	public void initConfig(ActionServlet actionServlet,
			ModuleConfig moduleConfig) throws Exception
	{
		ServletContext application = actionServlet.getServletContext();
		application.setAttribute("config", Config.getInstance());
	}
}
