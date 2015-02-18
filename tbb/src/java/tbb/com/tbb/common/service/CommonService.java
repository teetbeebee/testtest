package com.tbb.common.service;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.util.ValueUtils;
import com.tbb.common.dao.CommonDao;

/**
 * <p>
 * Company: DM
 * </p>
 * 
 *  
 * @version 2.0 2006-09-04
 */
public class CommonService extends BaseService
{
	private static CommonService instance = new CommonService();

	public CommonService()
	{
	// empty
	}

	public static CommonService getInstance()
	{
		return instance;
	}

	/**
     * 加工数字num，使其成为长度为width的字符串，再在字符串前面加上年、月 假如传入的参数为1,5，得到的字符串为：20060900001，
     * 如果是100000,5，得到的字符串为：20060900000
     * 
     * @param num
     * @param width
     * @return
     * @throws Exception
     */
	@SuppressWarnings ("unused")
	private String getId(int num, int width) throws Exception
	{
		String year = String.valueOf(ValueUtils.getYear());
		String month = String.valueOf(ValueUtils.getMonth());
		if (month.length() == 1)
		{
			month = "0" + month;
		}
		String id = year + month + ValueUtils.numToString(num, width);
		return id;
	}

	/**
     * 取序列的当前值
     * 
     * @param sequenceName 序列名称
     * @return 序列的当前值
     * @throws Exception
     */
	public Integer getCurrentSequence(String sequenceName) throws Exception
	{
		try
		{
			CommonDao dao = (CommonDao)getDao(CommonDao.class);
			return dao.getCurrentSequence(sequenceName);
		}
		catch (Exception e)
		{
			throw new BaseException("取序列[" + sequenceName + "]的当前值失败！", e);
		}
	}

	/**
     * 取序列的下一个值
     * 
     * @param sequenceName 序列名称
     * @return 序列的下一个值
     * @throws Exception
     */
	public Integer getNextSequence(String sequenceName) throws Exception
	{
		try
		{
			CommonDao dao = (CommonDao)getDao(CommonDao.class);
			return dao.getNextSequence(sequenceName);
		}
		catch (Exception e)
		{
			return new Integer(-1);
			// throw new BaseException("取序列[" + sequenceName + "]的下一个值失败！", e);
		}
	}

}
