package com.tbb.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tbb.sys.domain.SysDict;
import com.tbb.sys.service.SysDictService;

public class SysDictManage {
	private SysDictService sds;

	private static SysDictManage instance;

	private List<SysDict> sysDictList;

	private HashMap keyMap;

	@SuppressWarnings("unchecked")
	private SysDictManage() throws Exception {
		sds = SysDictService.getInstance();
		Map params = new HashMap();
		params.put("orderBy", "dict_type,sort_order desc,code_id");
		sysDictList = sds.querySysDictForList(params);
		keyMap = new HashMap();
		for (SysDict fd : sysDictList) {
			keyMap.put(fd.getDict_type() + ";" + fd.getCode_id(), fd
					.getCode_value());
		}
	}

	// 当系统字典发生更改时重载系统字典表
	@SuppressWarnings("unchecked")
	public void reloadSysDict() throws Exception {
		sds = SysDictService.getInstance();
		Map params = new HashMap();
		params.put("orderBy", "dict_type,sort_order desc,code_id");
		sysDictList = sds.querySysDictForList(params);
		keyMap = new HashMap();
		for (SysDict fd : sysDictList) {
			keyMap.put(fd.getDict_type() + ";" + fd.getCode_id(), fd
					.getCode_value());
		}
	}

	public static SysDictManage getInstance() throws Exception {
		if (instance == null) {
			instance = new SysDictManage();
		}
		return instance;
	}

	public List getSysDictList() {
		return sysDictList;
	}

	public String getDictValue(String key) {
		if (keyMap.get(key) != null) {
			return (String) keyMap.get(key);
		} else {
			return key.split(";").length > 1 ? key.split(";")[1] : "";
		}

	}

	@SuppressWarnings("unchecked")
	public List<String> getCodeValueList(String dict_type) {
		List<String> codeValueList = new ArrayList();

		Iterator it = keyMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();

			if (key.split(";")[0].equals(dict_type)) {
				codeValueList.add(key.split(";")[1]);
			}
		}

		return codeValueList;
	}
}
