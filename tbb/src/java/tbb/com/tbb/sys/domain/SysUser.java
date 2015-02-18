package com.tbb.sys.domain;

public class SysUser {
	
	private java.lang.String user_id;

	private java.lang.String organ_id;

	private java.lang.String user_name;

	private java.lang.String user_organ_id;

	private java.lang.String password;

	private java.lang.Integer state;

	private java.lang.Integer is_sys;

	private java.lang.String note;
	
	private java.lang.String qq;
	
	private java.lang.String email;
	
	private java.lang.String session_id;
	
	private java.sql.Timestamp operate_time;

	public java.lang.String getUser_id(){
		return user_id;
	}

	public void setUser_id(java.lang.String user_id){
		this.user_id = user_id;
	}

	public java.lang.String getOrgan_id(){
		return organ_id;
	}

	public void setOrgan_id(java.lang.String organ_id){
		this.organ_id = organ_id;
	}

	public java.lang.String getUser_name(){
		return user_name;
	}

	public void setUser_name(java.lang.String user_name){
		this.user_name = user_name;
	}

	public java.lang.String getUser_organ_id(){
		return user_organ_id;
	}

	public void setUser_organ_id(java.lang.String user_organ_id){
		this.user_organ_id = user_organ_id;
	}

	public java.lang.String getPassword(){
		return password;
	}

	public void setPassword(java.lang.String password){
		this.password = password;
	}

	public java.lang.Integer getState(){
		return state;
	}

	public void setState(java.lang.Integer state){
		this.state = state;
	}

	public java.lang.Integer getIs_sys(){
		return is_sys;
	}

	public void setIs_sys(java.lang.Integer is_sys){
		this.is_sys = is_sys;
	}

	public java.lang.String getNote(){
		return note;
	}

	public void setNote(java.lang.String note){
		this.note = note;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getSession_id() {
		return session_id;
	}

	public void setSession_id(java.lang.String session_id) {
		this.session_id = session_id;
	}

	public java.sql.Timestamp getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(java.sql.Timestamp operate_time) {
		this.operate_time = operate_time;
	}

	public java.lang.String getQq() {
		return qq;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	

	
}

