/****
循环滚动类
参数说明:
		ID		"marquee"	容器ID		(必选)
		Width		(760)	容器可视宽度	(可选,默认值为容器初始设置的宽度)
		Height		(52)	容器可视高度	(可选,默认值为容器初始设置的高度)
		Timer		(50)	定时器		(可选,默认值为30,数值越小,滚动的速度越快,1000=1秒,建议不小于20)
		Step		(1)		滚动的步长	(可选,默认值为2,数值越大,滚动越快)
		WaitTime	(3000)	开始时的等待时间(可选,默认或0为不等待,1000=1秒)
		DelayTime	(5000)	间歇停顿延迟时间(可选,默认为0不停顿,1000=1秒)
		ScrollStep	(52)	间歇滚动间距	(可选,默认为翻屏宽/高度)
****/
function Marquee(ID, Width, Height, Timer, Step, WaitTime, DelayTime, ScrollStep)
{
	// 指定默认值
	this.Width = this.Height = this.WaitTime = this.DelayTime = this.ScrollStep = this.CTL = 0;
	this.Timer = 30;
	this.Step = 1;
	this.textBuf = "";	// 缓存上次显示的文本
	this.textHeight = 0;	// 保存叠加前文本的真实高度
	
	// 读取参数
	this.ID = document.getElementById(arguments[0]);
	if(!this.ID) {
		return;
	}		
	if(typeof arguments[1] == "number")this.Width = arguments[1];
	if(typeof arguments[2] == "number")this.Height = arguments[2];
	if(typeof arguments[3] == "number")this.Timer = arguments[3];
	if(typeof arguments[4] == "number")this.Step = arguments[4];
	if(typeof arguments[5] == "number")this.WaitTime = arguments[5];
	if(typeof arguments[6] == "number")this.DelayTime = arguments[6];	
	if(typeof arguments[7] == "number")this.ScrollStep = arguments[7]

	// 隐藏滚动条
	this.ID.style.overflow = this.ID.style.overflowX = this.ID.style.overflowY = "hidden";
	this.ID.noWrap = true;
	this.IsNotOpera = (navigator.userAgent.toLowerCase().indexOf("opera") == -1);
	
	// 判断参数合法
	if ((arguments.length >= 7) && (this.Step > 0))
		this.Start();
}

// 更新显示内容
Marquee.prototype.InitHtml = function(text)
{
	// 叠加文本，防止跳动
	this.ID.innerHTML = text;
	// 保留源内容真实高度
	this.textHeight = this.ID.scrollHeight;
	// 多屏显示，叠加滚动
	if (this.textHeight > this.Height) {
		this.ID.innerHTML = text + text;		
	}
	this.ID.scrollTop = 0;
}

// 启动滚动计时器
Marquee.prototype.Start = function()
{
	if(this.Timer < 20)this.Timer = 20;
	// 统一滚动况尺寸
	if(this.Width == 0)
		this.Width = parseInt(this.ID.style.width);
	if(this.Height == 0)
		this.Height = parseInt(this.ID.style.height);

	this.ID.style.width = this.Width;
	this.ID.style.height = this.Height;

	var mqobj = this;
	var timer = this.Timer;
	var waittime = this.WaitTime;
	var delaytime = this.DelayTime;
	
	mqobj.StartID = function()
		{
			mqobj.Scroll();
		}
	
	mqobj.Continue = function()
		{
			clearInterval(mqobj.TimerID);
			mqobj.Stop = 0;
			mqobj.CTL = 0;
			mqobj.TimerID = setInterval(mqobj.StartID, timer);
		}

	mqobj.Pause = function()
		{
			mqobj.Stop = 1;
			clearInterval(mqobj.TimerID);
			setTimeout(mqobj.Continue, delaytime);
		}
	
	mqobj.Begin = function()	
		{			
			// 每隔timer调用mqobj.StartID事件
			mqobj.TimerID = setInterval(mqobj.StartID, timer);
		}

	// 等待1秒启动滚动
	setTimeout(mqobj.Begin, waittime);
}

// 计算滚动位置
Marquee.prototype.Scroll = function()
{
	this.CTL += this.Step;
	if ((this.CTL >= this.ScrollStep) && (this.DelayTime > 0)) {
		this.ID.scrollTop += this.ScrollStep + this.Step - this.CTL;
		this.Pause();
		return;
	} else {
		this.ID.scrollTop += this.Step;
		if (this.ID.scrollHeight > this.textHeight + this.Height) {
		/*	if (this.ID.scrollTop >= (this.textHeight + this.Height))
			{			
				this.ID.scrollTop = 0;
			}
		} else { */
			if (this.ID.scrollTop >= this.textHeight + (this.Height - this.ScrollStep))
			{	
				//this.Pause();		
				this.ID.scrollTop = 0;				
			}
		}
	}
	
/*			
	if (this.ID.scrollTop >= this.textHeight) {
		// 滚动完
		this.ID.scrollTop = 0;		
	} else {
		// 继续滚动
		this.ID.scrollTop += this.Step;
	}
	
	document.getElementById("a").value="scrollTop:"+ this.ID.scrollTop + 
		" CTL:"+ this.CTL +
		" scrollHeight:" + this.ID.scrollHeight + 
		" textHeight:" + this.textHeight +
		" clientHeight:"+ this.ID.clientHeight +
		" ScrollStep:"+ this.ScrollStep;	
*/
}