//调用方法
/*=======================公用函数===========================================*/
//得到html页面中id标识为id的元素
function $()
{
	var elements = new Array();
	for (var i = 0; i < arguments.length; i++)
	{
		var element = arguments[i];
		if (typeof element == 'string')
		{
			element = document.getElementById(element);
			if (arguments.length == 1)	return element;
			elements.push(element);
		}
	}
	return elements;
}

//字符串是否为空
function isEmpty(strInput)
{
	if (null == strInput)
	{
		return true;
	}

	if (strInput.replace(/(^[ |　|\s]*)|([ |　|\s]*$)/g,"").length == 0)
	{
		return true;
	}
	return false;
}

//获取xmlHttp
function getXmlHttp()
{
    var _xmlHttp = false;
	try 
	{
        _xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    } 
    catch (ex)
    {
        try
        {
            _xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (ex) 
        {
            _xmlHttp = false;
        }
    }

    if (!_xmlHttp && typeof(XMLHttpRequest) != "undefined")
    {
        try
        {
            _xmlHttp = new XMLHttpRequest();
        }
        catch (ex) 
        {
            _xmlHttp = false;
        }
    }
    
    return _xmlHttp;
}

/*=======================相似值获取函数===========================================*/
function getOnDuty(onDutySrc){
	var xmlHttpOnDuty = false;

	//调用ajax处理数据	
	if (!xmlHttpOnDuty || xmlHttpOnDuty == null)
	{
		xmlHttpOnDuty = getXmlHttp();
	}
	else
	{
		xmlHttpOnDuty.abort();
	}
	
	if (!xmlHttpOnDuty)
	{
		alert("不支持xmlHttp请求!");
		hiddenAlikeDiv(inputId);
		return;
	}		

	//对关键字进行编码
    var url = onDutySrc;
	url += "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttpOnDuty.open("GET", url, true);
    xmlHttpOnDuty.onreadystatechange = function(){updatePageOnDuty(xmlHttpOnDuty)};
    xmlHttpOnDuty.send(null); 
}

//更新界面
function updatePageOnDuty(xmlHttp)
{
	if (xmlHttp.readyState < 4)
	{
    	
    }
    if (xmlHttp.readyState == 4)
	{
		if (xmlHttp.status == 200)
		{	  
			loadOnDuty(xmlHttp.responseXML);
		}
	}
}

function loadOnDuty(doc)
{
	if(doc == null || doc.documentElement == null)
	{
		//alert("xml格式错误!");
		return;
	}

	var on_duty_charge = "";
	var on_duty_charge_name = "";

	var on_duty_cadre = "";
	var on_duty_cadre_name = "";

	var on_duty_commander= "";
	var on_duty_commander_name = "";

	var on_duty_center = "";
	var on_duty_center_name = "";

	var on_duty_pro = "";
	var on_duty_pro_name = "";

	var fh_front_staff = "";
	var fh_front_staff_name = "";

	var fh_back_staff = "";
	var fh_back_staff_name = "";

	var fh_squad_leader = "";
	var fh_squad_leader_name = "";

	var fh_messenger = "";
	var fh_messenger_name = "";

	var root = doc.documentElement;
	var cs = root.childNodes;
	for (var i = 0; i < cs.length; i++) 
	{
		if (cs[i].tagName == "center")
		{
			var cschild = cs[i].childNodes;
			for (var j = 0; j < cschild.length; j++) 
			{
				switch (cschild[j].tagName)
				{
					case "on_duty_charge"://值班领导
						on_duty_charge = unescape(cschild[j].text);							
						break;		
					case "on_duty_charge_name":
						on_duty_charge_name = unescape(cschild[j].text);					
						break;		
					case "on_duty_cadre"://行政值班
						on_duty_cadre = unescape(cschild[j].text);						
						break;	
					case "on_duty_cadre_name":
						on_duty_cadre_name = unescape(cschild[j].text);						
						break;	
					case "on_duty_commander"://指挥长
						on_duty_commander = unescape(cschild[j].text);						
						break;	
					case "on_duty_commander_name":
						on_duty_commander_name = unescape(cschild[j].text);						
						break;	
					case "on_duty_center"://战勤值班
						on_duty_center = unescape(cschild[j].text);						
						break;	
					case "on_duty_center_name":
						on_duty_center_name = unescape(cschild[j].text);						
						break;	
					case "on_duty_pro"://专职值班
						on_duty_pro = unescape(cschild[j].text);						
						break;	
					case "on_duty_pro_name":
						on_duty_pro_name = unescape(cschild[j].text);						
						break;	
				}
			}

			setOnDuty(on_duty_charge,on_duty_charge_name,on_duty_cadre,on_duty_cadre_name,on_duty_commander,on_duty_commander_name,on_duty_center,on_duty_center_name,on_duty_pro,on_duty_pro_name);
		}

		if (cs[i].tagName == "firehouse")
		{
			var cschild = cs[i].childNodes;
			for (var j = 0; j < cschild.length; j++) 
			{
				switch (cschild[j].tagName)
				{
					case "fh_front_staff"://中队前勤
						fh_front_staff= unescape(cschild[j].text);									
						break;
					case "fh_front_staff_name":
						fh_front_staff_name= unescape(cschild[j].text);									
						break;
					case "fh_back_staff"://中队后勤
						fh_back_staff = unescape(cschild[j].text);									
						break;				
					case "fh_back_staff_name":
						fh_back_staff_name = unescape(cschild[j].text);									
						break;				
					case "fh_squad_leader"://值班班长
						fh_squad_leader = unescape(cschild[j].text);									
						break;
					case "fh_squad_leader_name":
						fh_squad_leader_name = unescape(cschild[j].text);									
						break;
					case "fh_messenger"://中队通信员
						fh_messenger = unescape(cschild[j].text);					
						break;	
					case "fh_messenger_name":
						fh_messenger_name = unescape(cschild[j].text);					
						break;
				}
			}

			setOnDuty(fh_front_staff,fh_front_staff_name, fh_back_staff,fh_back_staff_name, fh_squad_leader,fh_squad_leader_name,fh_messenger,fh_messenger_name);
		}

		
	}	
}