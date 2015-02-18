package com.newbee.tmf.core;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

public class DaoPlugin implements PlugIn {

	/**
	 * Commons Logging instance.
	 */
	protected static Log log = LogFactory.getLog(DaoPlugin.class);

	private String dao = "/res/config/dao-config.xml";

	private String datasource = "";

	private boolean defaultsource = false;

	public void destroy() {
	}

	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws ServletException {

		try {
			// 根据配置文件初始化DaoConfig
			System.out.println("根据配置文件初始化DaoConfig");
			DaoConfig.init(datasource, dao, defaultsource);
		} catch (Exception ex) {
			log.error("init dao config error", ex);
			throw new ServletException("init dao error", ex);
		}
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getDao() {
		return dao;
	}

	public boolean isDefaultsource() {
		return defaultsource;
	}

	public void setDefaultsource(boolean defaultsource) {
		this.defaultsource = defaultsource;
	}
}
