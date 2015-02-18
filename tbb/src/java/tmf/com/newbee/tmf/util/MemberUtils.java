package com.newbee.tmf.util;

import com.tbb.tools.GUID;

public class MemberUtils {
	/**
	 * 生成 uid
	 */
	public static String genUid() {
		
		String uid = GUID.generate();

		return uid;
	}
	

}
