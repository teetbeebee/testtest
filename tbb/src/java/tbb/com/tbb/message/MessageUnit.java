package com.tbb.message;
import java.util.Map;
public class MessageUnit 
{
	private Map attMap;
	private String nodeValue = "";
	public Map getAttMap() 
	{
		return attMap;
	}
	public void setAttMap(Map attMap) 
	{
		this.attMap = attMap;
	}
	public void setNodeValue(String nodeValue)
	{
		this.nodeValue = nodeValue;
	}
	public String getNodeValue()
	{
		return nodeValue;
	}


	
}
