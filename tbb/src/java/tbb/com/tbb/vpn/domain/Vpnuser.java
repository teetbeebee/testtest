package com.tbb.vpn.domain;

public class Vpnuser {
	private java.lang.String user_id;
	private java.lang.String user_name;
	private java.lang.String email;
	private java.lang.String qq;
	private java.lang.String password;
	private java.lang.String session_id;
	private java.lang.Integer user_type;
	private java.lang.Integer state;
	private java.lang.String vpn_server_id;
	private java.lang.Double account;
	private java.sql.Timestamp regtime;
	private java.sql.Timestamp expiretime;

	public java.sql.Timestamp getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(java.sql.Timestamp expiretime) {
		this.expiretime = expiretime;
	}

	public java.sql.Timestamp getRegtime() {
		return regtime;
	}

	public void setRegtime(java.sql.Timestamp regtime) {
		this.regtime = regtime;
	}

	public java.lang.String getUser_id(){
		return user_id;
	}

	public void setUser_id(java.lang.String user_id){
		this.user_id = user_id;
	}
	public java.lang.String getUser_name(){
		return user_name;
	}

	public void setUser_name(java.lang.String user_name){
		this.user_name = user_name;
	}
	public java.lang.String getEmail(){
		return email;
	}

	public void setEmail(java.lang.String email){
		this.email = email;
	}
	public java.lang.String getQq(){
		return qq;
	}

	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
	public java.lang.String getPassword(){
		return password;
	}

	public void setPassword(java.lang.String password){
		this.password = password;
	}

	public java.lang.String getSession_id(){
		return session_id;
	}

	public void setSession_id(java.lang.String session_id){
		this.session_id = session_id;
	}
	public java.lang.Integer getUser_type(){
		return user_type;
	}

	public void setUser_type(java.lang.Integer user_type){
		this.user_type = user_type;
	}
	public java.lang.Integer getState(){
		return state;
	}

	public void setState(java.lang.Integer state){
		this.state = state;
	}
	public java.lang.String getVpn_server_id(){
		return vpn_server_id;
	}

	public void setVpn_server_id(java.lang.String vpn_server_id){
		this.vpn_server_id = vpn_server_id;
	}
	public java.lang.Double getAccount(){
		return account;
	}

	public void setAccount(java.lang.Double account){
		this.account = account;
	}
	
}

