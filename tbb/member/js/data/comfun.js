// *********************************** 常用函数 ***********************************

//获取XML文档中节点的值
window.getNodeValue=function(obj,tag)
{
	return obj.getElementsByTagName(tag)[0].firstChild.nodeValue;
}

// 移出特殊字符
function String.prototype.Trim() 
{
	return this.replace(/(^\s*)|(\s*$)/g,"");
}
