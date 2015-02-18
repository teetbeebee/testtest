//ss全选 tableID:数据列表的ID
function selectAll(tableID){
  var tableObj=null,i,checkBoxObj;
  tableObj=window.document.getElementById(tableID);
  if (tableObj==null) return;

  for (i=1;i<tableObj.rows.length;i++){
	  if (tableObj.rows[i].cells.length > 1)
	  {

    	checkBoxObj=tableObj.rows[i].cells[0].childNodes[0];
    	if (checkBoxObj!=null )  
		  {
				if (checkBoxObj.type && checkBoxObj.type =="checkbox")
				{
					checkBoxObj.checked=true;
				}
		  }
	  }
  }
} 

//ss不选 tableID:数据列表的ID
function selectNone(tableID){
  var tableObj=null,i,checkBoxObj;
  tableObj=window.document.getElementById(tableID);
  if (tableObj==null) return;
  for (i=1;i<tableObj.rows.length;i++){
	  if (tableObj.rows[i].cells.length > 1)
	  {
		checkBoxObj=tableObj.rows[i].cells[0].childNodes[0];
		if (checkBoxObj!=null )  
		  {
				if (checkBoxObj.type && checkBoxObj.type =="checkbox")
				{
					checkBoxObj.checked=false;
				}
		  }   
	  }
  }
} 

//ss反选 tableID:数据列表的ID
function selectReverse(tableID){
  var tableObj=null,i,checkBoxObj;
  tableObj=window.document.getElementById(tableID);
  if (tableObj==null) return;
  for (i=1;i<tableObj.rows.length;i++){
	  if (tableObj.rows[i].cells.length > 1)
	  {
		checkBoxObj=tableObj.rows[i].cells[0].childNodes[0];
		if (checkBoxObj!=null)
		{
			if (checkBoxObj.type && checkBoxObj.type =="checkbox")
			{
					if (checkBoxObj.checked==true)
					{
						checkBoxObj.checked=false;
					}
					else
					{
						checkBoxObj.checked=true;
					}
			}		
		}
	  }
  }
} 

//sdfsdf是否有checkbox选中 tableID:数据列表的ID
function isSelect(tableID){ 
  var tableObj=null,i,checkBoxObj;
  tableObj=window.document.getElementById(tableID);
  if (tableObj==null) return false;
  for (i=1;i<tableObj.rows.length;i++){
	  if (tableObj.rows[i].cells.length > 1)
	  {
		checkBoxObj=tableObj.rows[i].cells[0].childNodes[0];
		if (checkBoxObj!=null)
		{
			if (checkBoxObj.checked==true)
			{
				return true;
			}		
		}	
	  }
  } 
  
  return false;
}

//sdfdsf得到选中checkbox的值 tableID:数据列表的ID
function getSelects(tableID){
  var reStr = "";
  
  var tableObj=null,i,checkBoxObj;
  tableObj=window.document.getElementById(tableID);
  if (tableObj==null) return;
  for (i=1;i<tableObj.rows.length;i++){
	  if (tableObj.rows[i].cells.length > 1)
	  {
    checkBoxObj=tableObj.rows[i].cells[0].childNodes[0];
    if (checkBoxObj!=null)
	{
		if (checkBoxObj.type && checkBoxObj.type =="checkbox")
		{
			if (checkBoxObj.checked==true)
			{
				if(reStr.length>0)
				{
					reStr = reStr + ",";
					reStr = reStr +checkBoxObj.getAttribute("value");
				}
				else
				{
					reStr= checkBoxObj.getAttribute("value");
				}
			}		
		}
	    }
	  }
  }  
  return reStr;
}




