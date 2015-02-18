function move(left,right){
	if (left == null) {alert("源对象为空"); return;}
	if (right == null) {alert("目的对象为空"); return;}
	if (left.selectedIndex == -1) {alert("请至少选择一个项目!");return;}
	
	var index = 0;
	
	while (left.length > 0 && left.selectedIndex > -1)
	{
		index = left.selectedIndex;
		
		var opt = new Option(left.options[index].text, left.options[index].value);
		
		left.options[index] = null;		
		right.options[right.length] = opt;
	}	
}

function selectAllOptions(obj){
	if (obj == null) {alert("对象为空"); return;}
	for (i=0;i<obj.length;i++)
	{
		obj.options[i].selected = true;
	}
}