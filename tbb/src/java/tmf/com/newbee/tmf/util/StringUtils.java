package com.newbee.tmf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理公用类
 * 
 *  
 */
public class StringUtils
{
	public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	public final static String DEFAULT_TIME_PATTERN = "hh:mm:ss";

	public final static String DEFAULT_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
     * 判断传入的字符串是否有效(当字符串为空或为null时无效)
     * 
     * @param str
     * @return 有效true无效false
     */
	public static boolean isValid(String str)
	{
		if (str != null && !str.equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
     * 当字符串为空或为null或空串时返回null
     * 
     * @param str
     * @return
     */
	public static Object getStringValue(String str)
	{
		if (str != null && !str.equals(""))
		{
			return str.trim();
		}
		else
		{
			return null;
		}
	}

	/**
     * 判断传入的字符串数组是否有效(当字符串数组长度为0或为null时无效)
     * 
     * @param str
     * @return
     */
	public static boolean isValid(String[] str)
	{
		if (str != null && str.length > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
     * 判断传入的字符串数组是否有效(当字符串数组长度为0或为null或不等于length时无效)
     * 
     * @param str
     * @param length 数组长度
     * @return
     */
	public static boolean isValid(String[] str, int length)
	{
		if (isValid(str))
		{
			if (str.length == length)
			{
				return true;
			}
		}

		return false;
	}

	/**
     * 将字串的首写字母大写,出现"_"的,去掉并且将之后的一个字母大写.
     * 
     * @param formatstr
     * @return
     */
	public static String toFormatMethodName(String formatstr)
	{
		if (formatstr == null)
		{
			return "";
		}
		String _Str = formatstr.substring(0, 1).toUpperCase()
				+ formatstr.substring(1);
		int _Pos = 0;
		while ((_Pos = _Str.indexOf("_")) > 0)
		{
			if (_Str.length() > _Pos + 1)
			{
				_Str = _Str.substring(0, _Pos)
						+ _Str.substring(_Pos + 1, _Pos + 2).toUpperCase()
						+ _Str.substring(_Pos + 2);
			}
			else
			{
				_Str = _Str.substring(0, _Pos);
			}
		}
		return _Str;
	}

	/**
     * 将字串的首写字母大写
     * 
     * @param formatstr
     * @return
     */
	public static String toFormatName(String formatstr)
	{
		if (formatstr == null)
		{
			return "";
		}
		return formatstr.substring(0, 1).toUpperCase() + formatstr.substring(1);
	}

	/**
     * 将字串的首写字母小写
     * 
     * @param formatstr
     * @return
     */
	public static String toFormatVariable(String formatstr)
	{
		if (formatstr == null)
		{
			return "";
		}
		return formatstr.substring(0, 1).toLowerCase() + formatstr.substring(1);
	}

	/**
     * 按分隔符号读出字符串的内容
     * 
     * @param strlist 含有分隔符号的字符串
     * @param ken 分隔符号
     * @return 列表
     */
	public static final ArrayList parseStringToArrayList(String strlist,
			String ken)
	{
		StringTokenizer st = new StringTokenizer(strlist, ken);

		if (strlist == null || strlist.equals("") || st.countTokens() <= 0)
		{
			return new ArrayList();
		}

		int size = st.countTokens();
		ArrayList strv = new ArrayList();

		for (int i = 0; i < size; i++)
		{
			String nextstr = st.nextToken();
			if (!nextstr.equals(""))
			{
				strv.add(nextstr);
			}
		}
		return strv;
	}

	/**
     * 将字符串按照规则进行分段，如将"2001-10-12"按照"-"划分，则分为"2001"、"10"和"12"三段
     * 
     * @param szSource 进行分段的字符串
     * @param token 分隔符号
     * @return 分段后的字符串数组
     */
	public static final String[] SplitString(String szSource, String token)
	{
		if (szSource == null || token == null)
		{
			return null;
		}
		StringTokenizer st1 = new StringTokenizer(szSource, token);
		String d1[] = new String[st1.countTokens()];
		for (int x = 0; x < d1.length; x++)
		{
			if (st1.hasMoreTokens())
			{
				d1[x] = st1.nextToken().trim();
			}
		}
		return d1;
	}

	/**
     * Replaces all instances of oldString with newString in line.
     * 
     * @param line the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @return a String will all instances of oldString replaced by newString
     */
	public static final String replace(String line, String oldString,
			String newString)
	{
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
     * 用于character codes的转换(ISO8859-1--->GB2312)
     */
	public static final String toCNString(String str)
	{
		if (str == null)
		{
			return null;
		}
		try
		{
			byte[] bt = str.getBytes("ISO8859-1");
			str = new String(bt, "GB2312");
		}
		catch (Exception ex)
		{
			return null;
		}
		return str;
	}

	/**
     * 将一个字符串从编码from转换到编码to
     * 
     * @param str
     * @param from
     * @param to
     * @return
     */
	public static final String convertEncode(String str, String from, String to)
	{
		if (str == null || from == null || to == null)
		{
			return null;
		}
		try
		{
			byte[] bt = str.getBytes(from);
			str = new String(bt, to);
		}
		catch (Exception e)
		{
			return null;
		}
		return str;
	}

	/**
     * 将一个序列化对象转换成用点号隔开的字节码字符串,如"12.25.102.31"
     * 
     * @param obj
     * @return
     * @throws Exception
     */
	public static String writeObject(Serializable obj) throws Exception
	{
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(o);
		out.writeObject(obj);
		out.flush();
		out.close();
		o.close();
		byte[] data = o.toByteArray();

		String str = "";
		for (int i = 0; i < data.length; i++)
		{
			if (i == 0)
			{
				str += data[i];
			}
			else
			{
				str += "." + data[i];
			}
		}
		return str;
	}

	/**
     * 将一个被序列化成字符串的字符串反序列化成对象
     * 
     * @param str
     * @return
     * @throws Exception
     */
	public static Object readObject(String str) throws Exception
	{
		StringTokenizer st = new StringTokenizer(str, ".");
		ArrayList al = new ArrayList();
		while (st.hasMoreTokens())
		{
			al.add(st.nextToken());
		}

		byte[] bt = null;
		if (!al.isEmpty())
		{
			String[] s_bt = new String[al.size()];
			al.toArray(s_bt);
			bt = new byte[al.size()];
			for (int i = 0; i < bt.length; i++)
			{
				bt[i] = Byte.parseByte(s_bt[i]);
			}
		}
		ByteArrayInputStream i = new ByteArrayInputStream(bt);
		ObjectInputStream in = new ObjectInputStream(i);
		Object obj = in.readObject();
		in.close();
		i.close();
		return obj;
	}

	/**
     * 取源字符串中，从pos开始，出现在tag之间的子字符串
     * 
     * @param source源字符串
     * @param tag标识
     * @param pos开始位置
     * @return 子字符串
     */
	public static String subString(String source, String tag, int pos)
	{

		int start = source.indexOf("<" + tag + ">", pos) + tag.length() + 2;
		int end = source.indexOf("</" + tag + ">", pos + tag.length() + 3);
		if (start == tag.length() + 1)
		{ // 没找到开始标志
			return "";
		}
		return source.substring(start, end);
	}

	/**
     * 按tag从原字符串中提取子字符串，并按tag后的名字存放到hashmap中 tag中的字符串为 名$值 格式
     * 
     * @param source源字符串
     * @param tag标识
     * @return hashMap
     */
	public static HashMap split(String source, String tag)
	{
		int pos = 0;
		String temp = "";
		HashMap hash = new HashMap();
		while (!(temp = subString(source, tag, pos)).equals(""))
		{
			pos = source.indexOf(temp) + temp.length();
			int _pos = temp.indexOf("$");
			hash.put(temp.substring(0, _pos), temp.substring(_pos + 1));
		}
		return hash;
	}

	/**
     * 数组转String. 比如:<br>
     * 
     * <pre>
	 * String str = ArrayToString(new String[] { &quot;a&quot;, &quot;b&quot; }, &quot;_&quot;, &quot;,&quot;);
	 * System.out.println(str); // &quot;_a, _b&quot;
	 * </pre>
     * 
     * @param objs 数组
     * @param prefix 转换结果中每一项的前缀
     * @param delim 转换结果中各项的分隔符
     * @return
     */
	public static String ArrayToString(Object[] objs, String prefix,
			String delim)
	{
		StringBuffer sb = new StringBuffer();

		int len = objs == null ? 0 : objs.length;

		if (len > 0)
		{
			sb.append(prefix).append(objs[0]);

			for (int i = 1; i < len; i++)
			{
				sb.append(delim).append(prefix).append(objs[i]);
			}

		}

		return sb.toString();
	}

	public String firstCharToUpperCase(String name)
	{
		return new StringBuffer().append(Character.toUpperCase(name.charAt(0)))
				.append(name.substring(1)).toString();
	}

	public String firstCharToLowerCase(String name)
	{
		return new StringBuffer().append(Character.toLowerCase(name.charAt(0)))
				.append(name.substring(1)).toString();
	}

	/**
     * 将字符串按字母的进制进位(大写字母):比如<br>
     * 
     * <pre>
	 * String str = String.convertByLetter(&quot;ZZZ&quot;, 2, -1);
	 * System.out.println(str); // &quot;AAAB&quot;
	 * </pre>
     */
	public static String convertByLetter(String letters)
	{
		return convertByLetter(letters, 1, -1);
	}

	public static String convertByLetter(String letters, int increament)
	{
		return convertByLetter(letters, increament, -1);
	}

	/**
     * 将字符串按字母的进制进位(大写字母):比如<br>
     * 
     * <pre>
	 * String str = String.convertByLetter(&quot;ZZZ&quot;, 2, -1);
	 * System.out.println(str); // &quot;AAAB&quot;
	 * </pre>
     * 
     * @param letters 源字符串
     * @param increament 增量
     * @param pos 指定对哪一位进行操作（从1开始）-1表示最末位，0则返回"A"+letters
     * @return 转换后的字符串
     */
	public static String convertByLetter(String letters, int increament, int pos)
	{
		int len = letters.length();
		int value;
		char letter;
		String strPlace;

		if (pos == -1)
		{
			pos = len;
		}
		if (pos == 0)
		{
			letters = "A" + letters;
			return letters;
		}
		letter = letters.charAt(pos - 1);
		value = letter + increament;
		strPlace = letters.substring(pos - 1, pos);
		if (value - 90 > 0)
		{
			strPlace = strPlace.replace(letter, (char)(64 + (value - 90)));
			// 转换为大写字母
		}
		else
		{
			strPlace = strPlace.replace(letter, (char)value);
		}
		letters = letters.substring(0, pos - 1) + strPlace
				+ letters.substring(pos);
		if (value - 90 > 0)
		{ // 处理进位
			letters = convertByLetter(letters, 1, pos - 1);
		}

		return letters;

	}

	/**
     * 拷贝指定个数字符为一个字符串。
     * 
     * @param c 字符
     * @param count 拷贝个数。
     * @return 新字符串。如果count<1的话，返回空串。
     *  
     */
	public static String copyValue(char c, int count)
	{
		String string = new String("");
		if (count < 1)
		{
			return "";
		}
		for (int i = 0; i < count; i++)
		{
			string += c;
		}
		return string;
	}

	/**
     * 在指定的位子替换字符。
     * 
     * @param source 待替换的字符串。
     * @param c 要替换的字符。
     * @param pos 位子。
     * @return 新的被替换过的字符串。如果pos<0或pos>source.length就返回原字符串。
     *  
     */
	public static String replace(String source, char c, int pos)
	{
		String string;
		char[] sa = source.toCharArray();
		for (int i = 0; i < sa.length; i++)
		{
			if (i == pos)
			{
				sa[i] = c;
			}
		}
		string = new String(sa);
		return string;
	}

	/**
     * 将字符串得到按模式得到日期型的数据<br>
     * 
     * <pre>
	 *       String dateString=&quot;1989-06-25 06:12:36&quot;
	 *       String str = StringUtils.getDateString(dateString,HH:MM:SS)
	 *       System.out.println(str); // &quot;06:12:36&quot;
	 * </pre>
     */

	public static String getDateString(String date, String pattern)
	{

		Date formatDate = null;
		char DatePatternMediator = '-';
		char TimePatternMediator = ':';

		if (date.indexOf(DatePatternMediator) != -1)
		{
			if (date.indexOf(TimePatternMediator) != -1)
			{
				formatDate = getTimestamp(date, DEFAULT_TIMESTAMP_PATTERN);
			}
			else
			{
				formatDate = getTimestamp(date, DEFAULT_DATE_PATTERN);
			}
		}
		else
		{
			formatDate = getTimestamp(date, DEFAULT_TIME_PATTERN);

		}
		StringBuffer sb = new StringBuffer();
		pattern = pattern.toLowerCase();
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date(formatDate.getTime()));

		if (pattern.equalsIgnoreCase("'YYYY年M月D日'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("年").append(
					cal.get(Calendar.MONTH) + 1).append("月").append(
					cal.get(Calendar.DATE)).append("日");
		}
		if (pattern.equalsIgnoreCase("'YYYY年MM月DD日'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("年").append(
					fillZero(cal.get(Calendar.MONTH) + 1)).append("月").append(
					fillZero(cal.get(Calendar.DATE))).append("日");
		}
		if (pattern.equals("'" + DEFAULT_TIMESTAMP_PATTERN.toLowerCase() + "'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("-").append(
					fillZero(cal.get(Calendar.MONTH) + 1)).append("-").append(
					fillZero(cal.get(Calendar.DATE))).append(" ").append(
					fillZero(cal.get(Calendar.HOUR))).append(":").append(
					fillZero(cal.get(Calendar.MINUTE))).append(":").append(
					fillZero(cal.get(Calendar.SECOND)));
		}
		if (pattern.equals("'" + DEFAULT_DATE_PATTERN.toLowerCase() + "'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("-").append(
					fillZero(cal.get(Calendar.MONTH) + 1)).append("-").append(
					fillZero(cal.get(Calendar.DATE)));
		}
		if (pattern.equals("'" + DEFAULT_TIME_PATTERN.toLowerCase() + "'"))
		{
			sb.append(fillZero(cal.get(Calendar.HOUR))).append(":").append(
					fillZero(cal.get(Calendar.MINUTE))).append(":").append(
					fillZero(cal.get(Calendar.SECOND)));
		}

		if (pattern.equals("'yyyy-mm-dd hh:mm'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("-").append(
					fillZero(cal.get(Calendar.MONTH) + 1)).append("-").append(
					fillZero(cal.get(Calendar.DATE))).append(" ").append(
					fillZero(cal.get(Calendar.HOUR))).append(":").append(
					fillZero(cal.get(Calendar.MINUTE)));
		}
		if (pattern.equals("'yyyy-mm-dd hh'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("-").append(
					fillZero(cal.get(Calendar.MONTH) + 1)).append("-").append(
					fillZero(cal.get(Calendar.DATE))).append(" ").append(
					fillZero(cal.get(Calendar.HOUR)));
		}
		if (pattern.equals("'yyyy-mm'"))
		{
			sb.append(cal.get(Calendar.YEAR)).append("-").append(
					fillZero(cal.get(Calendar.MONTH) + 1));
		}
		if (pattern.equals("'mm-dd'"))
		{
			sb.append(fillZero(cal.get(Calendar.MONTH) + 1)).append("-")
					.append(fillZero(cal.get(Calendar.DATE)));
		}

		if (pattern.equals("'hh:mm'"))
		{

			sb.append(fillZero(cal.get(Calendar.HOUR))).append(":").append(
					fillZero(cal.get(Calendar.MINUTE)));
		}
		if (pattern.equals("'mm:ss'"))
		{

			sb.append(fillZero(cal.get(Calendar.MINUTE))).append(":").append(
					fillZero(cal.get(Calendar.SECOND)));
		}

		if (pattern.equals("'yyyy'"))
		{
			sb.append(cal.get(Calendar.YEAR));
		}
		if (pattern.equals("'mm'"))
		{
			sb.append(fillZero(cal.get(Calendar.MONTH) + 1));
		}
		if (pattern.equals("'dd'"))
		{
			sb.append(fillZero(cal.get(Calendar.DATE)));
		}
		if (pattern.equals("'hh'"))
		{
			sb.append(fillZero(cal.get(Calendar.HOUR)));
		}
		return sb.toString();

	}

	private static String fillZero(int i)
	{
		if (i < 10)
		{
			return "0" + String.valueOf(i);
		}
		else
		{
			return String.valueOf(i);
		}
	}

	/**
     * 将数字型货币转换为中文型货币<br>
     * 
     * <pre>
	 *       String currency=&quot;1111111.111&quot;
	 *       String str = StringUtils.getCurrency2CN(currency)
	 *       System.out.println(str); // &quot;壹佰一十一万壹千壹佰一十一元一角一分&quot;
	 * </pre>
     */
	final static String CARRY_SHI = "拾";

	final static String CARRY_BAI = "佰";

	final static String CARRY_QIAN = "仟";

	final static String CARRY_WAN = "万";

	final static String CARRY_YI = "亿";

	final static String CUR_YUAN = "元";

	final static String CUR_FEN = "分";

	final static String CUR_JIAO = "角";

	final static String[] digits = {"零", "壹", "貳", "叁", "肆", "伍", "陆", "柒",
			"捌", "玖",};

	public static String getValueOfCurrency2CN(String priValue)
	{
		String value = String.valueOf(priValue);
		int dot_pos = String.valueOf(value).indexOf('.');
		String int_value;
		String fraction_value;
		if (dot_pos == -1)
		{
			int_value = value;
			fraction_value = "00";
		}
		else
		{
			int_value = value.substring(0, dot_pos);
			fraction_value = value.substring(dot_pos + 1, priValue.length())
					+ "00".substring(0, 2);
		}

		StringBuffer cn_currency = new StringBuffer();

		int len = int_value.length();
		boolean zero_flag = false;

		// if (len == 16)
		// int_value.substring(0, 15);
		if (len > 12 && len <= 16)
		{
			String temp = int_value.substring(0, len - 12);
			if (Integer.parseInt(temp) != 0)
			{
				cn_currency.append(
						cell2CH(int_value.substring(0, len - 12), zero_flag))
						.append(CARRY_WAN);
			}
			int_value = int_value.substring(len - 12, len);
			len = 12;
			zero_flag = true;
		}
		if (len > 8 && len <= 12)
		{
			String temp = int_value.substring(0, len - 8);
			if (Integer.parseInt(temp) != 0)
			{
				cn_currency.append(
						cell2CH(int_value.substring(0, len - 8), zero_flag))
						.append(CARRY_YI);
			}
			int_value = int_value.substring(len - 8, len);
			len = 8;

		}
		if (len > 4 && len <= 8)
		{
			// zero_flag = false;
			String temp = int_value.substring(0, len - 4);
			if (Integer.parseInt(temp) != 0)
			{
				cn_currency.append(
						cell2CH(int_value.substring(0, len - 4), zero_flag))
						.append(CARRY_WAN);
			}
			int_value = int_value.substring(len - 4, len);
			len = 4;
			zero_flag = true;
		}

		cn_currency.append(cell2CH(int_value.substring(0, len), zero_flag));

		if (!cn_currency.toString().equals(""))
		{
			cn_currency.append(CUR_YUAN);

		}
		int t = Character.getNumericValue(fraction_value.charAt(0));
		if (t != 0)
		{
			cn_currency.append(digits[t]).append(CUR_JIAO);

		}
		t = Character.getNumericValue(fraction_value.charAt(1));
		if (t != 0)
		{
			cn_currency.append(digits[t]).append(CUR_FEN);

		}
		String returnVaule = cn_currency.toString();
		returnVaule = StringUtils.replace(returnVaule, "零零", "零");
		returnVaule = StringUtils.replace(returnVaule, "零元", "元");
		returnVaule = StringUtils.replace(returnVaule, "零万", "万");
		returnVaule = StringUtils.replace(returnVaule, "零亿", "亿");
		if (!returnVaule.endsWith(CUR_FEN))
		{
			returnVaule = returnVaule + "整";
		}
		return returnVaule;
	}

	private static String cell2CH(String cellValue, boolean zero_flag)
	{

		// 如果zero_flag=true,而且cell2CH的第二个值不是以"千"开头,则应该加上"零";
		// 比如:三万五百六十七 应该修改成 三万零五百六十七
		StringBuffer cellBuffer = new StringBuffer();
		cellValue = removeZero(cellValue);
		int pos = cellValue.length();
		int digit;
		if (pos == 4)
		{
			digit = Character.getNumericValue(cellValue.charAt(0));
			if (digit != 0)
			{
				cellBuffer.append(digits[digit]).append(CARRY_QIAN);
			}
			else
			{
				cellBuffer.append(digits[0]);
			}
			pos = pos - 1;
			cellValue = cellValue.substring(1, 4);
		}
		if (pos == 3)
		{
			digit = Character.getNumericValue(cellValue.charAt(0));
			if (digit != 0)
			{
				cellBuffer.append(digits[digit]).append(CARRY_BAI);
			}
			else
			{
				cellBuffer.append(digits[0]);
			}
			pos = pos - 1;
			cellValue = cellValue.substring(1, 3);
		}
		if (pos == 2)
		{
			digit = Character.getNumericValue(cellValue.charAt(0));
			if (digit != 0)
			{
				cellBuffer.append(digits[digit]).append(CARRY_SHI);
			}
			else
			{
				cellBuffer.append(digits[0]);
			}
			pos = pos - 1;
			cellValue = cellValue.substring(1, 2);
		}
		if (pos == 1)
		{
			digit = Character.getNumericValue(cellValue.charAt(0));
			if (digit != 0)
			{
				cellBuffer.append(digits[digit]);
			}
		}

		if (zero_flag && cellBuffer.length() >= 2
				&& cellBuffer.charAt(1) != '仟')
		{
			cellBuffer.insert(0, "零");
		}
		if (zero_flag && cellBuffer.length() == 1)
		{
			cellBuffer.insert(0, "零");
		}
		return cellBuffer.toString();
	}

	private static String removeZero(String _cellValue)
	{
		String cellValue = _cellValue;
		if (cellValue.startsWith("0"))
		{
			cellValue = cellValue.substring(1);
			return cellValue = removeZero(cellValue);
		}
		else
		{
			return cellValue;
		}
	}

	public static Timestamp getTimestamp(String timestamp, String pattern)
	{
		Timestamp t = null;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);

		try
		{
			t = new Timestamp(sdf.parse(timestamp).getTime());
		}
		catch (Exception e)
		{}

		return t;
	}

	/**
     * 将字符串中的占位符按序替换成param数组中的内容
     * 
     * @param line 源字符串
     * @param flag 占位符
     * @param param 要替换的参数
     * @return 替换后的字符串
     */
	public static String replace(String line, String flag, String[] param)
	{
		ArrayList list = parseStringToArrayList(line, flag);
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0)
		{
			if (!StringUtils.isValid(param, list.size() - 1))
			{
				throw new IllegalArgumentException("param is invalid");
			}

			for (int i = 0; i < list.size(); i++)
			{
				String str = (String)list.get(i);
				sb.append(str);
				if (i < list.size() - 1)
				{
					sb.append(param[i]);
				}
			}

		}
		return sb.toString();
	}

	/**
     * 截取字符串，并判断字符串的有效性
     * 
     * @param line 源字符串
     */
	public static String trimStr(String src)
	{
		if (isValid(src))
		{
			return src.trim();
		}
		else
		{
			return "";
		}
	}

	/**
     * 按号码产生字符串(如: getCurNo(11,8,"0") 将返回"00000011")
     * 
     * @param curno 号码
     * @param length 总长度
     * @param fillStr 填充串
     * @return
     */
	public static String getCurNo(int curno, int length, String fillStr)
	{
		int temp = curno;
		StringBuffer sb = new StringBuffer(length);
		int count = 0;
		while (curno / 10 != 0)
		{
			curno = curno / 10;
			count++;
		}
		int size = length - count - 1;
		for (int i = 0; i < size; i++)
		{
			sb.append(fillStr);
		}
		sb.append(temp);
		return sb.toString();
	}

	/**
     * 判断数组中是否包含指定字符串
     * 
     * @param src
     * @param value
     * @return 包含返回true否则false
     */
	public static boolean containValue(String[] src, String value)
	{
		if (!StringUtils.isValid(src))
		{
			return false;
		}
		boolean result = false;
		for (int i = 0; i < src.length; i++)
		{
			if (src[i].equals(value))
			{
				result = true;
				break;
			}
		}
		return result;
	}

	/**
     * 将map中指定key的value值在指定位置添加符号symbol
     * 
     * @param symbol 要添加的符号
     * @param map
     * @param keys
     * @param position 位置(-1 前面,0 首尾都加,1 尾部)
     * @return Map
     */
	public static Map addSymbol(Map map, String[] keys, char symbol,
			int position)
	{
		Map returnMap = new HashMap();
		ValueUtils.copyValue(returnMap, map);
		Set keySet = returnMap.keySet();
		for (int i = 0; i < keys.length; i++)
		{
			if (keySet.contains(keys[i]))
			{
				String key = keys[i].trim();
				String value = (String)returnMap.get(key);
				if (key == null || key.equals("") || value == null
						|| value.equals(""))
				{
					continue;
				}
				if (position == -1)
				{
					// 在前面添加符号
					returnMap.put(key, String.valueOf(symbol) + value);
				}
				else if (position == 1)
				{
					// 在后面添加符号
					returnMap.put(key, value + String.valueOf(symbol));
				}
				else if (position == 0)
				{
					// 在首尾添加符号
					returnMap.put(key, String.valueOf(symbol) + value
							+ String.valueOf(symbol));
				}
			}
		}
		return returnMap;
	}

	/* MD5加密算法 Added by hhb on 2006-09-01 */
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String byteArrayToHexString(byte[] b)
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
     * MD5加密算法
     * 
     * @param origin 原始的明文
     * @return MD5加密后的密文
     */
	public static String MD5Encode(String origin)
	{
		String resultString = null;

		try
		{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		}
		catch (Exception ex)
		{

		}
		return resultString;
	}
	
	
	public static String md5x(String origin){
		String res1 = MD5Encode(origin);
		String res2 = MD5Encode(origin.substring(5));
		return res1 + res2.substring(17);
	}

	/**
     * 判断字符串中是否含有中文
     * 
     * @param str
     * @return
     */
	public static boolean isChina(String str)
	{
		boolean bool = false;
		Pattern pa = Pattern.compile("[\u4E00-\u9FA0]", Pattern.CANON_EQ);
		Matcher m = pa.matcher(str);
		if (m.find())
		{
			bool = true;
		}
		else
		{
			bool = false;
		}
		return bool;
	}

	public static String strChange(String str_Info)
	{
		int length = str_Info.length();
		int num2 = 0;
		String str = "";
		String str2 = "";

		if (length != 0)
		{
			int num3;
			for (int i = 0; i < length; i++)
			{

				num2 += str_Info.substring((length - i) - 1, (length - i))
						.charAt(0);
			}
			num2 *= 2;
			for (int j = 0; j < length; j++)
			{
				num3 = num2 % 0x1a;
				num2 -= str_Info.substring((length - j) - 1, (length - j))
						.charAt(0);
				str = str + (char)((int)(0x41 + num3));
			}
			int num4 = 12 - length;
			num2 *= 3;
			for (int k = 0; k < num4; k++)
			{
				num3 = num2 % 0x1a;
				num2 -= num3 * 3;
				str2 = str2 + (char)((int)(0x41 + num3));
			}
			return (str2 + str);

		}
		return "";
	}
	
	public static void main(String[] args) {
		String pwd = "admin";
		System.out.println(StringUtils.md5x(pwd));
	}
}
