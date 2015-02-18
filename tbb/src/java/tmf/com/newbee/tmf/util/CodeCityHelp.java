package com.newbee.tmf.util;

import java.util.HashMap;
import java.util.Map;

public class CodeCityHelp
{
	private static final Map<String, String> CODE_CITYNAME = new HashMap<String, String>();

	static
	{
		CODE_CITYNAME.put("027", "武汉");
		CODE_CITYNAME.put("024", "沈阳");
		CODE_CITYNAME.put("0411", "大连");
		CODE_CITYNAME.put("0412", "鞍山");
		CODE_CITYNAME.put("0413", "抚顺");
		CODE_CITYNAME.put("0414", "本溪");
		CODE_CITYNAME.put("0415", "丹东");
		CODE_CITYNAME.put("0416", "锦州");
		CODE_CITYNAME.put("0417", "营口");
		CODE_CITYNAME.put("0419", "辽阳");
		CODE_CITYNAME.put("0410", "铁岭");
		CODE_CITYNAME.put("0421", "朝阳");
		CODE_CITYNAME.put("0418", "阜新");
		CODE_CITYNAME.put("0429", "葫芦岛");
		CODE_CITYNAME.put("0427", "盘锦");
	}

	public static String getCityNameByCode(String code)
	{
		return CODE_CITYNAME.get(code);
	}

	public static void main(String[] argvs)
	{
		System.out.println(CodeCityHelp.getCityNameByCode("027"));
	}
}
