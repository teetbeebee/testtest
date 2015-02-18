package com.tbb.message;

public class CommonMessage
{
    //通知更新报警记录列表
	public static final String updateAlarmList ="<MessageClass>001</MessageClass>"+
                                          "<MessageSubClass>9001</MessageSubClass>"+
                                          "<MessageType>0003</MessageType>"+
                                          "<MessageContent>"+
                                          "<MessageSection Name ='updateAlarmList'></MessageSection>"+
                                          "</MessageContent>"; 
	
    //案件调度消息
	public static final String attemperCase ="<MessageClass>002</MessageClass>"+
	                                         "<MessageSubClass>3002</MessageSubClass>"+
	                                         "<MessageType>0003</MessageType>"+
	                                         "<MessageContent>"+
	                                         "<MessageSection AttemperCase ='$AttemperCase$'></MessageSection>"+
	                                         "</MessageContent>";
    //下发通知给终端
	public static final String notify ="<MessageClass>009</MessageClass>"+
                                          "<MessageSubClass>9001</MessageSubClass>"+
                                          "<MessageType>0003</MessageType>"+
                                          "<MessageContent>"+
                                          "<MessageSection Notify_Sn ='$Notify_Sn$'></MessageSection>"+
                                          "<MessageSection Answer_Sn ='$Answer_Sn$'></MessageSection>"+
                                          "</MessageContent>"; 

	//回馈收到通知
	public static final String notifyAnswer ="<MessageClass>003</MessageClass>"+
                                          "<MessageSubClass>9001</MessageSubClass>"+
                                          "<MessageType>0003</MessageType>"+
                                          "<MessageContent>"+
                                          "<MessageSection Notify_Sn ='$Notify_Sn$'></MessageSection>"+
                                          "<MessageSection Answer_Sn ='$Answer_Sn$'></MessageSection>"+
                                          "<MessageSection Terminal_Id ='$Terminal_Id$'></MessageSection>"+
                                          "<MessageSection Answer_Time ='$Answer_Time$'></MessageSection>"+
                                          "<MessageSection Answer_Person ='$Answer_Person$'></MessageSection>"+
                                          "</MessageContent>";
    //通知所有中队更新车辆和装备列表
	public static final String updateFireEngineAndEquipmentList ="<MessageClass> 001</MessageClass>"+
						                                          "<MessageSubClass>9002</MessageSubClass>"+
						                                          "<MessageType>0003</MessageType>"+
						                                          "<MessageContent>"+
						                                          "<MessageSection Name ='updateFireEngineAndEquipmentList'></MessageSection>"+
						                                          "</MessageContent>"; 
    //通知中队更新出动列表列表
	public static final String updateCommand ="<MessageClass>001</MessageClass>"+
						                      "<MessageSubClass>9003</MessageSubClass>"+
						                      "<MessageType>0003</MessageType>"+
						                      "<MessageContent>"+
						                        "<MessageSection Name ='updateCommand’'></MessageSection>"+
						                      "</MessageContent>"; 
	
	//通知班长席让GPS上线
	public static final String updateGpsOnlineCommand ="<MessageClass>003</MessageClass>"+
						                      "<MessageSubClass>4001</MessageSubClass>"+
						                      "<MessageType>0003</MessageType>"+
						                      "<MessageContent>"+
						                      "<MessageSection Fe_Ids ='$Fe_Ids$'></MessageSection>"+
						                      "</MessageContent>";
	// 通知大屏更新显示页面
	public static final String updateLedCommand ="<MessageClass>007</MessageClass>"+
    				"<MessageSubClass>1007</MessageSubClass>"+
    				"<MessageType>0004</MessageType>"+
    				"<MessageContent>"+
    				"<MessageSection LedHUrl ='$Url_Hori$'></MessageSection>"+
    				"<MessageSection LedSUrl ='$Url_Squr$'></MessageSection>"+    											
    				"</MessageContent>";
	// LED屏亮度调整
	public static final String updateLedBright ="<MessageClass>007</MessageClass>" +
					"<MessageSubClass>2007</MessageSubClass>" +
					"<MessageType>0003</MessageType>" +
					"<MessageContent>" +
					"<MessageSection Bright='$Is_Up$'></MessageSection>" +
					"<MessageSection Value='$Change_Value$'></MessageSection>" +
					"</MessageContent>";

	// LED屏开启自动亮度控制
	public static final String updateLedAuto = "<MessageClass>007</MessageClass>" +
					"<MessageSubClass>3007</MessageSubClass>" +
					"<MessageType>0003</MessageType>" +
					"<MessageContent>" +
					"<MessageSection AutoBright='$Is_Open$'></MessageSection>" +
					"</MessageContent>";
	
	//三台合一中使用警情转移
    public static final String transferAlarm = "<MessageClass>001</MessageClass>" +
                                            "<MessageSubClass>0001</MessageSubClass>" +
                                            "<MessageType>0001</MessageType>" +
                                            "<MessageContent>" +
                                            "<MessageSection SendUser='$SendUser$'></MessageSection>" +
                                            "<MessageSection Tel_Log_Sn='$Tel_Log_Sn$'></MessageSection>" +
                                            "<MessageSection Alarm_type='$Alarm_type$'></MessageSection>" +
                                            "<MessageSection PostData=''><![CDATA[$PostData$]]></MessageSection>" +
                                            "</MessageContent>";
    
    // 异步大屏设置消息
    public static final String asynchroLEDSet = "<MessageClass>007</MessageClass>" +
    				"<MessageSubClass>5007</MessageSubClass>" +
    				"<MessageType>0003</MessageType>" +
    				"<MessageContent>" +
    				"<MessageSection welctext='$welcome_text$'></MessageSection>" +
    				"<MessageSection welcsize='$welcome_size$'></MessageSection>" +
    				"<MessageSection welccolor='$welcome_color$'></MessageSection>" +
    				"<MessageSection welcact='$welcome_act$'></MessageSection>" +
    				"<MessageSection welcstop='$welcome_stop$'></MessageSection>" +
    				"<MessageSection welcspeed='$welcome_speed$'></MessageSection>" +
    				"<MessageSection welcclear='$welcome_clear$'></MessageSection>" +
    				"<MessageSection caseact='$case_act$'></MessageSection>" +
    				"<MessageSection casestop='$case_stop$'></MessageSection>" +
    				"<MessageSection casespeed='$case_speed$'></MessageSection>" +
    				"<MessageSection caseclear='$case_clear$'></MessageSection>" +
    				"</MessageContent>";
    
    // 案件信息及天气更新消息
    public static final String asynchroLEDCase = "<MessageClass>007</MessageClass>"+
					"<MessageSubClass>6007</MessageSubClass>" +
					"<MessageType>0003</MessageType>" +
					"<MessageContent>" +
					"<MessageSection casetext='$case_text$'></MessageSection>" +
					"<MessageSection weathertext='$weather_text$'></MessageSection>" +
					"</MessageContent>";
    
    // 欢迎词更新通知
    public static final String updateWelcome = "<MessageClass>007</MessageClass>"+
					"<MessageSubClass>7007</MessageSubClass>"+
					"<MessageType>0003</MessageType>"+
					"<MessageContent>"+
					"<MessageSection welctext='$welcome_text$'></MessageSection>"+
					"<MessageSection welcsize='$welcome_size$'></MessageSection>"+
					"<MessageSection welccolor='$welcome_color$'></MessageSection>"+
					"</MessageContent>";

    public static final String updateActionSet = "<MessageClass>007</MessageClass>"+
    				"<MessageSubClass>8007</MessageSubClass>"+
     				"<MessageType>0003</MessageType>"+
     				 "<MessageContent>"+
      				"<MessageSection welcact='$welcome_act$'></MessageSection>"+
     				"<MessageSection welcstop='$welcome_stop$'></MessageSection>"+
     				"<MessageSection welcspeed='$welcome_speed$'></MessageSection>"+
     				"<MessageSection welcclear='$welcome_clear$'></MessageSection>"+
     				 "<MessageSection caseact='$case_act$'></MessageSection>"+
      				 "<MessageSection casestop='$case_stop$'></MessageSection>"+
      				 "<MessageSection casespeed='$case_speed$'></MessageSection>"+
      				 "<MessageSection caseclear='$case_clear$'></MessageSection>"+
      				 "</MessageContent>";

    public static final String updateForceUrl = "<MessageClass>007</MessageClass>"+
					"<MessageSubClass>9007</MessageSubClass>"+
	 				"<MessageType>0003</MessageType>"+
	 				"<MessageContent>"+
	 				"<MessageSection forceurl='$force_url$'></MessageSection>"+
	 				"<MessageSection forceact='$force_act$'></MessageSection>" +
	 				"<MessageSection forcestop='$force_stop$'></MessageSection>" +
	 				"</MessageContent>";
    
}
