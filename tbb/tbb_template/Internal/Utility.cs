using System;

public class Utility{
	public static String vo_name = "vpnuser";
	public static String cn_name = "vpn用户";
	public static String attributes = "user_id,user_name,email,qq,password,regtime,expiretime,session_id,user_type,state,vpn_server_id,account";
	public static String cn_attributes = "用户编号,用户名,邮件,qq,密码,注册时间,过期时间,session_id,用户类型,状态,vpn服务器id,账户余额";
	public static String types = "String,String,String,String,String,Timestamp,Timestamp,String,Integer,Integer,String,Double";
	
	public static String test(){
		return "hello";
		}
		
	public static string UpCaseFirst(string strIn){
		return strIn.Substring(0,1).ToUpper() + strIn.Substring(1);
		}

	public static string[] Split(string strIn){
		return strIn.Split(',');
		}

	public static string GetVo_name(){
		return vo_name;
		}

	public static string GetCn_name(){
		return cn_name;
	}
		
	public static string GetAttributes(){
		return attributes;
	}

	public static string GetCn_attributes(){
		return cn_attributes;
	}
	
	public static string GetTypes(){
		return types;
	}
}