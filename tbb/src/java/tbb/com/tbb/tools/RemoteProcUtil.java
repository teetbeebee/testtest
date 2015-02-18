package com.tbb.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.newbee.tmf.config.*;

import javax.servlet.http.HttpServletResponse;


/*
 * 使用说明
//返回错误
	RemoteProcUtil rpu = new RemoteProcUtil();
	rpu.outputError(response);
	return null;

//返回成功
//返回一条记录的，结果返回后用hashmap方式封装
	RemoteProcUtil rpu = new RemoteProcUtil();
	rpu.addValue("键名1", "键值");
	rpu.addValue("键名2", "键值");
	rpu.addValue("键名3", "键值");
	rpu.output(response);
	return null;
// 返回多条记录，结果以列表方式返回，每个列表里面一个hashmap
	RemoteProcUtil rpu = new RemoteProcUtil();
	for (int i =0; i<100; i++) {
		int ix = rpu.addValue(-1, "fe_id", String.valueOf(i));//这里的-1表示是增加一条新的记录
		rpu.addValue(ix, "organ_id", String.valueOf(i) );
		rpu.addValue(ix, "fe_name", String.valueOf(i));
	}
	arv.output(response);
	return null;
//也可以把构造好的hashmap直接放入
	RemoteProcUtil rpu = new RemoteProcUtil();
	Map m = new HashMap();
	m.put("键1","value1");
	m.put("键2","value2");
	rpu.addValue(m);//增加2个
	rpu.addValue(m);
	arv.output(response);
	return null;
 */
/**
 * 返回结果封装为xml文档片断
 */
public class RemoteProcUtil {

	private List lstRec;
	
	public RemoteProcUtil() {
		lstRec = new ArrayList();
	}
	
	@SuppressWarnings("unchecked")
	public int addValue(Map valuemap){//直接覆盖
		lstRec.add(valuemap);
		return lstRec.size()-1;
	}
	
	@SuppressWarnings("unchecked")
	//在一个rec中，增加map的一个属性，如果不存在的时候，则在最后增加一条rec，然后返回这条rec的index
	//因此第一个add的时候，需要:index=-1
	public int addValue(int index, String key, String value){
		Map m = null;
		if(index>=0 && index<lstRec.size()){
			m = (Map) lstRec.get(index);
		}
		if(null == m){
			m = new HashMap();
			m.put(key, value);
			lstRec.add(m);//如果list总长度为2，list.add(10,xxx) 会不会报错？还是自动扩展
			return lstRec.size()-1;			
		}
		m.put(key, value);
		return index;
	}

	//为了方便当hashmap传参数使用,一直使用第一个rec的hashmap
	public void addValue(String key, String value){
		addValue(0, key, value);//一直是增加第一个
	}
		
	public String getXmlContent() {
		String xmlContent = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n" +
				"<?xml-stylesheet type='text/xsl' href='"+ Config.CONTEXT +"/xsl_ok.vm'?>" +
				"<return result=\"OK\">";

		for(int i=0; i<lstRec.size(); i++){
			xmlContent += "<rec index=\""+ String.valueOf(i) +"\">";
			Map m = (Map) lstRec.get(i);
			if(null!=m){
				Iterator itvalue = m.keySet().iterator();
				while(itvalue.hasNext()){
					String key = (String) itvalue.next();
					String value = (String) m.get(key);
					xmlContent += "<key name=\""+key+"\"><![CDATA["+value+"]]></key>";				
				}	
			}
			xmlContent += "</rec>";	
		}
		xmlContent += "</return>";
		return xmlContent;
	}

		
	private void putHeader(HttpServletResponse response){			
		response.setContentType("text/xml;charset=GBK");		
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
	}
	
	/**
	 * 输出执行成功代码，返回值可以使用addValue进行添加
	 * @param response
	 * @throws IOException
	 */
	public void output(HttpServletResponse response) throws IOException
	{
		putHeader(response);
		PrintWriter out = response.getWriter();
		
		System.out.println(getXmlContent());
		
		out.println(getXmlContent());
		out.close();
	}
	
	/**
	 * 输出错误信息
	 * @param response
	 * @param code 错误代码
	 * @param error 错误信息
	 * @throws IOException
	 */
	public void outputError(HttpServletResponse response, String code, String error) throws IOException{
		putHeader(response);
		PrintWriter out = response.getWriter();
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n" +
				"<?xml-stylesheet type='text/xsl' href='"+ Config.CONTEXT +"/xsl_error.vm'?>" +
				"<return result=\"ERROR\">" +
				"<error code=\""+code+"\">" +
						"<![CDATA["+error+"]]>" +
				"</error>" +
				"</return>";
		
		System.out.println(xml);

		out.println(xml);
		out.close();	
	}
	
	/**
	 * 输出错误信息
	 * @param response
	 * @throws IOException
	 */
	public void outputError(HttpServletResponse response) throws IOException{
		outputError(response, "0", "系统内容错误");
	}
	
	/**
	 * 输出错误信息
	 * @param response 
	 * @param error 错误说明
	 * @throws IOException
	 */
	public void outputError(HttpServletResponse response,String error) throws IOException{
		outputError(response, "9000", error);
	}
	
	
}
