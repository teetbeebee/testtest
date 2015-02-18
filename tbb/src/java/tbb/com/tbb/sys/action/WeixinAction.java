package com.tbb.sys.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newbee.tmf.core.ActionContext;
import com.newbee.tmf.core.BaseDispatchAction;
import com.tbb.tools.XmlUtil;

public class WeixinAction extends BaseDispatchAction
{
	public ActionForward weixin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			ActionContext context) throws Exception
	{
		String Token = "glory";
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String[] ArrTmp = { Token, timestamp, nonce };
		Arrays.sort(ArrTmp);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sb.append(ArrTmp[i]);
		}
		String pwd = Encrypt(sb.toString());
		String echostr = request.getParameter("echostr");
		System.out.println("pwd=="+pwd);
		System.out.println("echostr=="+echostr);
		if(pwd.equals(signature)){
			if(!"".equals(echostr) && echostr != null){
				response.getWriter().print(echostr);
			}
		}

		
		return redirect(mapping, form, request, response, context, "registe",
				context.getQueryParams().getQueryMap());
	}
	
	public String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); //to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	
	public boolean isChinese(String str){  
	     boolean result=false;  
	     for (int i = 0; i < str.length(); i++){  
	          int chr1 = (char) str.charAt(i);  
	          if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
	              result = true;  
	          } 
	     }  
	     return result;  
	}
	
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		StringBuffer sb = new StringBuffer();
//		String line;
//		Map<String, String> map = null;
//		NodeDao nd = new NodeDaoImpl();
//		List<TChannelnode> list = null;
//		try {
//			BufferedReader reader = request.getReader();
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//			map = XmlUtil
//					.xml2Map(new String(sb.toString().getBytes(), "UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		sb = new StringBuffer();
//		if (isChinese(map.get("xml.Content"))) {
//			list = nd.getAllChannelnodes(map.get("xml.Content"));
//			if (list.size() == 0) {
//				sb.append("<xml><ToUserName><![CDATA[").append(
//						map.get("xml.FromUserName")).append(
//						"]]></ToUserName><FromUserName><![CDATA[").append(
//						map.get("xml.ToUserName")).append(
//						"]]></FromUserName><CreateTime>").append(
//						map.get("xml.CreateTime")).append(
//						"</CreateTime><MsgType><![CDATA[text]]></MsgType>")
//						.append("<Content><![CDATA[").append("对不起，没有查到您想要的信息！")
//						.append("]]></Content>").append(
//								"<FuncFlag>0</FuncFlag></xml>");
//			} else {
//				sb.append("<xml><ToUserName><![CDATA[").append(
//						map.get("xml.FromUserName")).append(
//						"]]></ToUserName><FromUserName><![CDATA[").append(
//						map.get("xml.ToUserName")).append(
//						"]]></FromUserName><CreateTime>").append(
//						map.get("xml.CreateTime")).append(
//						"</CreateTime><MsgType><![CDATA[text]]></MsgType>")
//						.append("<Content><![CDATA[");
//				for (int i = 0; i < list.size(); i++) {
//					TChannelnode node = list.get(i);
//					sb.append("名称：").append(node.getName()).append("\n")
//							.append("地址：").append(node.getAddress()).append(
//									"\n");
//					if (i != (list.size() - 1)) {
//						sb.append("\n");
//					}
//				}
//				sb.append("]]></Content>").append(
//						"<FuncFlag>0</FuncFlag></xml>");
//			}
//		} else {
//			sb.append("<xml><ToUserName><![CDATA[").append(
//					map.get("xml.FromUserName")).append(
//					"]]></ToUserName><FromUserName><![CDATA[").append(
//					map.get("xml.ToUserName")).append(
//					"]]></FromUserName><CreateTime>").append(
//					map.get("xml.CreateTime")).append(
//					"</CreateTime><MsgType><![CDATA[text]]></MsgType>").append(
//					"<Content><![CDATA[").append("请输入中文！").append(
//					"]]></Content>").append("<FuncFlag>0</FuncFlag></xml>");
//		}
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print(sb.toString());
//	}


//	public static void main(String[] args) {
//	StringBuffer sb = new StringBuffer();
//	NodeDao nd = new NodeDaoImpl();
//	List<TChannelnode> list = nd.getAllChannelnodes("1");
//	System.out.println(list.size());
//	for(TChannelnode node:list){
//		sb.append("名称：").append(node.getName()).append("，")
//		.append("地址：").append(node.getAddress()).append("\n");
//	}
//	sb.append("<xml><ToUserName><![CDATA[")
//    .append("]]></ToUserName><FromUserName><![CDATA[")
//    .append("]]></FromUserName><CreateTime>")
//    .append("</CreateTime><MsgType><![CDATA[text]]></MsgType>")
//    .append("<Content><![CDATA[")
//    .append("对不起，没有查到您想要的信息！")
//    .append("]]></Content>")
// 	.append("<FuncFlag>0</FuncFlag></xml>");
//	WeixinServlet ws = new WeixinServlet();
//	System.out.println(ws.isChinese("1"));
//	System.out.println(sb.toString());
//}


}
