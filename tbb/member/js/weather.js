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
function getWeather(weatherSrc){
	var xmlHttpWeather = false;

	//调用ajax处理数据	
	if (!xmlHttpWeather || xmlHttpWeather == null)
	{
		xmlHttpWeather = getXmlHttp();
	}
	else
	{
		xmlHttpWeather.abort();
	}
	
	if (!xmlHttpWeather)
	{
		alert("不支持xmlHttp请求!");
		hiddenAlikeDiv(inputId);
		return;
	}		

	//对关键字进行编码
    var url = weatherSrc;
	url += "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttpWeather.open("GET", url, true);
    xmlHttpWeather.onreadystatechange = function(){updatePageWeather(xmlHttpWeather)};
    xmlHttpWeather.send(null); 
}

//更新界面
function updatePageWeather(xmlHttp)
{
	if (xmlHttp.readyState < 4)
	{
    	
    }
    if (xmlHttp.readyState == 4)
	{
		if (xmlHttp.status == 200)
		{	  
			loadWeather(xmlHttp.responseXML);
		}
	}
}

function loadWeather(doc)
{
	setNoPng();

	if(doc == null || doc.documentElement == null)
	{
		//alert("xml格式错误!");
		return;
	}

	var temperature = "";
	var humidity = "";
	var wind_condition="";
	var wind_speed = "";
	var condition = "";
	var condition_dscr = "";

	var low_temp= "";
	var high_temp ="";
	var forecastaction = "";

	var hour = 0;

	var root = doc.documentElement;
	var cs = root.childNodes;
	for (var i = 0; i < cs.length; i++) 
	{
		if (cs[i].tagName == "weather")
		{
			var cschild = cs[i].childNodes;
			for (var j = 0; j < cschild.length; j++) 
			{
				switch (cschild[j].tagName)
				{
					case "temperature"://当前温度
						temperature = unescape(cschild[j].text);					
						break;						
					case "humidity"://当前湿度
						humidity = unescape(cschild[j].text);						
						break;	
					case "wind_condition"://当前风向
						wind_condition = unescape(cschild[j].text);						
						break;	
					case "wind_speed"://当前风力
						wind_speed = unescape(cschild[j].text);					
						break;
					case "condition"://天气情况
						condition = unescape(cschild[j].text);					
						break;	
					case "condition_dscr"://天气情况描述
						condition_dscr = unescape(cschild[j].text);					
						break;	
					case "hour"://当时小时时间
						hour = unescape(cschild[j].text);					
						break;	
				}
			}

			setWeather(temperature,humidity,wind_condition,wind_speed,condition,condition_dscr,hour);
		}

		if (cs[i].tagName == "weatherforecast")
		{
			var cschild = cs[i].childNodes;
			for (var j = 0; j < cschild.length; j++) 
			{
				switch (cschild[j].tagName)
				{
					case "low_temp"://最低温度
						low_temp= unescape(cschild[j].text);									
						break;
					case "high_temp"://最高温度
						high_temp = unescape(cschild[j].text);									
						break;				
					case "forecastcondition"://天气情况
						forecastcondition = unescape(cschild[j].text);									
						break;
					case "condition_dscr"://天气情况描述
						condition_dscr = unescape(cschild[j].text);					
						break;	
					case "hour"://当时小时时间
						hour = unescape(cschild[j].text);					
						break;	
				}
			}

			setWeatherForecast(low_temp,high_temp,forecastcondition,condition_dscr,hour);
		}

		
	}	
}