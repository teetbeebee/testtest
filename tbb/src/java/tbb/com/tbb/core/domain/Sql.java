package com.tbb.core.domain;


import java.io.UnsupportedEncodingException;

/**
 * 处理大字段 domain
 */
public class Sql {
	
	public String getStringFromBlob() throws UnsupportedEncodingException
	{
		if(blobValue == null){
			return "";
		}
		return  new String(blobValue,"GBK");
	}
	
	public Sql()
	{
		
	}
	
	public Sql(String table,String idFieldName,String idValue,String blobFieldName,byte[] blobValue)
	{
		this.setTable(table);
		this.setIdFieldName(idFieldName);
		this.setIdValue(idValue);
		this.setBlobFieldName(blobFieldName);
		this.setBlobValue(blobValue);
	}

	//操作大字段用
	//表名
	public String table;
	
	//主键名
	public String idFieldName;
	
	//主键值
	public String idValue;
	
	//大字段名称
	public String blobFieldName;
	
	//blob大字段值
	public byte[] blobValue;

	public String getBlobFieldName() {
		return blobFieldName;
	}

	public void setBlobFieldName(String blobFieldName) {
		this.blobFieldName = blobFieldName;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	public void setIdFieldName(String idFieldName) {
		this.idFieldName = idFieldName;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}
	
	
	
}
