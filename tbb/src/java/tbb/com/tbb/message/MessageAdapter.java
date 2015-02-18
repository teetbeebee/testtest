package com.tbb.message;

import java.io.IOException;

import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlBlaster.client.I_Callback;
import org.xmlBlaster.client.I_ConnectionStateListener;
import org.xmlBlaster.client.I_XmlBlasterAccess;
import org.xmlBlaster.client.key.GetKey;
import org.xmlBlaster.client.key.PublishKey;
import org.xmlBlaster.client.key.SubscribeKey;
import org.xmlBlaster.client.key.UpdateKey;
import org.xmlBlaster.client.qos.ConnectQos;
import org.xmlBlaster.client.qos.DisconnectQos;
import org.xmlBlaster.client.qos.GetQos;
import org.xmlBlaster.client.qos.PublishQos;
import org.xmlBlaster.client.qos.SubscribeQos;
import org.xmlBlaster.client.qos.UpdateQos;
import org.xmlBlaster.client.qos.UpdateReturnQos;
import org.xmlBlaster.util.Global;
import org.xmlBlaster.util.MsgUnit;
import org.xmlBlaster.util.SessionName;
import org.xmlBlaster.util.XmlBlasterException;
import org.xmlBlaster.util.def.Constants;
import org.xmlBlaster.util.dispatch.ConnectionStateEnum;
import org.xmlBlaster.util.qos.address.Destination;

import com.newbee.tmf.config.Config;

class MessageAdapter implements IMessage {
	private static MessageAdapter instance = null;

	private Global global;

	private I_XmlBlasterAccess conn;

	private Config sysParm;

	private Hashtable<String, Hashtable<String, String>> messageAction = null;

	private DocumentBuilder builder = null;

	private Document doc = null;

	private MessageAdapter() {
		// 获取系统相关的配置参数
		sysParm = Config.getInstance();
		// 初始化消息和处理
		Init();
		// 连接消息服务器
		Connect();
	}

	private void Init() {
		messageAction = new Hashtable<String, Hashtable<String, String>>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			builder = factory.newDocumentBuilder();
			String url = Config.ROOT
					+ "/WEB-INF/classes/com/tbb/message/MessageAction.xml";
			doc = builder.parse(new org.xml.sax.InputSource(
					new java.io.FileInputStream(url)));
			doc.normalize();// 去掉XML文档中作为格式化内容的空白而映射在DOM树中的不必要的Text Node对象
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		}
		NodeList links = doc.getElementsByTagName("MessageClass");
		for (int i = 0; i < links.getLength(); i++) {
			Node Classnode;
			Classnode = (Node) links.item(i);
			// 去除非元素接点
			if (Classnode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Hashtable<String, String> Actiontable = new Hashtable<String, String>();
			NamedNodeMap att = Classnode.getAttributes();
			// 处理消息的类名
			String classCode = att.getNamedItem("class").getTextContent();
			// 大类映射的消息Table
			messageAction.put(classCode, Actiontable);
			NodeList sublinks = Classnode.getChildNodes();
			for (int j = 0; j < sublinks.getLength(); j++) {
				Node subnode;
				subnode = (Node) sublinks.item(j);
				if (subnode.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				NamedNodeMap subatt = subnode.getAttributes();
				String subclasscode, actionclass;
				subclasscode = subatt.getNamedItem("subClass").getTextContent();
				actionclass = subnode.getFirstChild().getTextContent();
				// 子类映射Table
				Actiontable.put(subclasscode, actionclass);
			}
		}

	}

	private boolean Connect() {
		String url = Config.ROOT
				+ "/WEB-INF/classes/com/tbb/message/logging.properties";

		// URL url;
		// url = this.getClass().getResource("logging.properties");

		System.setProperty("java.util.logging.config.file", url);
		url = Config.ROOT
				+ "/WEB-INF/classes/com/tbb/message/xmlBlaster.properties";
		String[] args = { "-propertyFile", url, "-session.name",
				sysParm.getUserName(), "-passwd", sysParm.getPassWord(),
				"-bootstrapHostname", sysParm.getMomServerAddr() };
		global = new Global(args);
		conn = global.getXmlBlasterAccess();
		try {

			conn.connect(new ConnectQos(global), new I_Callback() {// 处理消息接收
						public String update(String cbSessionId,
								UpdateKey updateKey, byte[] content,
								UpdateQos updateQos) {
							UpdateReturnQos uq = new UpdateReturnQos(global);
							try {
								if (updateKey.isInternal()) {
									return "";
								}
								String Encoding = updateQos.getClientProperty(
										"Encoding", "");
								String messagetext;
								if (Encoding != null && Encoding != "") {
									messagetext = new String(content, Encoding);
								} else {
									messagetext = new String(content);
								}
								System.out.println(messagetext);
								String classtype[] = MessageHelp.getInstance()
										.getClassType(messagetext).split(";");

								if (messageAction.get(classtype[0]) == null) {
									uq.setState(Constants.STATE_OK);
									return uq.toXml();
								} else {
									Hashtable Actiontable = (Hashtable) messageAction
											.get(classtype[0]);
									if (Actiontable.get(classtype[1]) != null) {
										String className = (String) Actiontable
												.get(classtype[1]);
										Object Actionclass = Class.forName(
												className).newInstance();
										if (Actionclass instanceof AbstractMessagePerformed) {
											if (updateQos.isPtp()) {
												((AbstractMessagePerformed) Actionclass)
														.InitMessage(
																new Boolean(
																		updateQos
																				.isPtp()),
																updateQos
																		.getSender()
																		.getLoginName(),
																messagetext,
																classtype[0],
																classtype[1]);
											}

											else {
												((AbstractMessagePerformed) Actionclass)
														.InitMessage(
																new Boolean(
																		updateQos
																				.isPtp()),
																updateKey
																		.getOid(),
																messagetext,
																classtype[0],
																classtype[1]);
											}
											Thread messagrun = new Thread(
													(Runnable) Actionclass);
											messagrun.start();
										}
									}
								}

							} catch (Exception e) {
								uq.setState(Constants.STATE_FORWARD_ERROR);
								System.out.println("初始化消息件处理列表失败");
							}

							uq.setState(Constants.STATE_OK);
							return uq.toXml();
						}
					});
			conn.subscribe(new SubscribeKey(global, sysParm.getMessageTopic()),
					new SubscribeQos(global));
			conn.registerConnectionListener(new MessageStateListener());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("消息中间件连接失败");
			return false;
		}
		System.out.println("消息中间件重新连接成功");
		return true;
	}

	synchronized public void publish(String topic, String messasge)
			throws MessageException {
		try {
			PublishQos pq = new PublishQos(global);
			pq.addClientProperty("endcoding", "gbk");
			conn.publish(new MsgUnit(new PublishKey(global, topic), messasge,
					pq));
		} catch (Exception e) {
			try {
				disconnect();
				Connect();
				PublishQos pq = new PublishQos(global);
				pq.addClientProperty("endcoding", "gbk");
				conn.publish(new MsgUnit(new PublishKey(global, topic),
						messasge, pq));
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new MessageException("向主题" + topic + "发送消息异常");
			}
		}
	}

	synchronized public void sendPtP(String username, String messasge)
			throws MessageException {
		try {
			PublishQos pq = new PublishQos(global);
			pq.setPersistent(true);
			Destination desc = new Destination(
					new SessionName(global, username));
			desc.forceQueuing(true);
			pq.addDestination(desc);
			pq.addClientProperty("endcoding", "gbk");
			MsgUnit msg = new MsgUnit(new PublishKey(global), messasge, pq);
			conn.publish(msg);
		} catch (Exception e) {
			try {
				disconnect();
				Connect();
				PublishQos pq = new PublishQos(global);
				pq.setPersistent(true);
				Destination desc = new Destination(new SessionName(global,
						username));
				desc.forceQueuing(true);
				pq.addDestination(desc);
				pq.addClientProperty("endcoding", "gbk");
				MsgUnit msg = new MsgUnit(new PublishKey(global), messasge, pq);
				conn.publish(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new MessageException("向用户" + username + "发送消息异常");
			}
		}
	}

	private void disconnect() {
		try {
			conn.disconnect(new DisconnectQos(global));
			global.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	synchronized public void Release() {
		disconnect();
	}

	synchronized public static IMessage getInstance() {
		if (instance == null) {
			instance = new MessageAdapter();
		}
		return instance;
	}

	protected void finalize() throws Throwable {
		super.finalize();
		disconnect();
	}
	
	public final static int defMOMServer_Status_Alive = 1;
	public final static int defMOMServer_Status_Dead = -1;
	public final static int defMOMServer_Status_Polling = -2;
	public final static int defMOMServer_Status_Unknow = 0;
	public final static int defMOMServer_Status_Connect = 0;
	public final static int defMOMServer_Status_Disconnect = -3;
	
	public int getServerStatus()
	{
		if(conn.isAlive())
			return defMOMServer_Status_Alive;
		if(conn.isDead())
			return defMOMServer_Status_Dead;
		if(conn.isPolling())
			return defMOMServer_Status_Polling;
		if(conn.isConnected())
		{
			return defMOMServer_Status_Connect;
		}
		
		disconnect();//需要先断开连接，以免peeding后，消息服务器重启后又自动连接
		Connect();//自动重连消息服务器，由主监视线程控制 bruce 2007.11.7
		return defMOMServer_Status_Disconnect;
	}

	public boolean getUserState(String user) {
		if (conn.isPolling()) {
			return false;
		}
		String cmdLine;
		cmdLine = "__cmd:client/" + user + "/?sessionList";
		GetKey xmlKeyWr = new GetKey(global, cmdLine);
		GetQos xmlQos = new GetQos(global);
		MsgUnit msgs[];
		try {
			msgs = conn.get(xmlKeyWr.toXml(), xmlQos.toXml());
			String msgstring[] = msgs[0].getContentStr().split(",");
			String activeCmd = "__cmd:client/" + user + "/" + msgstring[0]
					+ "/?dispatcherActive";
			xmlKeyWr = new GetKey(global, activeCmd);
			msgs = conn.get(xmlKeyWr.toXml(), xmlQos.toXml());
			String isActive;
			isActive = msgs[0].getContentStr();
			if (isActive.equalsIgnoreCase("true")) {
				return true;
			} else {
				return false;
			}
		} catch (XmlBlasterException e) {
			return false;
		}
	}

	class MessageStateListener implements I_ConnectionStateListener {

		public void reachedAlive(ConnectionStateEnum arg0,
				I_XmlBlasterAccess arg1) {
			System.out.println("消息中间件已经连接");
		}

		public void reachedDead(ConnectionStateEnum arg0,
				I_XmlBlasterAccess arg1) {
			System.out.println("消息中间件已经断开 DEAD");
		}

		public void reachedPolling(ConnectionStateEnum arg0,
				I_XmlBlasterAccess arg1) {
			System.out.println("消息中间件转到离线 POOLING");
			disconnect();//禁止pooling，直接断开，保证没有缓存，全部使用实时消息连接
		}
	}
}
