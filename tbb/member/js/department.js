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
function getDepartment(departmentSrc){
	var xmlHttpDepartment = false;

	//调用ajax处理数据	
	if (!xmlHttpDepartment || xmlHttpDepartment == null)
	{
		xmlHttpDepartment = getXmlHttp();
	}
	else
	{
		xmlHttpDepartment.abort();
	}
	
	if (!xmlHttpDepartment)
	{
		alert("不支持xmlHttp请求!");
		hiddenAlikeDiv(inputId);
		return;
	}		

	//对关键字进行编码
    var url = departmentSrc;
	url += "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttpDepartment.open("GET", url, true);
    xmlHttpDepartment.onreadystatechange = function(){updatePageDepartment(xmlHttpDepartment)};
    xmlHttpDepartment.send(null); 
}

//更新界面
function updatePageDepartment(xmlHttp)
{
	if (xmlHttp.readyState < 4)
	{
    	
    }
    if (xmlHttp.readyState == 4)
	{
		if (xmlHttp.status == 200)
		{	  
			loadDepartment(xmlHttp.responseXML);
		}
	}
}

function loadDepartment(doc)
{
	if(doc == null || doc.documentElement == null)
	{
		alert("xml格式错误!");
		return;
	}

	var options = "<option value=\"0\">一级部门</option>\r\n";

	var root = doc.documentElement;
	var cs = root.childNodes;
	for (var i = 0; i < cs.length; i++) 
	{
		if (cs[i].tagName == "key")
		{			
			options += "<option value=\""+unescape(cs[i].getAttribute("dept_sn"))+"\">"+unescape(cs[i].text)+"</option>\r\n";
		}
	}	
	$("parent_dept").innerHTML = options;
}