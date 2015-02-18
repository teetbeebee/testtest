package com.tbb.message;

public class MessageException extends Exception 
{
   //该序列号需要用serialver.exe 进行生成
   private static final long serialVersionUID = 8918520602768516031L;
   public MessageException(String messagtext)
   {
      super(messagtext);     
   }
}
