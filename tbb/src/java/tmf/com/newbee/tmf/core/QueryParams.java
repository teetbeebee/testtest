package com.newbee.tmf.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 用于实现在页面上将查询参数同其他参数区分的方法，如果查询参数中有字段plan_id，而在
 * 列表每条记录上的其他操作如提交、删除也将使用plan_id作为参数，使用一个统一的方法来 将两者区分，便于服务器端可以重新利用该查询参数的值而不至于混淆。
 * <br>
 * 在values中，保存了普通的(key,value)值对，其中key为参数name，value为参数值
 * 在names中，保存了原key与改造后的key的对应关系，其中改造后的new_key目前采用在 原key前添加前缀_wmf_的办法 <br>
 * 在页面中使用方法 <form> <input type="text" name="$params.names.plan_id"
 * value="params.value.plan_id"> </form> 假设在运行时，plan_id=1001,则将解析为 <form> <input
 * type="text" name="_wmf_plan_id" value="1001"> </form>
 * 
 * 在服务器端，可以方便地将所有以"_wmf_"为前缀的参数取出来，去掉前缀后，可以还原到正常 名称的参数Map中去,参见BaseAction中的处理
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: dm.corp
 * </p>
 */
public class QueryParams implements Map {
	private Map<String, Object> values;

	private Map<String, Object> array_values;

	String prefix = "_wmf_";

	private NameMap names;

	public static int defaultPageSize = 10;

	class NameMap extends HashMap {
		/**
		 * 序列化id
		 */
		private static final long serialVersionUID = -2033984558953751647L;

		public String get(String name) {
			return QueryParams.this.getName(name);
		}

		public Object get(Object key) {
			Object o = super.get(key);
			if (o == null) {
				return QueryParams.this.getName(key);
			} else {
				return o;
			}
		}
	}

	public QueryParams() {
		names = new NameMap();
		values = new HashMap<String, Object>();
		array_values = new HashMap<String, Object>();
	}

	/**
	 * 取前缀
	 * 
	 * @return String
	 */
	private String getPrefix() {
		return this.prefix;
	}

	/**
	 * 检查是否为页面上查询参数专用的名称
	 * 
	 * @param name String
	 * @return boolean
	 */
	public boolean isQueryParameterName(String name) {
		return name.startsWith(prefix);
	}

	/**
	 * 从查询参数专用名称中取回原始参数名称
	 * 
	 * @param name String
	 * @return String
	 */
	public String getParameterName(String name) {
		return name.substring(prefix.length());
	}

	/**
	 * 将参数名称转化为页面上查询参数专用名称格式 在页面上的使用方法 $params.name("plan_id")
	 * 
	 * @param name String
	 * @return String
	 */
	public String getName(String name) {
		return getPrefix() + name;
	}

	/**
	 * 将参数名称转化为页面上查询参数专用名称格式 在页面上的使用方法 $params.name("plan_id")
	 * 
	 * @param name Object
	 * @return String
	 */
	public String getName(Object name) {
		if (name == null) {
			return getPrefix();
		} else {
			return getPrefix() + name;
		}
	}

	/**
	 * 添加参数,如果值为空串则视为null
	 * 
	 * @param name String
	 * @param value String
	 */
	public void addParameter(String name, String value) {
		if (value == null || value.trim().equals("")) {
			values.put(name, null);
		} else {
			values.put(name, value.trim());
		}
	}

	/**
	 * 添加参数（值为数组型）
	 * 
	 * @param name String
	 * @param value String[]
	 */
	public void addParameter(String name, String[] value) {
		array_values.put(name, value);
	}

	/**
	 * 添加查询参数，如果值为空串则视为null
	 * 
	 * @param name
	 * @param value
	 */
	public void addQueryParameter(String name, String value) {
		if (value == null || value.trim().equals("")) {
			values.put(name, null);
		} else {
			values.put(name, value.trim());
		}
		names.get(name);
	}

	// 用于实现类似从request里面获取参数的方法
	/**
	 * 取出参数Map
	 * 
	 * @return Map
	 */
	public Map<String, Object> getParameterMap() {
		return values;
	}

	public Map<String, Object> getQueryMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator iter = values.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry item = (Map.Entry) iter.next();
			map.put(getName(item.getKey()), item.getValue());
		}
		return map;
	}

	/**
	 * 直接取参数
	 * 
	 * @param name String
	 * @return String
	 */
	public String getParameter(String name) {
		return (String) values.get(name);
	}

	/**
	 * 直接取数组参数
	 * 
	 * @param name String
	 * @return String[]
	 */
	public String[] getParameterValues(String name) {
		return (String[]) array_values.get(name);
	}

	/**
	 * 用于页面上反显参数时取参数名标识 在页面上的使用方法 $params.names.plan_id
	 * 
	 * @return Map 记录名称对应关系
	 */
	public NameMap getNames() {
		return names;
	}

	/**
	 * 用于页面上取参数值时的值Map 在页面上的使用方法 $params.values.plan_id
	 * 
	 * @return Map 参数值
	 */
	public Map getValues() {
		return values;
	}

	/**
	 * 用于直接从params中取值 在页面上的使用方法 $params.plan_id
	 * 
	 * @param name String
	 * @return Object
	 */
	public Object get(String name) {
		return values.get(name);
	}

	/**
	 * 将所有的参数以hidden的方式添加到页面的form中 页面上使用方法: <form> $params.hiddenAll </form>
	 * 
	 * @return String
	 */
	public String getHiddenAll() {
		StringBuffer sb = new StringBuffer();
		Iterator iter = values.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			sb.append("<input type=\"hidden\" name=\"");
			sb.append(getName(entry.getKey()));
			sb.append("\" value=\"");
			Object o = entry.getValue();
			if (o != null) {
				sb.append(entry.getValue());
			}
			sb.append("\" > \r\n");
		}
		return sb.toString();
	}

	public int getPageIndex() {
		String pageIndex = getParameter("pageIndex");
		if (pageIndex == null || pageIndex.trim().equals("")) {
			return 1;
		} else {
			try {
				return Integer.parseInt(pageIndex);
			} catch (Exception e) {
				return 1;
			}
		}
	}

	public int getPageSize() {
		// 读取页码参数
		String pageIndex = getParameter("pageSize");
		if (pageIndex == null || pageIndex.trim().equals("")) {
			return defaultPageSize;
		} else {
			try {
				return Integer.parseInt(pageIndex);
			} catch (Exception e) {
				return defaultPageSize;
			}
		}

	}

	public String saveToString() {
		/** @todo 未完成,需要写一个高效可逆的算法 */
		return this.toString();
	}

	public void loadFromString(String value) {
		/** @todo 未完成,需要配合saveToString来处理 */
	}

	public static void main(String[] args) {
		QueryParams params = new QueryParams();
		String name = params.getName("demo");
		System.out.println("name = " + name);
		name = params.getParameterName(name);
		System.out.println("reverse = " + name);
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * @return the number of key-value mappings in this map.
	 * @todo 目前Map接口由values代替实现，以后考虑改为由query_map，即实际 的原始的(key,value)来实现
	 */
	public int size() {
		return values.size();
	}

	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 * 
	 * @return <tt>true</tt> if this map contains no key-value mappings.
	 */
	public boolean isEmpty() {
		return values.isEmpty();
	}

	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the specified
	 * key.
	 * 
	 * @param key key whose presence in this map is to be tested.
	 * @return <tt>true</tt> if this map contains a mapping for the specified
	 *         key.
	 */
	public boolean containsKey(Object key) {
		return values.containsKey(key);
	}

	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the
	 * specified value.
	 * 
	 * @param value value whose presence in this map is to be tested.
	 * @return <tt>true</tt> if this map maps one or more keys to the
	 *         specified value.
	 */
	public boolean containsValue(Object value) {
		return values.containsValue(value);
	}

	/**
	 * Returns the value to which this map maps the specified key.
	 * 
	 * @param key key whose associated value is to be returned.
	 * @return the value to which this map maps the specified key, or
	 *         <tt>null</tt> if the map contains no mapping for this key.
	 */
	public Object get(Object key) {
		return values.get(key);
	}

	/**
	 * Associates the specified value with the specified key in this map
	 * (optional operation).
	 * 
	 * @param key key with which the specified value is to be associated.
	 * @param value value to be associated with the specified key.
	 * @return previous value associated with specified key, or <tt>null</tt>
	 *         if there was no mapping for key. A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt>
	 *         with the specified key, if the implementation supports
	 *         <tt>null</tt> values.
	 */
	public Object put(Object key, Object value) {
		return values.put((String) key, value);
	}

	/**
	 * Removes the mapping for this key from this map if it is present (optional
	 * operation).
	 * 
	 * @param key key whose mapping is to be removed from the map.
	 * @return previous value associated with specified key, or <tt>null</tt>
	 *         if there was no mapping for key.
	 */
	public Object remove(Object key) {
		return values.remove(key);
	}

	/**
	 * Copies all of the mappings from the specified map to this map (optional
	 * operation).
	 * 
	 * @param t Mappings to be stored in this map.
	 */
	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		values.putAll(t);
	}

	/**
	 * Removes all mappings from this map (optional operation).
	 * 
	 */
	public void clear() {
		values.clear();
	}

	/**
	 * Returns a set view of the keys contained in this map.
	 * 
	 * @return a set view of the keys contained in this map.
	 */
	public Set keySet() {
		return values.keySet();
	}

	/**
	 * Returns a collection view of the values contained in this map.
	 * 
	 * @return a collection view of the values contained in this map.
	 */
	public Collection values() {
		return values.values();
	}

	/**
	 * Returns a set view of the mappings contained in this map.
	 * 
	 * @return a set view of the mappings contained in this map.
	 */
	public Set entrySet() {
		return values.entrySet();
	}

	/**
	 * Compares the specified object with this map for equality.
	 * 
	 * @param o object to be compared for equality with this map.
	 * @return <tt>true</tt> if the specified object is equal to this map.
	 * @todo 需要重写
	 */
	public boolean equals(Object o) {
		return values.equals(o);
	}

	/**
	 * Returns the hash code value for this map.
	 * 
	 * @return the hash code value for this map.
	 * @todo 需要重写
	 */
	public int hashCode() {
		return values.hashCode();
	}
}
