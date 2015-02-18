package com.newbee.tmf.core;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.newbee.tmf.util.StringUtils;
import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;

public class DaoConfig {

	private static DaoManager daoManager = null;

	private static Map<String, DaoManager> daoManagerMap = null;

	private static String defaultDbsource;
	
	private static Log log = LogFactory.getLog(DaoConfig.class);

	public static void init(String datasource, String dao_url,
			boolean defaultsource) throws Exception {
		if (daoManagerMap == null) {
			daoManagerMap = new HashMap<String, DaoManager>();
		}
		try {
			String resource = dao_url;
			log.info("Using SIMPLE configuration. (" + resource + ")");
			Reader reader = Resources.getResourceAsReader(resource);
			daoManager = DaoManagerBuilder.buildDaoManager(reader);
			daoManagerMap.put(datasource, daoManager);
			if (defaultsource) {
				defaultDbsource = datasource;
			}
		} catch (Exception e) {
			log.error("Could not init DaoConfig", e);
			System.out.println(e.getMessage());
			throw new RuntimeException(
					"Could not initialize DaoConfig.  Cause: " + e, e);
		}
	}

	public static DaoManager getDaoManager() {
		if (daoManager == null && defaultDbsource == null) {
			throw new RuntimeException("DaoManager has not init yet !");
		}
		return daoManagerMap.get(defaultDbsource);
	}

	public static DaoManager getDaoManager(String sourename) {
		if (daoManager == null) {
			throw new RuntimeException("DaoManager has not init yet !");
		}
		if (sourename == null || !StringUtils.isValid(sourename)
				|| !daoManagerMap.containsKey(sourename)) {
			return getDaoManager();
		} else {
			return daoManagerMap.get(sourename);
		}
	}
}
