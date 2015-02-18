package com.tbb.message;

public class MessageFactory 
{
    public static IMessage CreateMessage()
    {
    	return MessageAdapter.getInstance();
    }
}
