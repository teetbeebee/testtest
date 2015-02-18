//---------------------- 获取欢迎词 ----------------------//
function getUserHtml()
{	
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=get_userhtml";		
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	//SendRequest("GET", url, data, setWelcomeInfo, "");
	XMLHttp.sendReq("GET", url, data, setUserHtml);		
}

function setUserHtml(response)
{
	try
	{		
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
			// (typeof(response.value.xml) == "string" && response.value == "") || response.value.text == "") {		
			//showWelcome("", "left");
			return;
		}

		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);
    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT");
        //if ((item == null) || (item.length == 0))
        //	return ;
        
        var userhtml = "";
        for(var i=0;i<item.length;i++)
        {
        	userhtml = item[i].selectSingleNode("USERHTML").text;    	
		}
		
		// 切换显示当前页面
		showUser(userhtml);
	}
	catch(exception)
	{
		alert("响应自定义脚本错误！");
		return;
	}
}