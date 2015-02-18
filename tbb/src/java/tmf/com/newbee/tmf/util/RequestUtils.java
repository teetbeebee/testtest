package com.newbee.tmf.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.newbee.tmf.core.QueryParams;
import com.jspsmart.upload.Request;

public class RequestUtils {

	private RequestUtils() {
		// 防止创建实例
	}

	/**
	 * 设置url编码方式为
	 * 
	 * @param url
	 *            需要编码的url
	 * @param encode
	 *            编码(如:GBK)
	 * @return 编码后的url
	 */
	public static String encodeURL(String url, String encode) {
		try {
			return java.net.URLEncoder.encode(url, encode);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 从request对象中取值
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            变量名
	 * @param dft
	 *            如果key的值为null或空串,则返回该默认值
	 * @return key的值
	 */
	public static String getParameter(HttpServletRequest request, String key,
			String dft) {
		if (key == null) {
			return dft;
		}

		String value = request.getParameter(key);
		if (value == null || value.equals("")) {
			return dft;
		}

		return value.trim();
	}

	/**
	 * 从request中拷贝参数到map中. 注意:对于数组参数，只拷贝了第1个元素
	 * 
	 * @param request
	 *            request对象
	 * @param map
	 *            存放request对象中参数值的map
	 * @return 实际拷贝的参数个数
	 */
	public static int getParameter(HttpServletRequest request,
			Map<String, String> map) {
		int cnt = 0;
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();

			String value = request.getParameter(key);
			// 对于全空格的数据，仍然保留，在保存修改时可能需要
			if ((value != null)) {
				map.put(key, value.trim());
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 从upload request中拷贝参数到map中. 注意:对于数组参数，只拷贝了第1个元素
	 * 
	 * @param request
	 *            request对象
	 * @param map
	 *            存放request对象中参数值的map
	 * @return 实际拷贝的参数个数
	 */
	public static int getParameterUpload(Request request,
			Map<String, String> map) {
		int cnt = 0;
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();

			String value = request.getParameter(key);
			// 对于全空格的数据，仍然保留，在保存修改时可能需要
			if ((value != null)) {
				map.put(key, value.trim());
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 从request中拷贝参数到map中,如果参数值为空串,并且dftValues中有该参数的值,则用此值代替.
	 * 注意:对于数组参数，只拷贝了第1个元素
	 * 
	 * @param request
	 *            request对象
	 * @param map
	 *            存放参数值
	 * @param dftValues
	 *            存放后备的默认值
	 * @return 实际拷贝的参数个数
	 */
	public static int getParameter(HttpServletRequest request,
			Map<String, String> map, Map<String, String> dftValues) {
		int cnt = 0;
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();

			String value = request.getParameter(key);
			if (value == null) {
				continue;
			}

			// 对于全空格的数据，如果在dftValues中有定义，则使用dftValues中对应的值替换
			if (value.trim().equals("") && dftValues.containsKey(key)) {
				map.put(key, dftValues.get(key));
			} else {
				map.put(key, value.trim());
			}
			cnt++;
		}
		return cnt;
	}

	/**
	 * 根据keys指定参数，从request中拷贝参数到map中. 注意:对于数组参数，只拷贝了第1个元素
	 * 
	 * @param request
	 *            request对象
	 * @param map
	 *            存放参数值
	 * @param keys
	 *            需要拷贝的参数数组
	 * @return 实际拷贝的参数个数
	 */
	public static int getParameter(HttpServletRequest request,
			Map<String, String> map, String[] keys) {
		int cnt = 0;
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			if (key == null) {
				continue;
			}

			String value = request.getParameter(key);
			// 对于全空格的数据，仍然保留，在保存修改时可能需要
			if ((value != null)) {
				map.put(key, value.trim());
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 将request对象中参数的值转换为int型
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            参数名
	 * @param dft
	 *            默认值
	 * @return request中参数key的值
	 */
	public static int getParameterAsInt(ServletRequest request, String key,
			int dft) {
		String s = request.getParameter(key);
		if (s == null || s.equals("")) {
			return dft;
		}

		s = s.trim();
		if (s.equals("")) {
			return dft;
		}

		try {
			return Integer.parseInt(s);
		} catch (Exception ex) {
			return dft;
		}
	}

	/**
	 * 将request对象中参数的值转换为Integer型
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            参数名
	 * @param dft
	 *            默认值
	 * @return request中参数key的值
	 */
	public static Integer getParameterAsInteger(ServletRequest request,
			String key, int dft) {
		String s = request.getParameter(key);
		Integer integer = new Integer(dft);
		if (s == null || s.equals("")) {
			return integer;
		}

		s = s.trim();
		if (s.equals("")) {
			return integer;
		}

		try {
			return new Integer(s);
		} catch (Exception ex) {
			return integer;
		}
	}

	/**
	 * 将request对象中参数的值转换为Double型
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            参数名
	 * @param dft
	 *            默认值
	 * @return request中参数key的值
	 */
	public static Double getParameterAsDouble(ServletRequest request,
			String key, Double dft) {
		String s = request.getParameter(key);
		if (s == null || s.equals("")) {
			return dft;
		}

		s = s.trim();
		if (s.equals("")) {
			return dft;
		}

		try {
			return new Double(s);
		} catch (Exception ex) {
			return dft;
		}
	}

	/**
	 * 将request对象中参数的值转换为Date型
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            参数名
	 * @param dft
	 *            默认值
	 * @return request中参数key的值
	 */
	public static java.util.Date getParameterAsDate(ServletRequest request,
			String key, java.util.Date dft) {

		String s = request.getParameter(key);
		if (s == null || s.equals(""))
			return dft;

		s = s.trim();
		if (s.equals(""))
			return dft;

		DateFormat df = DateFormat.getDateInstance();
		try {
			return df.parse(s);
		} catch (Exception ex) {
			return dft;
		}

	}

	/**
	 * 将request对象中特定时间格式的格式参数的值转换为格式Date型
	 * 
	 * @param request
	 *            request对象
	 * @param key
	 *            参数名
	 * @param dft
	 *            默认值
	 * @return request中参数key的值
	 */
	public static java.util.Date getParameterAsDate(ServletRequest request,
			String key, String format, java.util.Date dft) {

		String s = request.getParameter(key);
		if (s == null || s.equals(""))
			return dft;

		s = s.trim();
		if (s.equals("")) {
			return dft;
		}

		return DateUtils.parseToDate(s, format, dft);
	}

	/**
	 * 将request对象中的数据以字符串的形式表示
	 * 
	 * @param request
	 * @return request对象中数据的字符串表示
	 * @throws Exception
	 */
	public static String requestInfo(ServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb
				.append("\r\n ------------------------ Debug info start -------------------------------- \r\n");

		Enumeration names = request.getAttributeNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			Object valueObj = request.getAttribute(key);

			String value = "";
			String valueClass = valueObj.getClass().getName();
			if (valueClass.indexOf("domain") != -1) {
				StringBuffer buffer = new StringBuffer();
				Map valueMap = BeanUtils.describe(valueObj);
				buffer.append(valueMap.toString());
				buffer.append("\r\n");
				value = buffer.toString();
			} else if (valueObj instanceof QueryParams) {
				QueryParams query = (QueryParams) valueObj;
				Map queryMap = query.getQueryMap();
				Map parameterMap = query.getParameterMap();
				value = "Query map:" + queryMap.toString() + "\r\n"
						+ "Parameter map:" + parameterMap.toString();
			} else {
				value = valueObj.toString();
			}

			sb.append("【" + key + "】：[" + valueObj.getClass() + "]\r\n");
			sb.append(value);
			sb.append("\r\n\r\n");
		}
		sb
				.append("\r\n ------------------------ Debug info end. --------------------------------");

		return sb.toString();
	}

}
