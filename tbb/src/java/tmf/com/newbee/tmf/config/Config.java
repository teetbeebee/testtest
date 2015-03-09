package com.newbee.tmf.config;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.newbee.tmf.config.Config;
import com.newbee.tmf.util.StringUtils;

public class Config {

	private static Config instance = null;

	// 系统绝对根路径
	public static String ROOT = "";

	// 系统虚拟路径
	public static String CONTEXT = "";

	private DocumentBuilder builder = null;

	private Document doc = null;

	private String momServerAddr = "";

	private String userName = "";

	private String passWord = "";

	private String webServer = "";

	private String gisServer = "";
	
	private String gpsServer = "";

	private String messageTopic = "";

	private String messageCustomer = "";

	private String recordServer = "";
	
	private String caseReportDisk = "";
	
	private String caseReportServer = "";
	
	private String currentSystem = ""; //当前启用的系统
	
	private String vpnserver = ""; //vpnserver地址

	public void setCurrentSystem(String currentSystem) {
		this.currentSystem = currentSystem;
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	private Config() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			builder = factory.newDocumentBuilder();
			String address = Config.ROOT + "/WEB-INF/config.xml";
			System.out.println(address);
			doc = builder.parse(new org.xml.sax.InputSource(
					new java.io.FileInputStream(address)));
			doc.normalize();// 去掉XML文档中作为格式化内容的空白而映射在DOM树中的不必要的Text Node对象
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		}

		NodeList links = doc.getElementsByTagName("Config");
		Element temp = (Element) links.item(0);
		links = temp.getChildNodes();
		Node el;
		for (int i = 0; i < links.getLength(); i++) {
			Node textnode;
			el = (Node) links.item(i);
			if (el.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("MomServerAddr") == 0) {
				textnode = el.getFirstChild();
				momServerAddr = textnode.getNodeValue();
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("MessageLoginUser") == 0) {
				textnode = el.getFirstChild();
				userName = textnode.getNodeValue();
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("MessagePassWord") == 0) {
				textnode = el.getFirstChild();
				passWord = textnode.getNodeValue();
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("WebServer") == 0) {
				textnode = el.getFirstChild();
				webServer = textnode.getNodeValue();
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("GisServer") == 0) {
				textnode = el.getFirstChild();
				gisServer = textnode.getNodeValue();
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("GpsServer") == 0) {
				textnode = el.getFirstChild();
				gpsServer = textnode.getNodeValue();
				continue;
			}
			if (el.getNodeName().compareToIgnoreCase("MessageTopic") == 0) {
				textnode = el.getFirstChild();
				messageTopic = textnode.getNodeValue();
			}
			if (el.getNodeName().compareToIgnoreCase("MessageCustomer") == 0) {
				textnode = el.getFirstChild();
				messageCustomer = textnode.getNodeValue();
			}
			if (el.getNodeName().compareToIgnoreCase("RecordServer") == 0) {
				textnode = el.getFirstChild();
				recordServer = textnode.getNodeValue();
			}
			
			if (el.getNodeName().compareToIgnoreCase("CaseReportDisk") == 0) {
				textnode = el.getFirstChild();
				caseReportDisk = textnode.getNodeValue();
			}
			if (el.getNodeName().compareToIgnoreCase("CaseReportServer") == 0) {
				textnode = el.getFirstChild();
				caseReportServer = textnode.getNodeValue();
			}
			if (el.getNodeName().compareToIgnoreCase("currentSystem") == 0) {
				textnode = el.getFirstChild();
				currentSystem = textnode.getNodeValue();
			}
			if (el.getNodeName().compareToIgnoreCase("vpnserver") == 0) {
				textnode = el.getFirstChild();
				vpnserver = textnode.getNodeValue();
			}
		}
	}

	public String getPassWord() {
		return passWord;
	}

	public String getMomServerAddr() {
		return momServerAddr;
	}

	public String getUserName() {
		return userName;
	}

	public String getGisServer() {
		return gisServer;
	}

	public String getWebServer() {
		return webServer;
	}

	public String getMessageTopic() {
		return messageTopic;
	}

	public String getMessageCustomer() {
		return messageCustomer;
	}

	public void setMessageCustomer(String messageCustomer) {
		this.messageCustomer = messageCustomer;
	}

	public String getRecordServer() {
		return recordServer;
	}

	public String getGpsServer() {
		return gpsServer;
	}

	public String getCaseReportDisk() {
		return caseReportDisk;
	}

	public String getCaseReportServer() {
		return caseReportServer;
	}
	
	public String getCurrentSystem() {
		return currentSystem;
	}
	
	public String getVpnserver() {
		return vpnserver;
	}
	
	public String getParam(String paramname){
		if(!StringUtils.isValid(paramname)){
			return "";
		} else if(paramname.equalsIgnoreCase("CurrentSystem")){
			return this.getCurrentSystem();
		} else{
			return "";
		}
	}
}
