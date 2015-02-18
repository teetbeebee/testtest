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
function getTerminal(terminalSrc){
	var xmlHttpTerminal = false;

	//调用ajax处理数据	
	if (!xmlHttpTerminal || xmlHttpTerminal == null)
	{
		xmlHttpTerminal = getXmlHttp();
	}
	else
	{
		xmlHttpTerminal.abort();
	}
	
	if (!xmlHttpTerminal)
	{
		alert("不支持xmlHttp请求!");
		hiddenAlikeDiv(inputId);
		return;
	}		

	//对关键字进行编码
    var url = terminalSrc;
	url += "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttpTerminal.open("GET", url, true);
    xmlHttpTerminal.onreadystatechange = function(){updatePageTerminal(xmlHttpTerminal)};
    xmlHttpTerminal.send(null); 
}

//更新界面
function updatePageTerminal(xmlHttp)
{
	if (xmlHttp.readyState < 4)
	{
    	
    }
    if (xmlHttp.readyState == 4)
	{
		if (xmlHttp.status == 200)
		{	  
			loadTerminal(xmlHttp.responseXML);
		}
	}
}

function loadTerminal(doc)
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
		if (cs[i].tagName == "terminal")
		{
			var cschild = cs[i].childNodes;
			for (var j = 0; j < cschild.length; j++) 
			{
				switch (cschild[j].tagName)
				{
					case "terminalstate"://状态
						try
						{
							$("terminalstate").innerHTML = unescape(cschild[j].text);
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