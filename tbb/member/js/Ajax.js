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


//ajax方法处理完后调用callbackFun处理返回值
function ajaxMethod(ajaxUrl,responseSuccess,responseFailure)
{
    var xmlHttp = false;
    //调用ajax处理数据	
	if (!xmlHttp || xmlHttp == null)
	{
		xmlHttp = getXmlHttp();
	}
	else
	{
		xmlHttp.abort();
	}
	
	if (!xmlHttp)
	{
		alert("不支持xmlHttp请求!");
		return;
	}	
	
	//对关键字进行编码
    var url = ajaxUrl + "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttp.open("GET", url, true);
    xmlHttp.onreadystatechange = function(){response(xmlHttp,responseSuccess,responseFailure)};
    xmlHttp.send(null); 	
}

//处理响应事件
function response(xmlHttp,responseSuccess,responseFailure)
{
    if (xmlHttp.readyState == 4)
	{
	    if (xmlHttp.status == 200)
		{
		    if (responseSuccess != null)
		    {
		        responseSuccess(xmlHttp);
		    }
		}
		else
		{
		    if (responseFailure != null)
		    {
		        responseFailure();
		    }
		    else
		    {
		        defaultFailure();
		    }
		}
	}
}

function defaultFailure()
{
    alert("系统错误00!");
}