package com.newbee.tmf.util;

import javax.servlet.http.HttpServletRequest;

import com.newbee.tmf.config.Config;

/**
 * 运行时配置等信息调用
 * 
 */
public class RuntimeUtils
{
	public static Config getConfig(HttpServletRequest request){
		return (Config) request.getSession().getServletContext().getAttribute("config");
	}
	
	/* 返回系统配置信息，如果没找到匹配项，则返回空字符串，而不是null */
	public static String getSysParam(HttpServletRequest request, String paramname){
		return RuntimeUtils.getConfig(request).getParam(paramname);
	}
}
