// ******************************* 使用ajax请求Led需要的数据 *******************************
// 消防站滚动编号
var firehouse = new Array();
// 当前滚动的消防站编号
var curid = 0;
// 当前滚动的页面id
var pageid = 0;
// 页面显示时的延时时长
var delay = 8000;
// 消防站数据是否加载
//var loadorgan = false;

//---------------------- 获取当前消防站列表 ----------------------//
function getOrganID()
{
	//alert("begin");
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps=((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +"/cs/led/LedAction.do?method=get_firehouse_list";
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	// SendRequest("GET", url, data, setOrganID, "");
	XMLHttp.sendReq("GET", url, data, setOrganID);
}

function setOrganID(response)
{
	try
	{	
		//if (response.error != null || response.value == null || 
		//	(typeof(response.value) == "string" && response.value == "") || 
		//	response.value.text == "") {
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {		
			// 没有数据,等待10秒后继续搜寻数据
	        setTimeout("getOrganID()", delay);
			return;
		}
		
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);
    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT");        
        if ((item == null) || (item.length == 0)) {
			// 没有数据重新请求
	        getOrganID();
        	return;
		}
		
        // 清空消防站元素
        firehouse = new Array();
        
        // 通过循环读取各个消防站实力
        for(var i=0;i<item.length;i++)
        {
            var organid = item[i].selectSingleNode("ORGAN").text;
            if (organid == "null") organid = "";
			var name = item[i].selectSingleNode("NAME").text;
			if (name == "null") name = "";
    		var state = item[i].selectSingleNode("STATE").text;
    		if (state == "null") state = "-1";
    		var soldier = item[i].selectSingleNode("SOLDIER").text;
    		if (soldier == "null") soldier = "0";
    		
    		var leader = item[i].selectSingleNode("LEADER").text;
    		if (leader == "null") leader = "";
    		var frontstaff = item[i].selectSingleNode("FRONTSTAFF").text;
    		if (frontstaff == "null") frontstaff = "";
    		var backstaff = item[i].selectSingleNode("BACKSTAFF").text;
    		if (backstaff == "null") backstaff = "";
    		var messenger = item[i].selectSingleNode("MESSENGER").text;
    		if (messenger == "null") messenger = "";
    		
    		if (firehouse[i]==null) { 
	            firehouse[i] = new posfirehouse(organid, name, state, soldier, leader, frontstaff, backstaff, messenger);
            } else {
            	firehouse[i] = posfirehouse(organid, name, state, soldier, leader, frontstaff, backstaff, messenger)
            }
        }
        
        // 准备请求页面数据        
        GoPage();        
        // 指定下个显示的页面
        //pageid++;
		//// 获取每个消防站的实力数据，直到轮寻完毕
		//getEngineList();		
	}
	catch(exception)
	{
		alert("响应消防站数据错误:" + exception.description);
		return;
	}
}

function posfirehouse(organid, name, state, soldier, leader, frontstaff, backstaff, messenger)
{
	// 消防站编号	
	this.OrganID = organid;
	// 消防站名称
	this.Name = name;
	// 消防站状态
	this.State = state;
	// 战斗人数
	this.Soldier = soldier;
	// 中队值班信息
	this.Leader = leader;
	this.Frontstaff = frontstaff;
	this.Backstaff = backstaff;
	this.Messenger = messenger;
}
//更新值班列表
function update_onduty()
{	
		
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=update_onduty";
	var data = null;	// 需要发送的参、值对
	// 请求实力数据
	//SendRequest("GET", url, data, setEngineList, "");
	XMLHttp.sendReq("GET", url, data, setOnduty);
}

function setOnduty(response)
{
	
	try
	{
		if (response == null || (typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
	        curid = 0;
			pageid = 0;
			getOrganID();
			return;
		}
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT"); 
                
        var frame = "";                
        if ((item != null) && (item.length > 0)) {　　
	    //通过循环读取各个消防站实力
         	var on_duty_chief = item[0].selectSingleNode("on_duty_chief").text;
	        var on_duty_assis_chief = item[0].selectSingleNode("on_duty_assis_chief").text;
    	    var on_duty_assistant = item[0].selectSingleNode("on_duty_assistant").text;
    	    var assistants = on_duty_assistant.split(" ");
    	    var assistant1 = on_duty_assistant;
    	    var assistant2 = "";
    	    if(assistants.length>1)
    	    {
    	    	assistant1 = assistants[0] +" "+assistants[1]
    	    }
    	    if(assistants.length>2)  
    	   		assistant2 = assistants[2];
        	var on_duty_clierk = item[0].selectSingleNode("on_duty_clierk").text;
        
        	frame += "<table ><th colspan='4'>总队值班</th><p>&nbsp;</p></th>"
        		  + "<tr><td>总指挥长："
        	      + on_duty_chief+"</td></tr>"
        	      + "<tr><td>副总指挥长（指挥长）："
        	      + on_duty_assis_chief+" </td></tr>"
        	      + "<tr><td>值班助理："
        	      + assistant1+"</td></tr>"
        	 if(assistant2!="")
        	 {
        	      frame+= "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+  assistant2+"</td></tr>"
        	 }
        	 frame += "<tr><td>专职值班员："
        	      + on_duty_clierk  +"</td></tr></table >"
        	      
         	var resDiv = document.getElementById('apDiv2');
		    if(resDiv!=null)
		        resDiv.innerHTML = frame
		}
	}
	catch(exception)
	{
		alert("响应车辆数据错误:" + exception.description);
		return;
	}
}
//---------------------- 获取车辆实力状况 ----------------------//
function getEngineList()
{	
	// 数组没有取到数据,返回上个流程
	if (firehouse[curid] == null) {
		curid = 0;
		pageid = 0;
		getOrganID();
		return;
	}
	
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=get_fire_engine_list&organ_id=" + firehouse[curid].OrganID;
	var data = null;	// 需要发送的参、值对
	// 请求实力数据
	//SendRequest("GET", url, data, setEngineList, "");
	XMLHttp.sendReq("GET", url, data, setEngineList);
}

function setEngineList(response)
{
	try
	{
		//if(response.error != null || response.value == null || 
		//	(typeof(response.value) == "string" && response.value == "") || 
		//	response.value.text == "") {
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
	        curid = 0;
			pageid = 0;
			getOrganID();
			return;
		}
		
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT"); 
                
        var frame = "";                
        if ((item != null) && (item.length > 0)) {　　
	        //通过循环读取各个消防站实力
    	    for (var i=0;i<item.length;i++) {
	            var fe_id = item[i].selectSingleNode("FE_ID").text;
    	        var fe_name = item[i].selectSingleNode("FE_NAME").text;
        	    var fe_type = item[i].selectSingleNode("FE_TYPE").text;
            	var squad_no = item[i].selectSingleNode("SQUAD_NO").text;
	            var fe_state = item[i].selectSingleNode("FE_STATE").text;
            
    	       	frame += "<tr><td class='label'>"+ fe_id +"</td>" +
					"<td class='label'>"+ fe_type +"</td>";
				// 颜色标注
				if (fe_state == "待命") {
					frame += "<td class='greentxt' align='center'>待命</td></tr>";
				} else {
					frame += "<td class='redtxt' align='center'>" +fe_state +"</td></tr>";
				}
        	}
        }
        
       var outtbl = "<table><tr><td class='yellowtxt' colspan='2'><b>" + firehouse[curid].Name + "</b>&nbsp;&nbsp;";
        if (firehouse[curid].State == "-1") {
	        outtbl += "<font class='redtxt'>终端异常</font>";
        } else {
			outtbl += "<font class='greentxt'>终端正常</font>";
        }
        outtbl += "</td><td class='yellowtxt' width='80'> 战斗人数：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Soldier +"</td>" +
			"</tr><tr><td width='80'class='greentxt'>中队前勤：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Frontstaff.replace(/\./, " ")  +"</td>" +
		  	"<td width='80'class='greentxt'>中队后勤：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Backstaff.replace(/\./, " ")  +"</td></tr><tr>" +
		  	"<td width='80'class='greentxt'>值班班长：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Leader.replace(/\./, " ")  +"</td>" +
  			"<td width='80'class='greentxt'>通讯员：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Messenger.replace(/\./, " ")  +"</td>" +
			"</tr></table>";
        
        outtbl += "<table class='maintb'><tr><td class='greentxt'>车辆编号</td>" +
			"<td class='greentxt'>车辆名称</td>" +
			"<td class='greentxt'>车辆状态</td>" +
			"</tr>" + frame + "</table>";
			
		//alert(frame);
		// 切换显示当前页面
		showPage(outtbl);
		// 准备请求页面数据
		setTimeout("GoPage()", delay);
        // 指定下个显示的页面
        pageid++;
	}
	catch(exception)
	{
		alert("响应车辆数据错误:" + exception.description);
		return;
	}
}

//---------------------- 获取装备实力状况 ----------------------//
function getEquipmentList()
{	
	// 数组没有取到数据,返回上个流程
	if (firehouse[curid] == null) {
		curid = 0;
		pageid = 0;
		getOrganID();
		return;
	}
	
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=get_equipment_list&organ_id=" + firehouse[curid].OrganID;
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	//SendRequest("GET", url, data, setEquipmentList, "");
	XMLHttp.sendReq("GET", url, data, setEquipmentList);
}

function setEquipmentList(response)
{
	try
	{
		//if(response.error != null || response.value == null || 
		//	(typeof(response.value) == "string" && response.value == "") || 
		//	response.value.text == "") {
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
			curid = 0;
			pageid = 0;
			getOrganID();
			return;
		}
		
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT");
       
        var frame = "";
        if ((item != null) && (item.length > 0)) {　　
	        //通过循环读取各个消防站实力
	        for(var i=0;i<item.length;i++) {
	            var equ_id = item[i].selectSingleNode("EQU_ID").text;
	            var equ_code = item[i].selectSingleNode("EQU_CODE").text;
	            var equ_name = item[i].selectSingleNode("EQU_NAME").text;
	            var equ_num = item[i].selectSingleNode("EQU_NUM").text;
	            var equ_type = item[i].selectSingleNode("EQU_TYPE").text;
	            //var equ_state = item[i].selectSingleNode("EQU_STATE").text;
	            
	           	frame += "<tr><td class='label'>"+ equ_code +"</td>" +
					"<td class='label'>"+ equ_name +"</td>"+
					"<td class='label' align='center'>"+ equ_type +"</td>" +
					"<td class='label' align='center'>"+ equ_num +"</td>";					
				// 颜色标注1 可用-2 出动-3 维修-4 报废
				//if (equ_state == "出动") {
				//	frame += "<td class='redtxt' align='center'>出动</td></tr>";
				//} else if (equ_state == "可用") {
				//	frame += "<td class='greentxt' align='center'>可用</td></tr>";
				//} else {
				//	frame += "<td class='label' align='center'>" + equ_state +"</td></tr>";
				//}
	        }
        }
        
        // MJ+ 08/01/21
        var outtbl = "<table><tr><td class='yellowtxt' colspan='2' width = '100'><b>" + firehouse[curid].Name + "</b></td><td class='yellowtxt' width='160'>";
        if (firehouse[curid].State == "-1") {
	        outtbl += "<font class='redtxt'>终端异常</font>";
        } else {
			outtbl += "<font class='greentxt'>终端正常</font>";
        }
         
		var arrfs = new Array();
		var arrbs = new Array();
		var arrld = new Array();
		var arrmg = new Array();
		arrfs =  firehouse[curid].Frontstaff.split(",");
		var strfs = "";
		if (arrfs.length > 0) {
			for (i=0; i < arrfs.length; i++)
				strfs += arrfs[i] + "&nbsp;";
		}
			
		arrbs = firehouse[curid].Backstaff.split(",");
		var strbs = "";
		if (arrbs.length > 0) {
			for (i=0; i < arrbs.length; i++)
				strbs += arrbs[i] + "&nbsp;";
		}
			
		arrld = firehouse[curid].Leader.split(",");
		var strld = "";
		if (arrld.length >0) {
			for (i=0; i < arrld.length; i++)
				strld += arrld[i] + "&nbsp;";
		}
		
		arrmg = firehouse[curid].Messenger.split(",");
		var strmg = "";
		if (arrmg.length > 0) {
			for (i=0; i < arrmg.length; i++)
				strmg += arrmg[i] + "&nbsp;";
		}
		
        outtbl += "&nbsp;&nbsp;战斗人数：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Soldier +"</td>" +
			"</tr><tr><td width='80'class='greentxt'>中队前勤：</td><td width='236' class='yellowtxt' colspan = '3'>"+ strfs +"</td>" +
		  	"</tr><tr><td width='80'class='greentxt'>中队后勤：</td><td width='236' class='yellowtxt' colspan = '3'>"+ strbs +"</td>" +
		  	"</tr><tr><td width='80'class='greentxt'>值班班长：</td><td width='236' class='yellowtxt' colspan = '3'>"+ strld +"</td>" +
  			"</tr><tr><td width='80'class='greentxt'>通讯员：</td><td width='236' class='yellowtxt' colspan = '3'>"+ strmg +"</td>" +
			"</tr></table>";
			
        //var outtbl = "<table><tr><td class='yellowtxt' colspan='2'><b>" + firehouse[curid].Name + "</b>&nbsp;&nbsp;";
        //if (firehouse[curid].State == "-1") {
	    //    outtbl += "<font class='redtxt'>终端异常</font>";
        //} else {
		//	outtbl += "<font class='greentxt'>终端正常</font>";
        //}
        //outtbl += "</td><td class='yellowtxt' width='80'> 战斗人数：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Soldier +"</td>" +
		//	"</tr><tr><td width='80'class='greentxt'>中队前勤：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Frontstaff +"</td>" +
		//  	"<td width='80'class='greentxt'>中队后勤：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Backstaff +"</td></tr><tr>" +
		//  	"<td width='80'class='greentxt'>值班班长：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Leader +"</td>" +
  		//	"<td width='80'class='greentxt'>通讯员：</td><td width='78' class='yellowtxt'>"+ firehouse[curid].Messenger +"</td>" +
		//	"</tr></table>";
			
	    outtbl += "<table class='maintb'><tr><td class='greentxt'>装备编号</td>" +
			"<td class='greentxt' >装备名称</td>" +
			"<td class='greentxt' >装备类别</td>" +
			"<td class='greentxt' >数量</td>" +			
			"</tr>" + frame + "</table>";
		
		// 切换显示当前页面
		showPage(outtbl);
		// 准备请求页面数据
		setTimeout("GoPage()", delay);
        // 指定下个显示的页面
        pageid++;
	}
	catch(exception)
	{
		alert("响应装备数据错误！" + exception.description);
		return;
	}
}

// 安全题字
function getAd()
{
	frame = "<div align=center><img src='../../images/ad.gif'/></div>";
	// 切换显示当前页面
	showPage(frame);
	// 准备请求页面数据
	//setTimeout("GoPage()", delay);
	setTimeout("getOrganID()",delay);
    // 指定下个显示的页面
    //pageid++;
}

// 用于跳转显示页面的请求信息
function GoPage()
{
	switch (pageid) {
	case 0:
		// 准备显示车辆信息
		getEngineList();
		break;
	//不显示装备
	//case 1:
	//	// 准备显示装备实力
	//	getEquipmentList();
	//	break;
	//case 2:
	//	// 插入的广告信息
	//	getAd();
	//	break;	
	default:
		curid++;
		pageid = 0;
		// 准备提取下一个消防站数据
		if (curid > firehouse.length-1)	{
			curid = 0;
			// 消防站数据已经完成轮寻，更新消防站数据			
			//getOrganID();
			//alert("end");
			getAd();
		} else {
			// 继续轮训下个消防站信息
			GoPage();
		}
	}
}

//---------------------- 获取指挥中心值班状况 ----------------------//
function getOnDutyList(organid)
{	
	if ((organid == null) || (organid == ""))
		return;
		
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=get_on_duty&organ_id=" + organid;		
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	//SendRequest("GET", url, data, setOnDutyList, "");
	XMLHttp.sendReq("GET", url, data, setOnDutyList);
}

function setOnDutyList(response)
{
	try
	{
		//if(response.error != null || response.value == null || 
		//	(typeof(response.value) == "string" && response.value == "") || 
		//	response.value.text == "") {
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
			return;
		}

		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);
    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT"); 
        // 没有数据显示空状态
        if ((item == null) || (item.length == 0))
        	return ;
        
        var frame = "";    
        for(var i=0;i<item.length;i++)
        {   
        	// 依次带班领导,值班干部,指挥中心值班,值班班长,中队前勤,中队后勤,中队通信员
        	var duty_charge = item[i].selectSingleNode("DUTY_CHARGE").text;
        	var duty_cadre = item[i].selectSingleNode("DUTY_CADRE").text;
        	var duty_center = item[i].selectSingleNode("DUTY_CENTER").text;        	
        	var squad_leader = item[i].selectSingleNode("SQUAD_LEADER").text;
	        var front_staff = item[i].selectSingleNode("FRONT_STAFF").text;
    	    var back_staff = item[i].selectSingleNode("BACK_STAFF").text;
        	var fh_messenger = item[i].selectSingleNode("FH_MESSENGER").text;
		}
		
		var arrdcg = new Array();
		var arrdcd = new Array();
		var ardct = new Array();

		arrdcg =  duty_charge.split(",");
		var strdcg = "";
		for (i=0; i < arrdcg.length; i++)
			strdcg += arrdcg[i] + "&nbsp;";
			
		arrdcd =  duty_cadre.split(",");
		var strdcd = "";
		for (i=0; i < arrdcd.length; i++)
			strdcd += arrdcd[i] + "&nbsp;";
			
		ardct =  duty_center.split(",");
		var strdct = "";
		for (i=0; i < ardct.length; i++)
			strdct += ardct[i] + "&nbsp;";
				
		frame = "<table><tr><td class='yellowtxt' colspan='2'><b>支队值班情况</b></tr>" +
				"<tr><td width='80'class='greentxt'>带班领导：</td>" +
				"<td width='236' class='yellowtxt'>"+ strdcg + "</td></tr>" +
				"<tr><td width='80' class='greentxt'>值班干部：</td>" +
				"<td width='236' class='yellowtxt'>"+ strdcd + "</td></tr>" +
				"<tr><td width='80' class='greentxt'>战备值班：</td>" +
				"<td width='236' class='yellowtxt'>" + strdct + "</td>" +
				"<td></table>";

		// 切换显示当前页面
		showOnduty(frame);		
	}
	catch(exception)
	{
		alert("响应值班数据错误：" + exception.description);
		return;
	}
}

//---------------------- 获取实时警报信息 ----------------------//
function getAlarmInfo()
{	
	// 获取应用服务器地址
	var bigs=document.location.href;
	var name=((bigs.split('//'))[1].split('/'))[0]; 
	var apps= ((bigs.split('//'))[1].split('/'))[1]; 
	
	var url = "http://"+ name +"/"+ apps +
		"/cs/led/LedAction.do?method=get_alarm";		
	var data = null;	// 需要发送的参、值对

	// 请求实力数据
	//SendRequest("GET", url, data, setAlarmInfo, "");
	XMLHttp.sendReq("GET", url, data, setAlarmInfo);
}

function setAlarmInfo(response)
{
	try
	{		
		//if(response.error != null || response.value == null || (typeof(response.value) == "string" && response.value == "") || response.value.text == "") {
		if (response == null || 
			(typeof(response.responseXML.xml) == "string" && response.responseXML.xml == "")) {
			showAlarm("");
			return;
		}
		var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async="false";
    	xmlDoc.loadXML(response.responseXML.xml);
    	
        var item = xmlDoc.selectNodes("ROOT/SEGMENT");
        //if ((item == null) || (item.length == 0))
        //	return ;
        
        var txt = "";
        for(var i=0;i<item.length;i++)
        {   
        	var alarm = item[i].selectSingleNode("ALARM").text;
        	
        	txt += alarm;
		}
		// 切换显示当前页面
		showAlarm(txt);
	}
	catch(exception)
	{
		alert("响应警报数据错误：" + exception.description);
		return;
	}
}