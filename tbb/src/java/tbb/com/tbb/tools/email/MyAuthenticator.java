package com.tbb.tools.email;   
  
import javax.mail.*;   
     
public class MyAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }

	public static void main(String[] args){
		System.out.println("=====开始发送邮件====");
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("admin@beehii.com");
		mailInfo.setPassword("hust4400");// 您的邮箱密码
		mailInfo.setFromAddress("admin@beehii.com");
		mailInfo.setToAddress("lvwei313@126.com");
		mailInfo.setSubject("[beehii] email帐户激活");
		
		String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" +
			"<html>" +
		  "<head>" +
		  "  <title>[beehii] email帐户激活</title>" +
		  "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
		  "</head>" +
		  "<body>" +
		"	<p style='margin-top: 20px;margin-left: 20px;'>您好，<b>"+"lvwei313@126.com"+"</b> ：<br/><br/>" +

		"欢迎加入<b>beehii</b>！请点击下面的链接来激活您的帐号。<br/><br/>" +

		"<a target='_blank' href='http://www.beehii.com/Index.php/User/activate.html?code="+"uuid()"+"&email="+"lvwei313@126.com"+"'>" +
		"http://www.beehii.com/Index.php/User/activate.html?code="+"uuid()"+"&uid="+"lvwei313@126.com"+
		"&email="+"lvwei313@126.com"+"</a><br/><br/>" +

		"如果您的邮箱不支持链接点击，请将以上链接地址复制到你的浏览器地址栏中认证。<br/><br/>" +

		"beehii致力于整理您的生活经历，为您提供便利。<br/><br/>" +

		"请来体验吧！<br/><br/>" +

		"</p>" +

		"  </body>" +
		"</html>";
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
//		sms.sendTextMail(mailInfo);// 发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
		
		System.out.println("=====完成发送邮件====");
	}  
}   