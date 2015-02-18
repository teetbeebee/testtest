//调用方法
//在input中加入
// onKeyUp="getAlike(this,'$link.contextPath/AjaxAction.do?method=get_alike_accident_address');" onfocus="selStart();"


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

//判断是否是ie浏览器	
function isIE()
{
    var sUserAgent = navigator.userAgent;
    var isOpera = sUserAgent.indexOf("Opera") > -1;
    var isIE = sUserAgent.indexOf("compatible") > -1 && sUserAgent.indexOf("MSIE") > -1 && !isOpera;    
}

//获取对象的top的坐标值，返回整数
function getAbsTop(obj) 
{
    var top = obj.offsetTop;
    while (obj = obj.offsetParent) 
    {
        top += obj.offsetTop;
    }
    if(isIE())
    {
        return parseInt(top.toString().replace("px",""),10);
    }
    else
    {
        return top;
    }
}

//获取对象的left的坐标值，返回整数
function getAbsLeft(obj) 
{
    var left = obj.offsetLeft;
    while (obj = obj.offsetParent) 
    {
        left += obj.offsetLeft;
    }
    if(isIE())
    {
        return parseInt(left.toString().replace("px",""),10);
    }
    else
    {
        return left;
    }
}

//判断鼠标是否在对象范围上
function isOnObject(obj)
{
	var left = getAbsLeft(obj);
    var top = getAbsTop(obj);
   	
    var width = obj.offsetWidth;
    var height = obj.offsetHeight;
    	
    var x =window.event.clientX;
    var y = window.event.clientY;
	    
	if ((x>=left && x<=(left+width)) && (y>=top && y<=(top+height)))
    {
	    //在提示区域内
	    return true;
    }
	else
	{
	    return false;
	}
}

//光标停留在文字后
function selStart() 
{ 
	var e = event.srcElement; 
	var r =e.createTextRange(); 
	r.moveStart('character',e.value.length); 
	r.collapse(true); 
	r.select(); 
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
//记录当前选中的项
var alikeIndex = -1;
var suffix = "AlikeDiv";
var splitStr = "@@";

//设置alike的样式
function setAlikeStyle(obj,isSelected)
{
	if (obj != null)
	{
		if (isSelected == true)
		{
			obj.style.paddingLeft = "5px";
			obj.style.fontSize = "13px";
			obj.style.lineHeight = "18px";
			obj.style.fontColor = "#fffffff";
			obj.style.background = "#DFF0F9";
			obj.style.fontWeight = "bold";
		}
		else
		{
			obj.style.paddingLeft = "5px";
			obj.style.fontSize = "13px";
			obj.style.lineHeight = "18px";
			obj.style.fontColor = "#333333";
			obj.style.background = "#ffffff";
			obj.style.fontWeight = "normal";
		}
	}
}


function getAlike(obj,alikeSrc)
{
	var inputId = obj.id;

	if (inputId == "")
	{
		alert("输入框没有id属性");
		return;
	}	
	
	if (obj.onblur == null)
	{
		obj.onblur = function(){blur(inputId);};
	};
	
    //处理按键事件
	handleKeyEvent(inputId,alikeSrc,window.event.keyCode);
}

function blur(inputId)
{
	if ($(inputId+suffix) != null)
	{
		if (isOnObject($(inputId+suffix)))
		{
			$(inputId).focus();
		}
		$(inputId+suffix).style.display = "none";
	}
	//复位初始值
	alikeIndex = -1;
}

//处理按键事件
function handleKeyEvent(inputId,alikeSrc,keyCode)
{
	switch (keyCode)
	{
		case 27: //esc键
			hiddenAlike(inputId);			
    		return;
		case 13: //回车			
			writeValue(inputId);
			hiddenAlike(inputId);					
    		return;
			break;
		case 37: //向左
		case 39://向右
			return;
			break;
		case 38: //向上		
			keyUp(inputId,"up");
			return;
			break;
		case 40://向下		
			keyUp(inputId,"down");
			return;
			break;	
	}

	 var xmlHttpAlike = false;

	if (isEmpty($(inputId).value))
	{
		hiddenAlike(inputId);
		return;
	}

	//调用ajax处理数据	
	if (!xmlHttpAlike || xmlHttpAlike == null)
	{
		xmlHttpAlike = getXmlHttp();
	}
	else
	{
		xmlHttpAlike.abort();
	}
	
	if (!xmlHttpAlike)
	{
		alert("不支持xmlHttp请求!");
		hiddenAlike(inputId);
		return;
	}		

	//对关键字进行编码
	var keyword = escape($(inputId).value);		
    var url = alikeSrc+"&keyword="+keyword;
	url += "&timestamp="+new Date().getTime(); 
	url= encodeURI(url);
	url= encodeURI(url);
	  
    xmlHttpAlike.open("GET", url, true);
    xmlHttpAlike.onreadystatechange = function(){updatePageAlike(inputId,xmlHttpAlike)};
    xmlHttpAlike.send(null); 
}

//写值
function writeValue(inputId)
{
	if (document.frames[inputId + suffix+"Iframe"] != null)
	{	
		var ul = document.frames[inputId + suffix+"Iframe"].document.getElementById(inputId + suffix+"IframeUl");
		if (ul != null)
		{
			var lis = ul.getElementsByTagName("li");
			if (lis != null && lis.length > 0)
			{
				try
				{
					$(inputId).value = lis[alikeIndex].innerHTML;
				}
				catch(ex)
				{}
			}
		}
	}
	do_search();
}

//根据按钮方向处理
function keyUp(inputId,direct)
{
	if ($(inputId + suffix) != null)
	{	
		if ($(inputId + suffix).style.display != "none")
		{
			var ul = document.frames[inputId + suffix+"Iframe"].document.getElementById(inputId + suffix+"IframeUl");
			if (ul != null)
			{
				var lis = ul.getElementsByTagName("li");
				if (lis != null && lis.length > 0)
				{				
					if (direct == "up")
					{
						alikeIndex --;
					}

					if (direct == "down")
					{
						alikeIndex ++;
					}

					if (alikeIndex <0)
					{
						alikeIndex = lis.length -1;
					}
					if (alikeIndex >= lis.length)
					{
						alikeIndex = 0;
					}
					
					for (var i=0;i<lis.length;i++)
					{
						if (i == alikeIndex)
						{
							setAlikeStyle(lis[i],true);
						}
						else
						{
							setAlikeStyle(lis[i],false);
						}
					}
				}
			}
		}
	}	
}

//隐藏Alike，聚焦到原文本框
function hiddenAlike(inputId)
{
	if ($(inputId+suffix) != null)
	{
		$(inputId+suffix).style.display = "none";
	}

	$(inputId).focus();
	
	//复位初始值
	alikeIndex = -1;
}


//更新界面
function updatePageAlike(inputId,xmlHttp)
{
	if (xmlHttp.readyState < 4)
	{
    	hiddenAlike(inputId);
    }
    if (xmlHttp.readyState == 4)
	{
		if (xmlHttp.status == 200)
		{	  	
			var response = xmlHttp.responseText;
			response = unescape(response);
						
			var arr = response.split(splitStr);
			var liStr = "";
			for (var i=0;i<arr.length;i++)
			{
				var vli = "<li  index='"+i+"' onmouseover=\"this.style.backgroundColor='#DFF0F9';this.style.fontColor='#ffffff';this.style.fontWeight='bold';\"   onmouseout=\"this.style.backgroundColor='#ffffff';this.style.fontColor='#333333';this.style.fontWeight='normal';\" onmousedown=\"parent.alikeIndex=this.index;parent.writeValue('"+inputId+"')\">";
				vli += arr[i];			
				vli += "</li>"				
				liStr += vli;
			}	

			if (response == "")
			{
				hiddenAlike(inputId);
				return;
			}
			else
			{				
				if ($(inputId + suffix) == null)
				{
					createAlike(inputId);
				}
				document.frames[inputId + suffix+"Iframe"].document.getElementById(inputId + suffix+"IframeUl").innerHTML = liStr;

				//设置默认样式
				var ul = document.frames[inputId + suffix+"Iframe"].document.getElementById(inputId + suffix+"IframeUl");
				if (ul != null)
				{
					var lis = ul.getElementsByTagName("li");
					if (lis != null && lis.length > 0)
					{				
						for (var i=0;i<lis.length ;i++ )
						{
							setAlikeStyle(lis[i],false);
						}						
					}
				}

				$(inputId + suffix).style.display = "block";
				alikeIndex = -1; //不选中任何相似值
			}
		}
		else
		{
			hiddenAlike(inputId);
		}
	}
}

//创建Alike
function createAlike(inputId)
{
	if ($(inputId+suffix) == null)
	{
		var doc = document;
		var div = doc.createElement("div");
		div.id = inputId + suffix;
		div.style.position = "absolute";
		div.style.left = getAbsLeft($(inputId)) + "px";
		div.style.top = (getAbsTop($(inputId)) + $(inputId).offsetHeight)+ "px";
		div.style.width = $(inputId).offsetWidth+50+"px";	
		div.style.zIndex = "1000";
		div.style.border = "1px solid #cccccc";
		//初始为不可见
		//div.style.display = "none";

		//div里增加一个iframe，避免被select下拉框给遮住
		var iframe = doc.createElement("iframe");
		iframe.id = inputId + suffix+"Iframe";
		iframe.style.width = "100%";			
		iframe.style.height = "600px";	
		iframe.frameBorder ="0";
		div.appendChild(iframe);
		document.body.appendChild(div);
		
		//在Iframe中新增一个ul，用于存储相似值
		var ul = document.frames[inputId + suffix+"Iframe"].document.createElement("ul");
		ul.id =  inputId + suffix+"IframeUl";
		ul.style.border = "0";
		ul.style.margin = "0";
		ul.style.padding = "0";	
		ul.style.listStyle = "none";
		ul.style.overflowY="scroll";
		document.frames[inputId + suffix+"Iframe"].document.appendChild(ul);
		ul = null;
		div = null;
		doc = null;
	}
}


