// ******************************* 使用ajax请求Led需要的数据 *******************************
function getCase()
{
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps=((bigs.split('//'))[1].split('/'))[1]; 
		
	var url = "http://"+ name +"/"+ apps +"/cs/led/LedAction.do?method=get_all_case_list";
	var data = '';	// 需要发送的参、值对
	// 请求实力数据
	//SendRequest("GET", url, data, setCase, "");
	XMLHttp.sendReq("GET", url, data, setCase);	
}

function setCase(response)
{
	try
	{		
		//if(response.error != null || response.value == null || 
		//	(typeof(response.value.xml) == "string" && response.value == "") || response.value.text == "") {
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
			showCase(null, null);
			return;
		}

		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);

    	//选定节点
        var item = xmlDoc.selectNodes("ROOT/SEGMENT"); 
        // 没内容时的处理
        if ((item == null) && (item.length == 0)) {
			showCase(null, null);
			return;
		}

        // 向服务器请求案件数据        
        var caseframe = ""; 
        //通过循环组合内容
        var timeArray = new Array();
        var stringArray = new Array();

        for(var i=0;i<item.length;i++)
        {
            var curr = item[i].selectSingleNode("CURR").text;
            if (curr == "null") curr = "";
			var addr = item[i].selectSingleNode("ADDR").text;
			if (addr == "null") addr = "";
			var type = item[i].selectSingleNode("TYPE").text;
			if (type == "null") type = "";
			var tele = item[i].selectSingleNode("TELE").text;
			if (tele == "null") tele = "";
			var area = item[i].selectSingleNode("AREA").text;
			if (area == "null") area = "";
			
			timeArray[i] = curr;
			
			caseframe = "<td><span id='scrolltime" + i + "'>" + curr + "</span></td><td>" + addr;
            if (type != "") caseframe += '(' + type + ')';
            caseframe += '<span class="greentxt">' + tele + '</span></td><td>' + area + '</td>';
			stringArray[i] = caseframe;
        }
        
		// 显示获取的数据
	    showCase(timeArray, stringArray);
	}
	catch(exception)
	{
		alert("响应案件数据错误:"+ exception.description);
		return;
	}
}

//---------------------- 获取案件统计数据 ----------------------//
function getCaseNum()
{
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps=((bigs.split('//'))[1].split('/'))[1]; 
		
	var url = "http://"+ name +"/"+ apps +"/cs/led/LedAction.do?method=get_case_count";
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	
	//SendRequest("GET", url, data, caseNumCallback, "");	
	XMLHttp.sendReq("GET", url, data, setCaseCount);	
}

function setCaseCount(response)
{
	try
	{	
		//if(response.error != null || response.value == null || 
		//	 (typeof(response.value.xml) == "string" && response.value == "") || response.value.text == "") {		
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {		
			return;
		}
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);
    	
    	//选定节点
        var item = xmlDoc.selectNodes("ROOT/SEGMENT"); 
        //没内容时的处理
        if ((item == null) || (item.length == 0)) return;
        
        // 向服务器请求案件数据        
        var caseframe = ""; 
        //通过循环组合内容
        for(var i=0;i<item.length;i++)
        {
            var fire = item[i].selectSingleNode("FIRE").text;
            if (fire == "null") fire = "";
			var rescue = item[i].selectSingleNode("RESCUE").text;
			if (rescue == "null") rescue = "";
			var duty = item[i].selectSingleNode("DUTY").text;
			if (duty == "null") duty = "";
			var social = item[i].selectSingleNode("SOCIAL").text;
			if (social == "null") social = "";
			var other = item[i].selectSingleNode("OTHER").text;
			if (other == "null") other = "";
			
			//alert(fire + "-" + rescue + "-" + duty + "-" + social + "-" + other)			
			var count = parseInt(fire,10) + parseInt(rescue,10) + parseInt(duty,10) + 
				parseInt(social,10) + parseInt(other,10);
			if (i == 0)
			{
            	caseframe += "<td align='left'>全天 "+ count + "次</td>";            	
            } else {
            	caseframe += "<td align='left'>&nbsp;&nbsp;本月 "+ count + "次</td>";
            }
            //var allother = parseInt(duty,10) + parseInt(social,10) + parseInt(other,10);
            caseframe += "<td align='left'>火灾 "+ fire + "次</td>"+
            	"<td align='left'>救援 "+ rescue + "次</td>"+
            	"<td align='left'>其它 "+ other + "次</td>";
        }
        
		// 显示获取的数据
		if (caseframe != "") {
		    setCaseNum(caseframe);			    
		}
	}
	catch(exception)
	{
		alert("响应统计案件数据错误:"+ exception.description);
		return;
	}
}

//---------------------- 获取欢迎词 ----------------------//
function getWelcomeInfo()
{	
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=get_welcome";		
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	//SendRequest("GET", url, data, setWelcomeInfo, "");
	XMLHttp.sendReq("GET", url, data, setWelcomeInfo);		
}

function setWelcomeInfo(response)
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
        
        var welcome = "";
        var font = 0;
        //var fontsize = 0;
        var fontcolor = 0;
        var direct = 0;		// 默认        
        for(var i=0;i<item.length;i++)
        {
        	welcome = item[i].selectSingleNode("WELCOME").text;
        	font =  parseInt(item[i].selectSingleNode("FONT").text,10);
        	//fontsize = parseInt(item[i].selectSingleNode("FONTSIZE").text,10);
        	fontcolor = parseInt(item[i].selectSingleNode("FONTCOLOR").text,10);
        	direct = parseInt(item[i].selectSingleNode("DIRECT").text, 10);        	
		}
		
		// 切换显示当前页面
		showWelcome(welcome, font, fontcolor, direct);
	}
	catch(exception)
	{
		alert("响应欢迎数据错误！");
		return;
	}
}