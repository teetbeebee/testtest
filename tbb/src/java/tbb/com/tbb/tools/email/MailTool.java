package com.tbb.tools.email;


/**
 * 
 * 
 */
public class MailTool {
	
	public static void sendmail(String receiveAddress, String content){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.gmail.com");
		mailInfo.setMailServerPort("587");
		mailInfo.setValidate(true);
		mailInfo.setUserName("teetbeebee@gmail.com");
		mailInfo.setPassword("teetbeebee123");// 您的邮箱密码
		mailInfo.setFromAddress("teebeebee@gmail.com");
		mailInfo.setToAddress(receiveAddress);
		mailInfo.setSubject("帐户注册");
		
		mailInfo.setContent(content);
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendHtmlMail(mailInfo);//
	}
	
	public static void main(String[] args){
		String receiveAddress = "neddonkey@126.com";
		String userid = "aaa";
		String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
				"<html>" +
			  "<head>" +
			  "  <title>[vpn] email帐户激活</title>" +
			  "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
			  "</head>" +
			  "<body>" +
			"	<p style='margin-top: 20px;margin-left: 20px;'>您好，<b>"+ receiveAddress +"</b> ：<br/><br/>" +

			"欢迎加入<b>beehii</b>！请点击下面的链接来激活您的帐号。<br/><br/>" +

			"<a target='_blank' href='http://192.168.1.242/tbb/sys/RegisteAction.do?method=activate&code="+userid+"&email="+ receiveAddress +"'>" +
			"http://192.168.1.242/tbb/sys/RegisteAction.do?method=activate&code=" + userid +
			"&email="+receiveAddress+"</a><br/><br/>" +

			"如果您的邮箱不支持链接点击，请将以上链接地址复制到你的浏览器地址栏中认证。<br/><br/>" +

			"请来体验吧！<br/><br/>" +

			"</p>" +

			"  </body>" +
			"</html>";
		MailTool.sendmail(receiveAddress, content);
	}
	
}
