package com.tbb.manage;

import java.util.HashMap;
import java.util.List;

import com.tbb.sys.domain.SysUser;
import com.tbb.sys.service.SysUserService;

public class SysUserManage {
	private SysUserService sus;

	private static SysUserManage instance;

	private List<SysUser> sysUserList;

	private HashMap keyMap;

	@SuppressWarnings("unchecked")
	private SysUserManage() throws Exception {
		sus = SysUserService.getInstance();
		sysUserList = sus.querySysUserForList(null);
		keyMap = new HashMap();
		for (SysUser user : sysUserList) {
			keyMap.put(user.getUser_id(), user.getUser_name());
		}
	}

	// 当用户表发生更改时重载用户表
	@SuppressWarnings("unchecked")
	public void reloadSysUser() throws Exception {
		sus = SysUserService.getInstance();
		sysUserList = sus.querySysUserForList(null);
		keyMap = new HashMap();
		for (SysUser user : sysUserList) {
			keyMap.put(user.getUser_id(), user.getUser_name());
		}
	}

	public static SysUserManage getInstance() throws Exception {
		if (instance == null) {
			instance = new SysUserManage();
		}
		return instance;
	}

	public List getSysUserList() {
		return sysUserList;
	}

	public String getUserName(String key) {
		if (keyMap.get(key) != null) {
			return (String) keyMap.get(key);
		} else {
			return key;
		}

	}

}
