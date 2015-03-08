package com.newbee.tmf.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.fileupload.FileItem;
import org.xmlBlaster.util.Timestamp;

import com.newbee.tmf.util.converters.ClassConverter;

public class ValueUtils {

	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	private ValueUtils() {

	}

	/**
	 * 将长度为0或只包含空字符的字符串转换成null
	 * 
	 * @param str
	 *            String
	 */
	public static String emptyStringToNull(String str) {
		if (str == null) {
			return null;
		}
		if (str.trim().length() == 0) {
			return null;
		}
		return str;
	}

	/**
	 * 对map的每个类型为String的值进行emptyStringToNull转换
	 * 
	 * @param stringMap
	 *            HashMap
	 */
	public static Map emptyStringToNull(Map stringMap) {
		if (stringMap == null) {
			return null;
		}
		Set keys = stringMap.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = stringMap.get(key);
			if (!(value instanceof String)) {
				continue;
			}
			String s = emptyStringToNull((String) value);
			stringMap.put(key, s);
		}
		return stringMap;
	}

	/**
	 * 对List中的每个Map进行emptyStringToNull转换
	 * 
	 * @param stringMapList
	 *            List
	 */
	public static List emptyStringToNull(List stringMapList) {
		if (stringMapList == null) {
			return null;
		}
		for (int i = 0; i < stringMapList.size(); i++) {
			Object obj = stringMapList.get(i);
			if (!(obj instanceof Map)) {
				continue;
			}
			emptyStringToNull((Map) obj);
		}
		return stringMapList;
	}

	/**
	 * 将null转换成长度为0的字符串
	 * 
	 * @param str
	 *            String
	 */
	public static String nullStringToEmptyString(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 对map的每个类型为String的值进行makeVacant转换
	 * 
	 * @param str
	 *            String
	 */
	public static Map nullStringToEmptyString(Map stringMap) {
		if (stringMap == null) {
			return null;
		}
		Set keys = stringMap.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = stringMap.get(key);
			if (!(value instanceof String)) {
				continue;
			}
			String s = nullStringToEmptyString((String) value);
			stringMap.put(key, s);
		}
		return stringMap;
	}

	/**
	 * 对List中的每个Map进行nullToEmptyString转换
	 * 
	 * @param str
	 *            String
	 */
	public static List nullStringToEmptyString(List stringMapList) {
		if (stringMapList == null) {
			return null;
		}
		for (int i = 0; i < stringMapList.size(); i++) {
			Object obj = stringMapList.get(i);
			if (!(obj instanceof Map)) {
				continue;
			}
			nullStringToEmptyString((Map) obj);
		}
		return stringMapList;
	}

	/**
	 * 对Map中的Date类型数据转换成要求格式的日期字符串
	 * 
	 * @param stringMap
	 *            HashMap
	 * @param date_format
	 *            日期格式(DATE_FORMAT,DATETIME_FORMAT)
	 */
	public static Map dateToString(Map stringMap, String date_format) {
		if (stringMap == null) {
			return null;
		}
		Set keys = stringMap.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = stringMap.get(key);
			if (!(value instanceof Date)) {
				continue;
			}
			stringMap.put(key, toString((Date) value, date_format));
		}
		return stringMap;
	}

	/**
	 * 对List中的每个Map中的Date类型数据转换成要求格式的日期字符串
	 * 
	 * @param stringMapList
	 *            List
	 * @param date_format
	 *            日期格式(DATE_FORMAT,DATETIME_FORMAT)
	 */
	public static List dateToString(List stringMapList, String date_format) {
		if (stringMapList == null) {
			return null;
		}
		Iterator iter = stringMapList.iterator();
		while (iter.hasNext()) {
			Object obj = (Object) iter.next();
			if (obj instanceof Map) {
				dateToString((Map) obj, date_format);
			}
		}
		return stringMapList;
	}

	/**
	 * 对Map中的Money类型数据转换成指定位数的字符串
	 * 
	 * @return stringMap Hashmap
	 * @param bit
	 *            小数的位数
	 */
	public static Map moneyToString(Map stringMap, int bit) {
		if (stringMap == null) {
			return null;
		}
		Set keys = stringMap.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = stringMap.get(key);
			if (!(value instanceof java.math.BigDecimal)) {
				continue;
			}
			String strMoney = String.valueOf(value);
			stringMap.put(key, strMoney.substring(0, strMoney.lastIndexOf(".")
					+ bit + 1));
		}
		return stringMap;
	}

	/**
	 * 对List中的每个Map中的Money类型数据转换成指定位数的字符串
	 * 
	 * @return stringMapList List
	 * @param bit
	 *            小数的位数
	 */
	public static List moneyToString(List stringMapList, int bit) {
		if (stringMapList == null) {
			return null;
		}
		Iterator iter = stringMapList.iterator();
		while (iter.hasNext()) {
			Object obj = (Object) iter.next();
			if (obj instanceof Map) {
				moneyToString((Map) obj, bit);
			}
		}
		return stringMapList;
	}

	/**
	 * 以字符串形式输出map中的对象
	 * 
	 * @param map
	 *            Map
	 * @return String
	 */
	public static String toString(Map map) {
		if (null == map) {
			return "null";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("[The map value begin]" + "\r\n");
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry item = (Map.Entry) iter.next();
			sb.append(item.getKey() + " = " + toString(item.getValue()));
			sb.append("\r\n");
		}
		sb.append("[The map value end]" + "\r\n");

		return sb.toString();
	}

	/**
	 * 以字符串形式输出list中的所有对象
	 * 
	 * @param list
	 *            List
	 * @return String
	 */
	public static String toString(List list) {
		if (null == list) {
			return "null";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("[The list value begin]" + "\r\n");
		Iterator iter = list.iterator();
		int i = 0;
		while (iter.hasNext()) {
			Object item = (Object) iter.next();
			sb.append("list[" + i + "] = " + toString(item));
			i++;
		}
		sb.append("[The list value end]" + "\r\n");

		return sb.toString();
	}

	/**
	 * 将符合java.sql.Date或java.sql.Timestamp的时间格式的字符串 转换成相应的java.util.Date对象
	 * 处理过程：如果str是有效格式的Date，用DATE_FORMAT进行格式化； 否则用DATETIME_FORMAT进行格式化
	 * 
	 * @param str
	 *            String
	 * @throws Exception
	 * @return Date
	 */
	public static java.util.Date toDate(String str) throws Exception {
		if (str == null) {
			return null;
		}
		str = str.trim();
		java.text.SimpleDateFormat sdfmt = new java.text.SimpleDateFormat(
				DATE_FORMAT);
		try {
			return sdfmt.parse(str);
		} catch (Exception e) {
			sdfmt = new java.text.SimpleDateFormat(DATETIME_FORMAT);
			return sdfmt.parse(str);
		}
	}

	/**
	 * 将字符串型日期转换成指定格式的java.util.Date类型
	 * 
	 * @param str
	 *            String
	 * @param format
	 *            日期格式(DATE_FORMAT,TIMESTAMP_FORMAT)
	 * @throws Exception
	 * @return Date
	 */
	public static java.util.Date toDate(String str, String format)
			throws Exception {
		java.text.SimpleDateFormat sdfmt = new java.text.SimpleDateFormat(
				format);
		java.util.Date date = sdfmt.parse(str);
		return date;
	}

	/**
	 * 将java.util.Date类型的日期转换成指定格式的字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(java.util.Date date, String format) {
		java.text.SimpleDateFormat sdfmt = new java.text.SimpleDateFormat(
				format);
		String s = sdfmt.format(date);
		sdfmt = null;
		return s;
	}

	public static String toString(java.util.Date date) {
		return toString(date, DATE_FORMAT);
	}

	/**
	 * 返回当前日期的DATE_FORMAT格式的字符串
	 * 
	 * @return dateString
	 */
	public static String getDate() {
		java.util.Date now = new java.util.Date();
		String dateString = toString(now, DATE_FORMAT);
		return dateString;
	}

	/**
	 * 返回当前日期的DATETIME_FORMAT格式的字符串
	 * 
	 * @return dateString
	 */
	public static String getDateTime() {
		java.util.Date now = new java.util.Date();
		String dateString = toString(now, DATETIME_FORMAT);
		return dateString;
	}

	/**
	 * 返回当前日期的年份
	 * 
	 * @return year int
	 */
	public static int getYear() {
		java.util.Date now = new java.util.Date();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 返回指定日期的年份
	 * 
	 * @return year int
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 返回当前日期的月份
	 * 
	 * @return month int
	 */
	public static int getMonth() {
		java.util.Date now = new java.util.Date();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(now);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 返回指定日期的月份
	 * 
	 * @return month int
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 返回当前日期的天
	 * 
	 * @return month int
	 */
	public static int getDay() {
		java.util.Date now = new java.util.Date();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(now);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 以字符串形式输出对象
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String toString(Object obj) {
		if (obj == null) {
			return null;
		}

		if (obj instanceof String[]) {
			String values[] = (String[]) obj;
			StringBuffer sb = new StringBuffer();
			sb.append('[');
			for (int i = 0; i < values.length; i++) {
				if (i > 0) {
					sb.append(',');
				}
				sb.append(values[i]);
			}
			sb.append(']');
			return sb.toString();
		}

		if (obj instanceof List) {
			return toString((List) obj);
		}

		if (obj instanceof Map) {
			return toString((Map) obj);
		}

		return obj.toString();
	}

	/**
	 * 取Map中数据类型为int的值,如果没有则返回默认值dft
	 * 
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(Map map, String key, int defaultValue) {
		Object obj = map.get(key);
		if (obj == null) {
			return defaultValue;
		}

		if (obj instanceof Integer) {
			return ((Integer) obj).intValue();
		}

		if (obj instanceof String) {
			int v = defaultValue;
			try {
				v = Integer.parseInt((String) obj);
				return v;
			} catch (NumberFormatException ex) {
				return defaultValue;
			}
		}

		return defaultValue;
	}

	// 以下是关于对Map赋值相关的一些操作

	/**
	 * 将一个bean的可读属性转化为一个Map
	 * 
	 * @param bean
	 *            Object
	 * @return Map
	 */
	public static Map describe(Object bean) {
		try {
			return BeanUtils.describe(bean);
		} catch (Exception ex) {
			ex.printStackTrace();
			// 返回空的HashMap
			return new HashMap();
		}
	}

	/**
	 * 从map中取出值复制到bean中
	 * 
	 * @param bean
	 *            需要设置属性的目标bean
	 * @param map
	 *            提供属性值的源map
	 * @return 实际设置的属性个数
	 * @throws Exception
	 *             设置属性出错
	 */
	public static int populate(Object bean, Map map) throws Exception {

		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (map == null)) {
			return cnt;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = map.keySet().iterator();
		while (names.hasNext()) {

			// Identify the property name and value(s) to be assigned
			String name = (String) names.next();
			if (name == null) {
				continue;
			}
			Field field;
			try {
				field = bean.getClass().getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				continue;
			}
			Type type = field.getType();
			Object value = null;
			String tempvalue = null;
			tempvalue = (String) map.get(name);

			if (tempvalue == null || tempvalue.equalsIgnoreCase("")) {
				continue;
			}
			if (type.toString().equalsIgnoreCase(
					"int")) {
				value = Integer.valueOf(tempvalue);
			} else
			if (type.toString().equalsIgnoreCase(
					"long")) {
				value = Long.valueOf(tempvalue);
			} else
			if (type.toString().equalsIgnoreCase(
					"double")) {
				value = Double.valueOf(tempvalue);
			} else
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.String")) {
				value = tempvalue;
			} else
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Integer")) {
				value = Integer.valueOf(tempvalue);
			} else
			if (type.toString().substring(6).equalsIgnoreCase("java.util.Date")) {
				value = tempvalue;
			} else
			if (type.toString().substring(6)
					.equalsIgnoreCase("java.lang.Float")) {
				value = new Float(tempvalue);
			} else
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Double")) {
				value = new Double(tempvalue);
			} else
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Boolean")) {
				value = new Boolean(tempvalue);
			}else
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Long")) {
				value = new Long(tempvalue);
			} else
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.math.BigDecimal")) {
				value = new BigDecimal(tempvalue);
			} else
				if (type.toString().substring(6).equalsIgnoreCase(
						"java.sql.Timestamp")) {
				value = Timestamp.valueOf(tempvalue);
			}

			// Perform the assignment for this property
			setProperty(bean, name, value);
			cnt++;
		}
		return cnt;
	}

	/**
	 * 根据指定的keys从map中取值复制到bean中
	 * 
	 * @param bean
	 * @param map
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public static int populate(Object bean, Map map, String[] keys)
			throws Exception {

		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (map == null) || keys == null) {
			return cnt;
		}

		for (int i = 0; i < keys.length; i++) {
			String name = keys[i];
			if (name == null) {
				continue;
			}

			// 如果map中没有指定的key，忽略之
			if (!map.containsKey(name)) {
				continue;
			}

			Object value = map.get(name);

			// Perform the assignment for this property
			setProperty(bean, name, value);
			cnt++;
		}
		return cnt;
	}

	/**
	 * 从map中复制值到bean中，并支持map和bean的属性映射, 映射格式为 (map-key , bean-prop)
	 * 
	 * @param bean
	 * @param map
	 * @param keyMapping
	 * @return
	 * @throws Exception
	 */
	public static int populate(Object bean, Map map, Map keyMapping)
			throws Exception {

		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (map == null)) {
			return cnt;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = map.keySet().iterator();
		while (names.hasNext()) {

			// Identify the property name and value(s) to be assigned
			String map_key = (String) names.next();
			if (map_key == null) {
				continue;
			}
			Object value = map.get(map_key);
			String bean_prop = (String) keyMapping.get(map_key);
			if (bean_prop == null) {
				bean_prop = map_key;
			}
			// Perform the assignment for this property
			setProperty(bean, bean_prop, value);
			cnt++;
		}
		return cnt;
	}

	/**
	 * 根据指定的keys从map中取值复制到bean中,并支持map和bean的属性映射, 映射格式为 (map-key , bean-prop)
	 * 
	 * @param bean
	 * @param map
	 * @param keys
	 * @param keyMapping
	 * @return
	 * @throws Exception
	 */
	public static int populate(Object bean, Map map, String[] keys,
			Map keyMapping) throws Exception {

		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (map == null) || keys == null) {
			return cnt;
		}

		for (int i = 0; i < keys.length; i++) {
			String map_key = keys[i];
			if (map_key == null) {
				continue;
			}

			// 如果map中没有指定的key，忽略之
			if (!map.containsKey(map_key)) {
				continue;
			}

			Object value = map.get(map_key);

			String bean_prop = (String) keyMapping.get(map_key);
			if (bean_prop == null) {
				bean_prop = map_key;
			}
			// Perform the assignment for this property
			setProperty(bean, bean_prop, value);
		}
		return cnt;
	}
	
	/**
	 * 从map中取出值复制到bean中
	 * 
	 * @param bean
	 *            需要设置属性的目标bean
	 * @param map
	 *            提供属性值的源map
	 * @return 实际设置的属性个数
	 * @throws Exception
	 *             设置属性出错
	 */
	public static int populate(Object bean, Iterator it, String codetype) throws Exception {

		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (it == null)) {
			return cnt;
		}
		
		// Loop through the property name/value pairs to be set
		
		while (it.hasNext()) {

			// Identify the property name and value(s) to be assigned
			FileItem item = (FileItem) it.next();
			String name = item.getFieldName();
			if (name == null) {
				continue;
			}
			Field field;
			try {
				field = bean.getClass().getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				continue;
			}
			Type type = field.getType();
			Object value = null;
			String tempvalue = null;
			tempvalue = item.getString("UTF-8");

			// if (tempvalue.equalsIgnoreCase("") || tempvalue==null)
			// {
			// continue;
			// }
			//  如果是字符串修改为空的话则仍然赋值
			
			if ((tempvalue == null || tempvalue.equalsIgnoreCase("")) && !type.toString().substring(6).equalsIgnoreCase(
			"java.lang.String")) {
				continue;
			}
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.String")) {
				value = tempvalue;
			}
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Integer")) {
				value = Integer.valueOf(tempvalue);
			}
			if (type.toString().substring(6).equalsIgnoreCase("java.util.Date")) {
				value = tempvalue;
			}
			if (type.toString().substring(6)
					.equalsIgnoreCase("java.lang.Float")) {
				value = new Float(tempvalue);
			}
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Double")) {
				value = new Double(tempvalue);
			}
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.lang.Boolean")) {
				value = new Boolean(tempvalue);
			}
			if (type.toString().substring(6).equalsIgnoreCase(
					"java.math.BigDecimal")) {
				value = new BigDecimal(tempvalue);
			}

			// Perform the assignment for this property
			setProperty(bean, name, value);
			cnt++;
		}
		return cnt;
	}

	/**
	 * 向bean的指定属性写入给定的值，可以自动完成类型转换, 特殊类型需要向ConvertUtils注册转换器Conveter,
	 * 目前使用的commons组件的BeanUtils的方法
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public static void setProperty(Object bean, String name, Object value)
			throws Exception {
		try {
			BeanUtils.copyProperty(bean, name, value);
		} catch (Exception e) {
			throw new Exception("设置属性出错, 值: " + value.toString() + " 无法设置给属性: "
					+ name + ", 原因:\r\n" + e, e);
		}
	}

	/**
	 * 封装了ConvertUtils的register方法，用于下面static部分的初始注册
	 * 
	 * @param clazz
	 * @param converter
	 */
	public static void registerConverter(Class clazz, Converter converter) {
		ConvertUtils.register(converter, clazz);
	}

	static {
		// 以下代码 copy from ConvertUtils 类，再修改而来

		boolean booleanArray[] = new boolean[0];
		byte byteArray[] = new byte[0];
		char charArray[] = new char[0];
		double doubleArray[] = new double[0];
		float floatArray[] = new float[0];
		int intArray[] = new int[0];
		long longArray[] = new long[0];
		short shortArray[] = new short[0];
		String stringArray[] = new String[0];

		// TODO date类型转换时，2005-02-30类型的无效日期将转化为2005-03-02，而不会抛异常
		// 考虑以后使用自己的converter来替代
	}

	// 以下是向Map中复制数据的一组方法

	public static void addValue(Map dst, Map src) {
		if (dst == null) {
			return;
		}
		if (src == null) {
			return;
		}

		Iterator iter = src.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			dst.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 从一个map中复制所有的数据到目标map中
	 * 
	 * @param to
	 *            Map
	 * @param from
	 *            Map
	 * @return int
	 */
	public static int copyValue(Map to, Map from) {
		Object[] fields = from.keySet().toArray();
		if (fields == null) {
			return 0;
		}

		int cnt = 0;
		int size = fields.length;

		for (int i = 0; i < size; i++) {
			Object key = fields[i];
			Object obj = from.get(key);
			if (obj != null) {
				to.put(key, obj);
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 从一个map中将指定的一批key对应的值复制到目标map中
	 * 
	 * @param to
	 *            Map
	 * @param from
	 *            Map
	 * @param fields
	 *            String[]
	 * @return int
	 */
	public static int copyValue(Map to, Map from, String[] fields) {
		// 参考copyValue(Map from, Map to, Object[] fields) 实现
		if (fields == null) {
			return 0;
		}

		int cnt = 0;
		int size = fields.length;

		for (int i = 0; i < size; i++) {
			Object key = fields[i];
			Object obj = from.get(key);
			if (key != null) {
				to.put(key, obj);
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 从from复制数据到to中， 将fromFields中健确定的值赋值到toFields对应的健中
	 * 
	 * @param to
	 *            Map
	 * @param toFields
	 *            Object[]]
	 * @param from
	 *            Map
	 * @param fromFields
	 *            Object[]
	 * @return int
	 */
	public static int copyValue(Map to, Object[] toFields, Map from,
			Object[] fromFields) {
		// 参考copyValue(Map from, Map to) 实现
		if (fromFields == null || toFields == null) {
			return 0;
		}

		int cnt = 0;
		int size = fromFields.length;
		if (size > toFields.length) {
			size = toFields.length;
		}

		for (int i = 0; i < size; i++) {
			Object from_key = fromFields[i];
			Object to_key = toFields[i];
			Object obj = from.get(from_key);
			if (from_key != null && to_key != null) {
				to.put(to_key, obj);
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 从一个bean中读取所有可读属性到指定目标map中
	 * 
	 * @param to
	 *            Map
	 * @param from
	 *            Object
	 */
	public static void copyValue(Map to, Object from) {
		/** @todo 可以优化一下 */
		Map map = describe(from);
		copyValue(to, map);
	}

	// 下面是一组从Map中取值设置到其他位置的方法

	public static int setValue(Object bean, Map map, Map dftValues)
			throws Exception {
		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (map == null)) {
			return cnt;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = map.keySet().iterator();
		while (names.hasNext()) {

			// Identify the property name and value(s) to be assigned
			String name = (String) names.next();
			if (name == null) {
				continue;
			}
			Object value = map.get(name);

			// Perform the assignment for this property
			try {
				setProperty(bean, name, value);
			} catch (Exception e) {
				// 从dft中取值
				if (dftValues.containsKey(name)) {
					setProperty(bean, name, dftValues.get(name));
				} else {
					throw e;
				}
			}

			cnt++;
		}
		return cnt;
	}

	public static int setValue(Object bean, Map map, String[] keys,
			Map dftValues) throws Exception {
		int cnt = 0;

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (map == null) || keys == null) {
			return cnt;
		}

		for (int i = 0; i < keys.length; i++) {
			String name = keys[i];
			if (name == null) {
				continue;
			}

			// 如果map中没有指定的key，忽略之
			if (!map.containsKey(name)) {
				continue;
			}

			Object value = map.get(name);

			// Perform the assignment for this property
			try {
				setProperty(bean, name, value);
			} catch (Exception e) {
				// 从dft中取值
				if (dftValues.containsKey(name)) {
					setProperty(bean, name, dftValues.get(name));
				} else {
					throw e;
				}
			}
		}
		return cnt;
	}

	public static String toUrlParamter(Map map) {
		StringBuffer sb = new StringBuffer();
		Iterator iter = map.entrySet().iterator();
		boolean isFirst = true;
		while (iter.hasNext()) {
			if (!isFirst) {
				sb.append("&");
			}
			Map.Entry entry = (Map.Entry) iter.next();
			sb.append(entry.getKey());
			sb.append("=");

			/** @todo 应处理特殊字符的转化 */
			String value = entry.getValue().toString();
			sb.append(value);

			isFirst = false;
		}
		return sb.toString();
	}

	/**
	 * 为某个字符串添加前缀，如果已经有该前缀，则不添加
	 * 
	 * @param value
	 *            String
	 * @param prefix
	 *            String
	 * @return String
	 */
	public static String addPrefix(String value, String prefix) {
		if (value == null) {
			return null;
		}

		if (prefix == null) {
			return value;
		}

		if (!value.startsWith(prefix)) {
			value = prefix + value;
		}
		return value;
	}

	/**
	 * 为Map中指定的部分的字符串添加前缀
	 * 
	 * @param map
	 *            Map
	 * @param prefix
	 *            String
	 * @param keys
	 *            String[]
	 * @return Map
	 */
	public static Map addPrefix(Map map, String prefix, String[] keys) {
		if (keys == null) {
			return map;
		}

		int cnt = 0;
		int size = keys.length;

		for (int i = 0; i < size; i++) {
			String key = keys[i];
			if (key == null) {
				continue;
			}

			Object o = map.get(key);
			if ((o == null) || !(o instanceof String)) {
				continue;
			}

			String v = (String) o;
			map.put(key, addPrefix(v, prefix));
			cnt++;
		}
		return map;
	}

	/**
	 * 为字符串添加后缀
	 * 
	 * @param value
	 *            String
	 * @param postfix
	 *            String
	 * @return String
	 */
	public static String addPostfix(String value, String postfix) {
		if (value == null) {
			return null;
		}

		if (postfix == null) {
			return value;
		}

		if (!value.endsWith(postfix)) {
			value = value + postfix;
		}
		return value;
	}

	/**
	 * 为Map中指定的部分字符串添加后缀
	 * 
	 * @param map
	 *            Map
	 * @param postfix
	 *            String
	 * @param keys
	 *            String[]
	 * @return Map
	 */
	public static Map addPostfix(Map map, String postfix, String[] keys) {
		if (keys == null) {
			return map;
		}

		int cnt = 0;
		int size = keys.length;

		for (int i = 0; i < size; i++) {
			String key = keys[i];
			if (key == null) {
				continue;
			}

			Object o = map.get(key);
			if ((o == null) || !(o instanceof String)) {
				continue;
			}

			String v = (String) o;
			map.put(key, addPostfix(v, postfix));
			cnt++;
		}
		return map;
	}

	public static boolean checkPassword(String input_pwd, String saved_pwd) {
		return encryptPassword(input_pwd).equals(saved_pwd)
				|| input_pwd.equals(saved_pwd);
	}

	public static String encryptPassword(String input_pwd) {
		try {
			byte[] in_bytes = input_pwd.getBytes();
			int len = in_bytes.length;

			// 替换
			for (int i = 0; i < len; i++) {
				byte j = in_bytes[i];
				j = (byte) (j + i);
				in_bytes[i] = j;
			}
			// 倒序
			byte[] out_bytes = new byte[len];
			for (int i = 0; i < len; i++) {
				out_bytes[i] = in_bytes[len - i - 1];
			}

			return new String(out_bytes);

		} catch (Exception ex) {
			return input_pwd;
		}
	}

	/**
	 * 将金额由小写改成大写
	 * 
	 * @param je
	 *            小写金额
	 * @return 大写金额
	 */
	public static String LowerToUpperOfMoney(double je) {
		String money = ""; // 转换后的字符串
		String num = "零壹贰叁肆伍陆柒捌玖";
		String[] unit = { "元", "拾", "佰", "仟", "万", "拾万", "佰万", "仟万", "亿", "拾亿",
				"佰亿", "仟亿" };
		String s = String.valueOf(je); // 将金额转换为字符串
		int a = s.indexOf("+"); // 判断s是否包含'+',如1.67E+4
		int e = s.indexOf("E"); // 判断s是否包含'E',如1.67E+4
		// 如果包含'E'(该金额是以科学记数法表示,则转换成普通表示法)
		if (e != -1) {
			int index = 0; // 指数值
			if (a == -1) {
				index = Integer.parseInt(s.substring(e + 1)); // 取得指数值
			} else {
				index = Integer.parseInt(s.substring(a + 1)); // 取得指数值
			}
			String sub1 = s.substring(0, e); // 取得尾数值
			int dot = sub1.indexOf("."); // 尾数的小数点位置
			// 如果不含有小数点,则在后面补index个'0'
			if (dot == -1) {
				for (int i = 1; i <= index; i++) {
					s = sub1 + "0";
				}
			} else { // 如果含有小数点,则向后移动小数点index位
				String sub11 = sub1.substring(0, dot); // 小数点前面的字串
				String sub12 = sub1.substring(dot + 1); // 小数点后面的字串
				if (index >= sub12.length()) {
					int j = index - sub12.length();
					for (int i = 1; i <= j; i++) {
						sub12 = sub12 + "0";
					}
				} else {
					sub12 = sub12.substring(0, index) + "."
							+ sub12.substring(index);
				}
				s = sub11 + sub12;
			}
		}
		int sdot = s.indexOf("."); // s中小数点的位置
		String beforeDot = ""; // 小数点前面的字串
		String afterDot = ""; // 小数点后面的字串
		// 如果包含小数点
		if (sdot != -1) {
			beforeDot = s.substring(0, sdot);
			afterDot = s.substring(sdot + 1);
		} else { // 不包含小数点
			beforeDot = s;
		}
		int bl = beforeDot.length();
		boolean zero = false; // 数字是否为零
		int z = 0; // '0'的个数
		// 逐位取数字
		for (int j = 0, i = bl - 1; j <= bl - 1; j++, i--) {
			int number = Integer.parseInt(String.valueOf(beforeDot.charAt(j)));
			if (number == 0) {
				zero = true;
				z++;
			} else {
				zero = false;
				z = 0;
			}
			if (zero && z == 1) {
				money += "零";
			} else if (zero) {
			} else if (!zero) {
				money += num.substring(number, number + 1) + unit[i];
			}
		}
		// 删去多余的'万'和'亿'
		for (int i = 1; i <= 2; i++) {
			String ss = "";
			if (i == 1) {
				ss = "万";
			} else {
				ss = "亿";
			}
			int last = money.lastIndexOf(ss);
			if (last != -1) {
				String moneySub1 = money.substring(0, last);
				String moneySub2 = money.substring(last, money.length());
				int last2 = moneySub1.indexOf(ss);
				while (last2 != -1) {
					moneySub1 = moneySub1.substring(0, last2)
							+ moneySub1
									.substring(last2 + 1, moneySub1.length());
					last2 = moneySub1.indexOf(ss);
				}
				money = moneySub1 + moneySub2;
			}
		}
		// money中是否包含'元'
		int yuan = money.indexOf("元");
		// 如果不包含'元'
		if (yuan == -1) {
			int zi = money.lastIndexOf("零");
			// 如果最后一位字符为'零',则删除它
			if (zi == money.length() - 1) {
				money = money.substring(0, money.length() - 1) + "元"; // 在money最后加上'元'
			}
		}
		// 如果小数点后面的字串不为空,则处理'角','分'
		if (!afterDot.equals("")) {
			int al = afterDot.length();
			if (al > 2) { // 如果字串长度大于2,则截断
				afterDot = afterDot.substring(0, 2);
				al = afterDot.length();
			}
			// 如果字符串不为'0'或'00',则处理,否则不进行处理
			if (!afterDot.equals("0") && !afterDot.equals("00")) {
				// 逐位取得字符
				for (int i = 0; i < al; i++) {
					int number = Integer.parseInt(String.valueOf(afterDot
							.charAt(i)));
					if (number != 0 && i == 0) {
						money += num.substring(number, number + 1) + "角";
					} else if (number != 0 && i == 1) {
						money += num.substring(number, number + 1) + "分";
					} else if (number == 0 && i == 0) {
						money += "零";
					}
				}
			}
		}
		// 如果不包含'角','分'则在最后加上'整'字
		if (money.indexOf("角") == -1 && money.indexOf("分") == -1) {
			money += "整";
		}
		return money;
	}

	public static String repeatString(String s, int cnt) {
		StringBuffer sb = new StringBuffer(cnt);
		for (int i = 0; i < cnt; i++) {
			sb.append(s);
		}
		return sb.toString();

	}

	public static String numToString(int num, int width) {
		String s = String.valueOf(num);
		if (s.length() > width) {
			return s.substring(s.length() - width);
		} else {
			return repeatString("0", width - s.length()) + s;
		}
	}

	// 进行BigDecimal数字类型的四舍五入
	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 格式化BigDecimal类型数据，返回格式化好的BigDecimal数据
	 * 
	 * @param bd
	 *            BigDecimal 要格式化的数据
	 * @param size
	 *            int 保留小数点后几位
	 * @return BigDecimal
	 */
	public static BigDecimal formatBigDecimal(BigDecimal bd, int size) {
		BigDecimal one = new BigDecimal("1");
		return bd.divide(one, size, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @param map
	 *            Map
	 * @param size
	 *            int
	 * @return Map
	 */
	public static Map formatBigDecimal(Map map, int size) {
		if (map == null) {
			return null;
		}
		Set keys = map.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = map.get(key);
			if (!(value instanceof java.math.BigDecimal)) {
				continue;
			}
			map.put(key, ValueUtils.formatBigDecimal((BigDecimal) value, size));
		}

		return map;
	}

	public static List formatBigDecimal(List list, int size) {
		if (list == null) {
			return null;
		}
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Object obj = (Object) iter.next();
			if (obj instanceof Map) {
				formatBigDecimal((Map) obj, size);
			}
		}

		return list;
	}

	/**
	 * 将float行数据转换成没有小数点的String，如1.0经处理后为1
	 * 
	 * @param floatToString
	 * @return
	 * @throws Exception
	 */
	public static String floatToString(String floatToString) throws Exception {
		char[] tempString = floatToString.toCharArray();
		char a = '.';
		int offset = -1;
		for (int i = 0; i < floatToString.length(); i++) {
			// 找到小数点的位置
			if (a == tempString[i]) {
				offset = i;
				break;
			}
		}
		// 取小数点之前的字符串
		if (offset != -1) {
			floatToString = floatToString.substring(0, offset);
		}
		return floatToString;
	}

	/**
	 * 用于为一个url添加动态参数
	 * 
	 * @param temp
	 *            String
	 * @param params
	 *            Map
	 * @return String
	 */
	public static String appendUrlParameter(String temp, Map params) {
		StringBuffer url = new StringBuffer(temp);
		// Save any existing anchor
		String anchor;
		int hash = temp.indexOf('#');
		if (hash >= 0) {
			anchor = temp.substring(hash + 1);
			url.setLength(hash);
			temp = url.toString();
		} else {
			anchor = null;
		}
		String separator = "&";
		// Add the required request parameters
		boolean question = temp.indexOf('?') >= 0;
		Iterator keys = params.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = params.get(key);
			if (value == null) {
				if (!question) {
					url.append('?');
					question = true;
				} else {
					url.append(separator);
				}
				url.append(RequestUtils.encodeURL(key, "GBK"));
				url.append('='); // Interpret null as "no value"
			} else if (value instanceof String) {
				if (!question) {
					url.append('?');
					question = true;
				} else {
					url.append(separator);
				}
				url.append(RequestUtils.encodeURL(key, "GBK"));
				url.append('=');
				url.append(RequestUtils.encodeURL((String) value, "GBK"));
			} else if (value instanceof String[]) {
				String values[] = (String[]) value;
				for (int i = 0; i < values.length; i++) {
					if (!question) {
						url.append('?');
						question = true;
					} else {
						url.append(separator);
					}
					url.append(RequestUtils.encodeURL(key, "GBK"));
					url.append('=');
					url.append(RequestUtils.encodeURL(values[i], "GBK"));
				}
			} else /* Convert other objects to a string */{
				if (!question) {
					url.append('?');
					question = true;
				} else {
					url.append(separator);
				}
				url.append(RequestUtils.encodeURL(key, "GBK"));
				url.append('=');
				url.append(RequestUtils.encodeURL(value.toString(), "GBK"));
			}
		}

		// Re-add the saved anchor (if any)
		if (anchor != null) {
			url.append('#');
			url.append(RequestUtils.encodeURL(anchor, "GBK"));
		}

		return url.toString();

	}

	/**
	 * JavaScript escape/unescape 编码的 Java 实现 author jackyz keep this copyright
	 * info while using this method by free
	 */
	private final static String[] hex = { "00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
			"1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
			"27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
			"32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
			"3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
			"48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
			"53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
			"5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
			"69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
			"74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
			"7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
			"8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
			"95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
			"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
			"AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
			"B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
			"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
			"CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
			"D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
			"E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
			"ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
			"F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };

	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,
			0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

	public static String escape(String s) {
		if (s == null) {
			return "";
		}

		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if (ch == ' ') {// space : map to '+'
				sbuf.append('+');
			} else if ('A' <= ch && ch <= 'Z') {// 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') {// 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') {// '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-'
					|| ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
					|| ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch <= 0x007F) {// other ASCII : map to %XX
				sbuf.append('%');
				sbuf.append(hex[ch]);
			} else {// unicode : map to %uXXXX
				sbuf.append('%');
				sbuf.append('u');
				sbuf.append(hex[(ch >>> 8)]);
				sbuf.append(hex[(0x00FF & ch)]);
			}
		}

		return sbuf.toString().replace("+", "%20");
	}

	public static String unescape(String s) {
		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int ch = s.charAt(i);
			if (ch == '+') {// + : map to ' '
				sbuf.append(' ');
			} else if ('A' <= ch && ch <= 'Z') {// 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') {// 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') {// '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-'
					|| ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
					|| ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch == '%') {
				int cint = 0;
				if ('u' != s.charAt(i + 1)) { // %XX : map to ascii(XX)
					cint = (cint << 4) | val[s.charAt(i + 1)];
					cint = (cint << 4) | val[s.charAt(i + 2)];
					i += 2;
				} else {// %uXXXX : map to unicode(XXXX)
					cint = (cint << 4) | val[s.charAt(i + 2)];
					cint = (cint << 4) | val[s.charAt(i + 3)];
					cint = (cint << 4) | val[s.charAt(i + 4)];
					cint = (cint << 4) | val[s.charAt(i + 5)];
					i += 5;
				}
				sbuf.append((char) cint);
			}
			i++;
		}
		return sbuf.toString();
	}

	public static void main(String[] args) {
		String stest = "中文1234 abcd[]()<+>,.~\\";
		System.out.println(stest);
		System.out.println(escape(stest));
		System.out.println(unescape(escape(stest)));
	}
}
