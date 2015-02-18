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
function getTeleGen(telegenSrc){
	var xmlHttpTeleGen = false;

	//调用ajax处理数据	
	if (!xmlHttpTeleGen || xmlHttpTeleGen == null)
	{
		xmlHttpTeleGen = getXmlHttp();
	}
	else
	{
		xmlHttpTeleGen.abort();
	}
	
	if (!xmlHttpTeleGen)
	{
		alert("不支持xmlHttp请求!");
		hiddenAlikeDiv(inputId);
		return;
	}		

	//对关键字进行编码
    var url = telegenSrc;
	url += "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttpTeleGen.open("GET", url, true);
    xmlHttpTeleGen.onreadystatechange = function(){updatePageTeleGen(xmlHttpTeleGen)};
    xmlHttpTeleGen.send(null); 
}

//更新界面
function updatePageTeleGen(xmlHttp)
{
	if (xmlHttp.readyState < 4)
	{
    	
    }
    if (xmlHttp.readyState == 4)
	{
		if (xmlHttp.status == 200)
		{	  
			loadTeleGen(xmlHttp.responseXML);
		}
	}
}

function loadTeleGen(doc)
{
	if(doc == null || doc.documentElement == null)
	{
		alert("xml格式错误!");
		return;
	}

	var root = doc.documentElement;
	var cs = root.childNodes;
	for (var i = 0; i < cs.length; i++) 
	{
		if (cs[i].tagName == "telegen")
		{
			var cschild = cs[i].childNodes;
			for (var j = 0; j < cschild.length; j++) 
			{
				switch (cschild[j].tagName)
				{
					case "tele_owner"://机主姓名
						try
						{
							$("alarm_person").value = unescape(cschild[j].text);
						}
						catch (ex)
						{
						}
						break;						
					case "tele_summary"://装机地址
						try
						{
							$("tele_summary").value = unescape(cschild[j].text);
							//装机地址多半不是灾害地址，所以不使用
							//$("accident_address").value = unescape(cschild[j].text);
							//window.open("command://search?");
						}
						catch (ex)
						{
						}
						break;
					case "mu_objname"://装机地址
						try
						{
							$("accident_unit").value = unescape(cschild[j].text);
							//装机地址多半不是灾害地址，所以不使用
							//$("accident_address").value = unescape(cschild[j].text);
							//window.open("command://search?");
						}
						catch (ex)
						{
						}
						break;
				}
			}
		}
	}	
}