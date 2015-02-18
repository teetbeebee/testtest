package com.tbb.tools;

import java.io.ByteArrayInputStream;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class RemoteProcHelp {

	/*
	 * 根据action返回的xml片断封装为ActionReturnValue ActionReturnValue结构 1、成功：
	 * IsSuccess：true Value：List(Hashtable) Hashtable中所有的键名均为大写 2、失败：
	 * IsSuccess：false ErrorCode：错误代码 Error：错误消息
	 */
	@SuppressWarnings("unchecked")
	public static RemoteProcReturnValue getRemoteProcReturnValue(
			String xmlContent) throws ParserConfigurationException {
		RemoteProcReturnValue actionReturnValue = new RemoteProcReturnValue();
		@SuppressWarnings("unused")
		HashMap returnValue = new HashMap();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = null;

		try {
			doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes()));
		} catch (Exception ex) {
			actionReturnValue.setSuccess(false);
			actionReturnValue.setErrorCode("0");
			actionReturnValue.setErrorMessage("系统内部错误!");
			return actionReturnValue;
		}

		Element root = doc.getDocumentElement();
		String result = root.getAttribute("result");
		if (result != null && result.equals("ERROR")) {
			actionReturnValue.setSuccess(false);

			NodeList errorList = root.getElementsByTagName("error");
			if (errorList.getLength() > 0) {
				Element error = ((Element) errorList.item(0));
				if (error.getAttribute("code") != null) {
					actionReturnValue.setErrorCode(error.getAttribute("code"));
					actionReturnValue.setErrorMessage(error.getFirstChild().getNodeValue());
				} else {
					actionReturnValue.setErrorCode("0");
					actionReturnValue.setErrorMessage("系统内部错误!");
				}
			} else {
				actionReturnValue.setErrorCode("0");
				actionReturnValue.setErrorMessage("系统内部错误!");
			}

			return actionReturnValue;
		}

		List<HashMap> actionValue = new ArrayList<HashMap>();

		NodeList recs = doc.getElementsByTagName("rec");
		for (int i = 0; i < recs.getLength(); i++) {
			Element rec = (Element) recs.item(i);
			
			HashMap table = new HashMap();
			
			NodeList keys = rec.getElementsByTagName("key");			
			for (int j=0;j<keys.getLength();j++)
			{
				Element key = (Element)keys.item(j);
				if (!key.getAttribute("name").equals(""))
				{
					String name =key.getAttribute("name");
					
					String value = key.getFirstChild().getNodeValue();
		
					name = name.toUpperCase();
					table.put(name, value);
				}
			}
			
			actionValue.add(table);
		}

		actionReturnValue.setSuccess(true);
		actionReturnValue.setValue(actionValue);

		return actionReturnValue;
	}
}
