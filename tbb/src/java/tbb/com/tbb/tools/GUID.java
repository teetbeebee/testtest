package com.tbb.tools;

import java.util.UUID;

public class GUID {

	/**
	 * 生成32位的唯一字符串
	 * 
	 * @return
	 */
	public static String generate() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
