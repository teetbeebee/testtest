package com.tbb.message;

public interface IMessage
{
	public void publish(String topic,String messasge) throws MessageException;
	public void sendPtP(String username,String messasge) throws MessageException;
	public boolean getUserState(String user);
	public int getServerStatus();
}
