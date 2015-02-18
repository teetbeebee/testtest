// JavaScript Document
//显示日期
function showDate()
{
	todayDate = new Date();
	date = todayDate.getDate();
	month= todayDate.getMonth() +1;
	year= todayDate.getYear();
	document.write("今天是：&nbsp;&nbsp;")
	if(navigator.appName == "Netscape")
	{
		document.write(1900+year);
		document.write("年");
		document.write(month);
		document.write("月");
		document.write(date);
		document.write("日&nbsp;&nbsp;");
	}
	if(navigator.appVersion.indexOf("MSIE") != -1)
	{
		document.write(year);
		document.write("年");
		document.write(month);
		document.write("月");
		document.write(date);
		document.write("日&nbsp;&nbsp;");
	}
	if (todayDate.getDay() == 5) document.write("星期五")
	if (todayDate.getDay() == 6) document.write("星期六")
	if (todayDate.getDay() == 0) document.write("星期日")
	if (todayDate.getDay() == 1) document.write("星期一")
	if (todayDate.getDay() == 2) document.write("星期二")
	if (todayDate.getDay() == 3) document.write("星期三")
	if (todayDate.getDay() == 4) document.write("星期四")
}

/* 
  跳转菜单,跳转时执行一个链接,或执行函数
  执行链接时，第一个参数为target
  执行函数时，选中的内容要以"javascript:"开头
*/
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  var v = selObj.options[selObj.selectedIndex].value;
  if (v.substring(0,11) == "javascript:") {
    eval(selObj.options[selObj.selectedIndex].value);	
  }
  else {
    eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  }
  if (restore) selObj.selectedIndex=0;
}

/* 
  跳转菜单,跳转时执行javascript函数
*/
function MM_actionJumpMenu(targ,selObj,restore){ //v3.0
  var v = selObj.options[selObj.selectedIndex].value;
  alert("请统一使用MM_jumpMenu方法");
  if (v.substring(0,11) == "javascript:") {
    eval(selObj.options[selObj.selectedIndex].value);	
  }
  else {
    eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  }
  if (restore) selObj.selectedIndex=0;
}

function isIntInput(inputobj, alertstr)
{
	if(inputobj.value == null || inputobj.value == "")
	{
		alert(alertstr + "不能为空!");
		inputobj.focus();
		return false;
	}
	if(!inputobj.value.match(/^\d+$/)){
		alert(alertstr + "必须为整数!")
		inputobj.focus();
		return false;
	}
	return true;
}

function isIntInputOrNull(inputobj, alertstr)
{
	if(inputobj == null)
		return true;
	return isIntInput(inputobj, alertstr)
}


function isIntRangeInput(inputobj, alertstr, min, max)
{
	if(!isIntInput(inputobj, alertstr))
		return false;
	var n = parseInt(inputobj.value);
	if(n>max || n<min)
	{
		alert(alertstr + "范围应该大于" + min + " 小于"+max);
		inputobj.focus();
		return false;
	}
	return true;
}


function isFloatInput(inputobj, alertstr)
{
	if(inputobj.value == null || inputobj.value == "")
	{
		alert(alertstr + "不能为空!");
		inputobj.focus();
		return false;
	}
	if(!inputobj.value.match(/^\d+(\.\d+)?$/)){
		alert(alertstr + "必须为数字!")
		inputobj.focus();
		return false;
	}
	return true;
}

function isFloatRangeInput(inputobj, alertstr, min, max)
{
	if(!isFloatInput(inputobj, alertstr))
		return false;
	var n = parseFloat(inputobj.value);
	if(n>max || n<min)
	{
		alert(alertstr + "范围应该大于" + min + " 小于"+max);
		inputobj.focus();
		return false;
	}
	return true;
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

//查看
function pop(url,winWidth,winHeight){

	var x=(screen.availWidth-winWidth)/2,y=(screen.availHeight-winHeight)/2;//新窗口的左上角的坐标

	var win=window.open (url, "_blank", "height="+winHeight+", width="+winWidth+",left="+x+",top="+y+", toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no");
 	win.focus();
} 

function pop_stat(url,name,winWidth,winHeight){
	var x=(screen.availWidth-winWidth)/2,y=(screen.availHeight-winHeight)/2;//新窗口的左上角的坐标
	var win=window.open (url, name, "height="+winHeight+", width="+winWidth+",left="+x+",top="+y+", toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no");
 	win.focus();
} 

//拨打电话
function call_out(tele_no)
{
	//过滤电话号码，外线加0，内线不加0
	if (tele_no == null)
	{
		tele_no = "";
	}

	if (tele_no.length > 3)
	{
		tele_no = "0" + tele_no;
	}

	window.open("command://call_out?tele_no="+tele_no);
}

//查看人员信息
function view_person(person_sn)
{
	window.open("command://view_person?person_sn="+person_sn);
}

//查看案件信息
function view_all_case(case_sn)
{
	window.open("command://view_all_case?case_sn="+case_sn);
}