package com.tbb.vpn.domain;

public class Vpnline {
	private java.lang.String line_id;
	private java.lang.String ip;
	private java.lang.String port;
	private java.lang.String servicetype;
	private java.lang.String linename;
	private java.sql.Timestamp start_time;
	private java.sql.Timestamp expire_time;

	public java.lang.String getLine_id(){
		return line_id;
	}

	public void setLine_id(java.lang.String line_id){
		this.line_id = line_id;
	}
	public java.lang.String getIp(){
		return ip;
	}

	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
	public java.lang.String getPort(){
		return port;
	}

	public void setPort(java.lang.String port){
		this.port = port;
	}
	public java.lang.String getServicetype(){
		return servicetype;
	}

	public void setServicetype(java.lang.String servicetype){
		this.servicetype = servicetype;
	}
	public java.lang.String getLinename(){
		return linename;
	}

	public void setLinename(java.lang.String linename){
		this.linename = linename;
	}
	public java.sql.Timestamp getStart_time(){
		return start_time;
	}

	public void setStart_time(java.sql.Timestamp start_time){
		this.start_time = start_time;
	}
	public java.sql.Timestamp getExpire_time(){
		return expire_time;
	}

	public void setExpire_time(java.sql.Timestamp expire_time){
		this.expire_time = expire_time;
	}
}


