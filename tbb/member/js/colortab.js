var color_odd="#FFFFFF"; //基数行
var color_even="#F2F5FC"; //偶数行

//设置表格隔行变色
function setTableColor()
{

	var tables = document.getElementsByTagName("table");
	if (tables == null)
	{
		return;
	}

   var table = null;
   var row = null;
	for (var i=0;i<tables.length;i++)
	{
		table = tables[i];
		if (table.className == "maintb")
		{
			var index = 0;
			for (var j=0;j<table.rows.length;j++)
			{
				row = table.rows[j];
				if (row != null && row.cells.length >0)
				{
					var td = row.cells[0];

					if (td.className != "title")
					{
						if (index % 2 == 0)
						{
							row.style.backgroundColor = color_even;
						}
						else
						{
							row.style.backgroundColor = color_odd;
						}
						index ++;	
					}
				}
			}
		}
	}
}

