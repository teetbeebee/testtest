/**
 * 
 */
package com.newbee.tmf.util;

/**
 * 产生唯一的标识
 */
import java.util.UUID;

public class UUIDGenerator
{
	public static String generatorHexUUID()
	{
		String uuid = UUID.randomUUID().toString();

		return uuid;
	}
}
