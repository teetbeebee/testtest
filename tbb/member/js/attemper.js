//调度使用的js 


//改变中队类型
function changeFireHouseType()
{
	//获取中队类型值
	var fireHouseTypeValue = document.objForm.fireHouseType.value;
	
	//获取属于中队类型的中队列表填充到控件
	document.objForm.fireHouseList.length = 0;
	
	for (var i=0;i<vFireHouseList.length;i++)
	{
		if (vFireHouseList[i][2] == fireHouseTypeValue)
		{
			document.objForm.fireHouseList.options[document.objForm.fireHouseList.length]=new Option(vFireHouseList[i][1],vFireHouseList[i][0]); 
		}
	}
	
	if (document.objForm.fireHouseList.length > 0)
	{
		document.objForm.fireHouseList[0].selected = true;		
	}
	changeFireHouseList();
}

//改变中队
function changeFireHouseList()
{
	document.objForm.fireEngineList.length = 0;
	document.objForm.equipmentList.length = 0;

	if (document.objForm.fireHouseList.selectedIndex != -1)
	{
		//获取中队类型值
    	var fireHouseListValue = document.objForm.fireHouseList.value;
    	
    	//获取属于中队的可用车辆列表填充到控件    	
    	for (var i=0;i<vFireEngineList.length;i++)
    	{
    		if (vFireEngineList[i][0] == fireHouseListValue && vFireEngineList[i][3] == true)
    		{
    			document.objForm.fireEngineList.options[document.objForm.fireEngineList.length]=new Option(vFireEngineList[i][2],vFireEngineList[i][1]); 
    		}
    	}
		
		//获取属于中队的可用装备列表填充到控件    	
    	for (var i=0;i<vEquipmentList.length;i++)
    	{
    		if (vEquipmentList[i][0] == fireHouseListValue && vEquipmentList[i][3] == true)
    		{
    			document.objForm.equipmentList.options[document.objForm.equipmentList.length]=new Option(vEquipmentList[i][2],vEquipmentList[i][1]); 
    		}
    	}
		
		if (document.objForm.fireEngineList.length > 0)
    	{
    		document.objForm.fireEngineList[0].selected = true;		
    	}
		
		if (document.objForm.equipmentList.length > 0)
    	{			
    		document.objForm.equipmentList[0].selected = true;		
    	}
	}	
}

//设置车辆状态
function setFireEngineStatus(fe_id,flag)
{
	for (i=0;i<vFireEngineList.length;i++)
	{
		if (vFireEngineList[i][1] == fe_id)
		{
			vFireEngineList[i][3] = flag;
		}
	}	
}

//设置装备状态
function setEquipmentStatus(equ_id,flag)
{
	for (i=0;i<vEquipmentList.length;i++)
	{
		if (vEquipmentList[i][1] == equ_id)
		{
			vEquipmentList[i][3] = flag;
		}
	}	
}

//设置派车、派装备、移除指派按钮的可用状态
function setButtonEnable()
{
	if (document.objForm.fireEngineList.selectedIndex == -1)
    {
    	document.getElementById("btnAppointFireEngine").disabled = true;		
    }
	else
	{
		document.getElementById("btnAppointFireEngine").disabled = false;		
	}

	if (document.objForm.equipmentList.selectedIndex == -1)
    {
    	document.getElementById("btnAppointEquipment").disabled = true;		
    }
	else
	{
		document.getElementById("btnAppointEquipment").disabled = false;		
	}

	if (!tree.getSelected())
	{
		document.getElementById("btnCancelAppoint").disabled = true;		
	}
	else
	{
		document.getElementById("btnCancelAppoint").disabled = false;
	}
}

//根据中队ID获取中队对象
function getFireHouseById(organ_id)
{
	for (var i=0;i<vFireHouseList.length;i++)
	{
		if (vFireHouseList[i][0] == organ_id)
		{
			return vFireHouseList[i];
		}
	}
	return null;
}

//根据车辆ID获取车辆对象
function getFireEngineById(fe_id)
{
	for (var i=0;i<vFireEngineList.length;i++)
	{
		if (vFireEngineList[i][1] == fe_id)
		{
			return vFireEngineList[i];
		}
	}
	return null;
}

//根据装备ID获取装备对象
function getEquipmentById(equ_id)
{
	for (var i=0;i<vEquipmentList.length;i++)
	{
		if (vEquipmentList[i][1] == equ_id)
		{
			return vEquipmentList[i];
		}
	}
	return null;
}


//初始化操作
function init()
{
	changeFireHouseType();
	//每100毫秒设置派车、派装备按钮的可用状态
	setInterval("setButtonEnable()",100);
}

//初始化
init();

/**====================================树操作========================================================**/
//树的节点类型：0 中队 1 车辆 2 装备 3 车辆Tag 4 装备Tag
//根据中队id获取对应的结点
function getFireHouseNode(organ_id)
{
	var obj = null;
	//遍历一级节点
	for(var i=0; i<tree.childNodes.length; i++)//遍历子节点
	{
	  if (tree.childNodes[i].value == organ_id && tree.childNodes[i].type == 0)
	  {	
		 return tree.childNodes[i];
	  }
	}
	return obj;
}

//根据中队id获取对应的车辆Tag节点
function getFireEngineTagNode(organ_id)
{
	var obj = null;
	
	var fireHouseNode = getFireHouseNode(organ_id);
	if (fireHouseNode == null)
	{
		return null;
	}

	//遍历二级节点
	for(var i=0; i<fireHouseNode.childNodes.length; i++)//遍历子节点
	{
	  if (fireHouseNode.childNodes[i].value == organ_id && fireHouseNode.childNodes[i].type == 3)
	  {	
		 return fireHouseNode.childNodes[i];
	  }
	}
	return obj;
}

//根据中队id获取对应的装备Tag节点
function getEquipmentTagNode(organ_id)
{
	var obj = null;
	
	var fireHouseNode = getFireHouseNode(organ_id);
	if (fireHouseNode == null)
	{
		return null;
	}

	//遍历二级节点
	for(var i=0; i<fireHouseNode.childNodes.length; i++)//遍历子节点
	{
	  if (fireHouseNode.childNodes[i].value == organ_id && fireHouseNode.childNodes[i].type == 4)
	  {	
		 return fireHouseNode.childNodes[i];
	  }
	}
	return obj;
}

//派车操作
function appointFireEngine()
{
	var obj = document.objForm.fireEngineList;

	if (obj.selectedIndex == -1)
	{
		alert("请至少选择一个要指派的车辆!");
		return;
	}

	for (var i=0;i<obj.length;i++)
	{
		if (obj[i].selected == true)
		{
			var fireEngine = getFireEngineById(obj[i].value);
			if (fireEngine != null)
			{
				var fireHouse = getFireHouseById(fireEngine[0]);
				if (fireHouse != null)
				{
					var fireHouseNode = getFireHouseNode(fireHouse[0]);					
					if (fireHouseNode == null)
					{
						fireHouseNode =  new WebFXTreeItem(fireHouse[1]+"@@"+fireHouse[0]+"@@0","javascript:void(0);");
						tree.add(fireHouseNode);
					}
					var fireEngineTagNode = getFireEngineTagNode(fireHouse[0]);
					if (fireEngineTagNode == null)
					{
						fireEngineTagNode =  new WebFXTreeItem("出动车辆@@"+fireHouse[0]+"@@3","javascript:void(0);");
						fireHouseNode.add(fireEngineTagNode);
					}
					var fireEngineNode = new WebFXTreeItem(fireEngine[2]+"@@"+fireEngine[1]+"@@1","javascript:void(0);");
					fireEngineTagNode.add(fireEngineNode);

					//更改当前车辆为不可用
					setFireEngineStatus(fireEngine[1],false);
					//移除车辆
					obj.options[i] = null;
					i= i-1;
				}
			}
		}		
	}
	tree.expandAll();

	if (obj.length > 0)
    {
    	obj[0].selected = true;		
    }
}

//派装备操作
function appointEquipment()
{
	var obj = document.objForm.equipmentList;

	if (obj.selectedIndex == -1)
	{
		alert("请至少选择一个要指派的装备!");
		return;
	}

	for (var i=0;i<obj.length;i++)
	{
		if (obj[i].selected == true)
		{
			var equipment = getEquipmentById(obj[i].value);
			if (equipment != null)
			{
				var fireHouse = getFireHouseById(equipment[0]);
				if (fireHouse != null)
				{
					var fireHouseNode = getFireHouseNode(fireHouse[0]);					
					if (fireHouseNode == null)
					{
						fireHouseNode =  new WebFXTreeItem(fireHouse[1]+"@@"+fireHouse[0]+"@@0","javascript:void(0);");
						tree.add(fireHouseNode);
					}
					var equipmentTagNode = getEquipmentTagNode(fireHouse[0]);
					if (equipmentTagNode == null)
					{
						equipmentTagNode =  new WebFXTreeItem("出动装备@@"+fireHouse[0]+"@@4","javascript:void(0);");
						fireHouseNode.add(equipmentTagNode);
					}
					var equipmentNode = new WebFXTreeItem(equipment[2]+"@@"+equipment[1]+"@@2","javascript:void(0);");
					equipmentTagNode.add(equipmentNode);

					//更改当前装备为不可用
					setEquipmentStatus(equipment[1],false);
					//移除装备
					obj.options[i] = null;
					i= i-1;
				}
			}
		}		
	}
	tree.expandAll();

	if (obj.length > 0)
    {
    	obj[0].selected = true;		
    }
}

//移除指派 nodeType 0 中队 1 车辆 2 装备 3 车辆Tag 4 装备Tag
function cancelAppoint()
{
	if (!tree.getSelected())
	{
		alert("请至少选择一个节点后然后移除指派!");
		return;
	}
	try
	{
		//获取节点
		var node = tree.getSelected();
		switch (node.type)
		{
			case "0":
				//设置车辆状态
				for (var i=0;i<vFireEngineList.length;i++)
				{
					if (vFireEngineList[i][0] = node.value)
					{						
						setFireEngineStatus(vFireEngineList[i][1],true);
					}
				}
				//设置装备状态
				for (var i=0;i<vEquipmentList.length;i++)
				{
					if (vEquipmentList[i][0] = node.value)
					{
						setEquipmentStatus(vEquipmentList[i][1],true);
					}
				}
				tree.getSelected().remove();				
				break;
			case "1":
				//设置车辆状态
				for (var i=0;i<vFireEngineList.length;i++)
				{
					if (vFireEngineList[i][1] = node.value)
					{						
						setFireEngineStatus(vFireEngineList[i][1],true);
						//查看车辆Tag下还有没有车辆，没有就进行删除
						var fireEngineTagNode = getFireEngineTagNode(vFireEngineList[i][0]);
						if (fireEngineTagNode != null)
						{
							fireEngineTagNode.remove();			
						}
						
						break;
					}
				}				
				tree.getSelected().remove();				
				break;
		}
		//将当前取消的车辆还原到控件上
		changeFireHouseList();
	}
	catch(ex)
	{}
}