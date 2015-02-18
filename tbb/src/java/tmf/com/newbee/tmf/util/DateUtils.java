/*
 * Created on 2003-11-2 To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.newbee.tmf.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理公用类
 * 
 *  
 */
public class DateUtils
{
	// 日期类型格式
	public static String DATE_FORMAT = "yyyy-MM-dd";

	// 日期时间类型格式
	public static String DATE_TIME_NO_SECOND_FORMAT = "yyyy-MM-dd HH:mm";

	// 日期时间类型格式
	public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	// 时间戳类型格式
	public static String TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	private DateUtils()
	{}

	/**
     * 把字符串转换成Calendar
     * 
     * @param dateStr(表示日期的字符串如2004-12-12，2004/12/12，2004年12月12日)
     * @return
     */
	public static Calendar parseToCalendar(String dateStr)
	{
		if (StringUtils.isValid(dateStr))
		{
			String dStr = null;
			String tStr = null;
			int pos = dateStr.indexOf(" ");
			if (pos != -1)
			{
				dStr = dateStr.substring(0, pos);
				tStr = dateStr.substring(pos + 1);
			}
			else
			{
				dStr = dateStr;
			}

			Calendar cal = null;
			if (StringUtils.isValid(dStr))
			{
				String[] dArray = null;
				if (dStr.indexOf("/") != -1)
				{
					dArray = StringUtils.SplitString(dStr, "/");
				}
				else if (dStr.indexOf("-") != -1)
				{
					dArray = StringUtils.SplitString(dStr, "-");
				}
				else if (dStr.indexOf("年") != -1)
				{
					int p = dStr.indexOf("年");
					dArray = new String[3];
					dArray[0] = dStr.substring(0, p);

					String temp = dStr.substring(p + 1);

					if (StringUtils.isValid(temp))
					{
						if (temp.indexOf("月") != -1)
						{
							p = temp.indexOf("月");
							dArray[1] = temp.substring(0, p);
							temp = temp.substring(p + 1);
							if (StringUtils.isValid(temp))
							{
								if (temp.indexOf("日") != -1)
								{
									p = temp.indexOf("日");
									dArray[2] = temp.substring(0, p);
								}
								else
								{
									dArray[2] = temp;
								}
							}
						}
					}
				}

				if (StringUtils.isValid(dArray, 3))
				{

					int year = Integer.parseInt(dArray[0]);
					if (year < 1000)
					{
						year += 2000;
					}

					int month = Integer.parseInt(dArray[1]);
					int day = Integer.parseInt(dArray[2]);

					cal = Calendar.getInstance();
					cal.set(Calendar.YEAR, year);
					cal.set(Calendar.MONTH, month - 1);
					cal.set(Calendar.DAY_OF_MONTH, day);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
				}

			}

			if (StringUtils.isValid(tStr))
			{
				String[] tArray = null;
				tArray = StringUtils.SplitString(tStr, ":");
				if (StringUtils.isValid(tArray, 3))
				{
					int hour = Integer.parseInt(tArray[0]);
					int mm = Integer.parseInt(tArray[1]);
					int ss = Integer.parseInt(tArray[2]);
					cal.set(Calendar.HOUR_OF_DAY, hour);
					cal.set(Calendar.MINUTE, mm);
					cal.set(Calendar.SECOND, ss);
				}
			}
			return cal;
		}
		throw new IllegalArgumentException();
	}

	/**
     * 把日期字符串转换成Date
     * 
     * @param dateStr(表示日期的字符串如2004-12-12，2004/12/12，2004年12月12日)
     * @return
     */
	public static Date parseToDate(String dateStr)
	{
		DateFormat df = DateFormat.getDateInstance();
		try
		{
			return df.parse(dateStr);
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
     * 把日期字符串转换成Date
     * 
     * @param dateStr(表示日期的字符串如"2004-12-12"，"2004-12-12 11:30:20"，"2004-12-12
     *            11:30:20.126")
     * @param format 指定所要解析的日期字符串属于那种格式
     * @param dft 当出现异常时要求返回的默认值
     * @return java.util.Date
     */
	public static java.util.Date parseToDate(String dateStr, String format,
			java.util.Date dft)
	{
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = null;
		if (format.equals(DateUtils.DATE_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_FORMAT);
		}
		else if (format.equals(DateUtils.DATE_TIME_NO_SECOND_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_TIME_NO_SECOND_FORMAT);
		}
		else if (format.equals(DateUtils.DATE_TIME_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
		}
		else if (format.equals(DateUtils.TIME_STAMP_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.TIME_STAMP_FORMAT);
		}
		else
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_FORMAT);
		}

		try
		{
			date = sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			date = dft;
		}

		return date;
	}

	public static java.util.Date parseToDate(String dateStr, String format)
			throws ParseException
	{
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = null;
		if (format.equals(DateUtils.DATE_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_FORMAT);
		}
		else if (format.equals(DateUtils.DATE_TIME_NO_SECOND_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_TIME_NO_SECOND_FORMAT);
		}
		else if (format.equals(DateUtils.DATE_TIME_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
		}
		else if (format.equals(DateUtils.TIME_STAMP_FORMAT))
		{
			sdf = new SimpleDateFormat(DateUtils.TIME_STAMP_FORMAT);
		}
		else
		{
			sdf = new SimpleDateFormat(DateUtils.DATE_FORMAT);
		}

		date = sdf.parse(dateStr);

		return date;
	}

	/**
     * 获得当前日期
     * 
     * @return 以YYYY-MM-DD的形式返回
     */
	public static String getNowString()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String result = df.format(new Date());
		// result = StringUtils.replace(result, "年", "-");
		// result = StringUtils.replace(result, "月", "-");
		// result = StringUtils.replace(result, "日", "");
		return result;
	}

	/**
     * 获得当前时间
     * 
     * @return 以YYYY-MM-DD hh:mm:ss的形式返回
     */
	public static String getNowTimeString()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.CHINA);
		String result = df.format(new Date());
		return result;
	}

	/**
     * 格式化日期
     * 
     * @param date Date对象
     * @param mask 格式掩码
     * @return 经过格式化的日期字符串
     */
	public static String formatDate(Date date, String mask)
	{
		if (date == null)
		{
			return "";
		}
		else
		{
			DateFormat df = new SimpleDateFormat(mask);
			return df.format(date);
		}
	}

	/**
     * 把Calendar对象转换成Date对象
     * 
     * @param cal
     * @return
     */
	public static Date calendarToDate(Calendar cal)
	{
		return cal.getTime();

	}

	public static Calendar dateToCal(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
     * 根据Calendar获得相应的中文日期
     * 
     * @param cal
     * @return
     */
	public static String calendarToCN(Calendar cal)
	{
		// final String[] digits =
		// { "零", "壹", "貳", "叁", "肆", "伍", "陆", "柒", "捌", "玖", };

		final String[] digits = {"零", "一", "二", "三", "四", "五", "六", "七", "八",
				"九",};

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		String yearStr = String.valueOf(year);
		String monthStr = String.valueOf(month);
		String dayStr = String.valueOf(day);

		StringBuffer sb = new StringBuffer();
		int t = Character.getNumericValue(yearStr.charAt(0));
		sb.append(digits[t]);
		t = Character.getNumericValue(yearStr.charAt(1));
		sb.append(digits[t]);
		t = Character.getNumericValue(yearStr.charAt(2));
		sb.append(digits[t]);
		t = Character.getNumericValue(yearStr.charAt(3));
		sb.append(digits[t]);
		sb.append("年");
		if (monthStr.length() == 1)
		{
			t = Character.getNumericValue(monthStr.charAt(0));
			sb.append(digits[t]);
		}
		else if (monthStr.length() == 2)
		{
			t = Character.getNumericValue(monthStr.charAt(0));
			if (t != 1)
			{
				sb.append(digits[t]);
			}
			sb.append("十");
			t = Character.getNumericValue(monthStr.charAt(1));
			sb.append(digits[t]);
		}

		sb.append("月");

		if (dayStr.length() == 1)
		{
			t = Character.getNumericValue(dayStr.charAt(0));
			sb.append(digits[t]);
		}
		else if (dayStr.length() == 2)
		{
			t = Character.getNumericValue(dayStr.charAt(0));
			if (t != 1)
			{
				sb.append(digits[t]);
			}
			sb.append("十");
			t = Character.getNumericValue(dayStr.charAt(1));
			sb.append(digits[t]);
		}

		sb.append("日");
		return sb.toString();
	}

	/**
     * 根据Date对象获取相应的中文日期
     * 
     * @param date
     * @return
     */
	public static String dateToCN(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return calendarToCN(cal);
	}

	/**
     * 根据日期字符串获取相应的中文日期
     * 
     * @param str
     * @return
     */
	public static String strToCN(String str)
	{
		Calendar cal = parseToCalendar(str);
		return calendarToCN(cal);
	}

	public static int getMonthLastDay(int year, int month)
	{

		switch (month)
		{
			case 1:
				return 31;
			case 2:
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				{
					return 29;
				}
				else
				{
					return 28;
				}
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			default:
				return 0;
		}
	}
}
