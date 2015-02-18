package com.tbb.tools;

import java.util.List;
import java.util.Map;

import com.tbb.core.service.SqlService;

public class Pinyin {
	
	private static Pinyin instance = new Pinyin();

	private Pinyin() 
	{
		// empty
		// 防止直接创建对象
	}

	public static Pinyin getInstance() {
		return instance;
	}

	//暂时没有考虑多音字问题
	public static String getPinyinFirst(String strChinese)
	{
		String sRet = "";
		SqlService sqlsvr = SqlService.getInstance();
		String sUpperChinese = strChinese.toUpperCase();

		for(int i=0; i<strChinese.length(); i++)
		{
			char thisChar = sUpperChinese.charAt(i);
			if(Character.isDigit(thisChar))//数字
			{
				sRet += thisChar;
				continue;
			}
			if(Character.isLetter(thisChar))
			{
				int type = Character.getType(thisChar);
				if(type == Character.UPPERCASE_LETTER)//字母
				{
					sRet += thisChar;
					continue;
				}
				else if(type == Character.OTHER_LETTER)//中文
				{
					String sql = "SELECT PINYIN FROM PINYIN WHERE CHINESE='" + thisChar + "'";
					try {
						List list = sqlsvr.querySqlForList(sql);
						if(list.size()<1)
							continue;
						String sPy = (String)((Map)list.get(0)).get("pinyin");
						if(sPy.length()<0)
							continue;
						char firstChar = sPy.toUpperCase().charAt(0);
						sRet += firstChar;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}	
		return sRet;
	}
}