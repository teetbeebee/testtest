package com.tbb.message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MessageHelp 
{
  private static MessageHelp instance = null;
  private DocumentBuilder builder= null;
  private Document doc=null;
  private MessageHelp()
  {
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      builder=factory.newDocumentBuilder();
    }
    catch(ParserConfigurationException e)
    {
      System.out.println(e.getMessage());
    }
  }
  public static MessageHelp getInstance()
  {
    if (instance == null)
    {
      instance = new MessageHelp();
    }
    return instance;
  }
  //返回大类代码和小类代码中间以";"进行分割
  synchronized public String getClassType(String message)
  {
      String classtype="";
      String tempmessage;
      try
      {
         tempmessage="<Message>"+ message+"</Message>";
         doc=builder.parse(new ByteArrayInputStream(tempmessage.getBytes()));     
         doc.normalize();//去掉XML文档中作为格式化内容的空白而映射在DOM树中的不必要的Text Node对象
         NodeList links;
         links= doc.getElementsByTagName("MessageClass");
         for(int i=0;i<links.getLength();i++)
         {
            if ((links.item(i).getFirstChild().getTextContent()=="\n")||
                (links.item(i).getFirstChild().getTextContent().trim()==""))
            {
               continue;
            }
            else
            {
              classtype = links.item(i).getFirstChild().getTextContent().trim();     
            }
         }
         
         links= doc.getElementsByTagName("MessageSubClass");
         for(int i=0;i<links.getLength();i++)
         {
            if ((links.item(i).getFirstChild().getTextContent()=="\n")||
               (links.item(i).getFirstChild().getTextContent().trim()=="")) 
            {
               continue;
            }
            else
            {
              classtype = classtype+";"+links.item(0).getFirstChild().getTextContent().trim();     
            }
         }
         
         
      }
      catch(IOException e)
      {
        System.out.println(e.getMessage());
      }
      catch(SAXException e)
      {
        System.out.println(e.getMessage());
      }
      return classtype;
  }
  //生成以MessageContent为节点的子节点列表
  synchronized public NodeList getMessageContent(String message)
  {
      String tempmessage;
      NodeList links = null;
      try
      {
         tempmessage="<Message>"+ message+"</Message>";
         doc=builder.parse(new ByteArrayInputStream(tempmessage.getBytes()));     
         doc.normalize();//去掉XML文档中作为格式化内容的空白而映射在DOM树中的不必要的Text Node对象
         links= doc.getElementsByTagName("MessageContent");
         links= links.item(0).getChildNodes();
      }
      catch(IOException e)
      {
        System.out.println(e.getMessage());
      }
      catch(SAXException e)
      {
        System.out.println(e.getMessage());
      }
      return links;
  }
  
  synchronized public MessageUnit getMessageUnit(Node node)
  {
	  if (node.getNodeName().toUpperCase().equalsIgnoreCase("MESSAGECONTENT"))
	  {
		   return null;
	  }
	  Map<String, String> attMap= new HashMap<String, String>();
	  MessageUnit unit = new MessageUnit();
	  if (node.getNodeName().toUpperCase().equalsIgnoreCase("MESSAGESECTION"))
	  {
		   NamedNodeMap nodemap = node.getAttributes();
		   for(int j= 0;j<nodemap.getLength();j++)
		   {
			   Node childnode = nodemap.item(j);
			   attMap.put(childnode.getNodeName().toUpperCase(), childnode.getNodeValue());
		   }
		   Node textnode = node.getFirstChild();
		   if (textnode!=null)
		   {
			   unit.setNodeValue(textnode.getNodeValue());
		   }
		   unit.setAttMap(attMap);
	  }
	  return unit;
	  
  }
}
