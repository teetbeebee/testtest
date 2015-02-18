//WZW.Math.add = add;				//add(n1,n2) = n1+n2
//WZW.Math.subtract = subtract;	//subtract(n1,n2) = n1-n2
//WZW.Math.multiply = multiply;	//multiply(n1,n2) = n1*n2
//WZW.Math.divide = divide;		//divide(n1,n2) = n1/n2
//WZW.Math.mod = mod;				//mod(n1,n2) = n1%n2
//WZW.Math.round = round;			//round(n1,n2) = n1保留n2位小数

//两数相加: n1 + n2
function add(n1,n2)
{
	if (isNaN(n1) || isNaN(n2))
	{
		return 0;
	}
	var rate = rateMax(n1,n2);
	return ((___parseInt(n1*rate) + ___parseInt(n2*rate))/rate);
}

//两数相减: n1 - n2
function subtract(n1,n2)
{
	if (isNaN(n1) || isNaN(n2))
	{
		return 0;
	}
	var rate = rateMax(n1,n2);
	return ((___parseInt(n1*rate) - ___parseInt(n2*rate))/rate);
}

//两数相乘: n1 * n2
function multiply(n1,n2)
{
	if (isNaN(n1) || isNaN(n2))
	{
		return 0;
	}
	var rate = rateMax(n1,n2);
	return ((___parseInt(n1*rate) * ___parseInt(n2*rate))/Math.pow(rate,2));
}

//两数相除: n1 / n2
function divide(n1,n2)
{
	if (isNaN(n1) || isNaN(n2))
	{
		return 0;
	}
	var rate = rateMax(n1,n2);
	return ((___parseInt(n1*rate) / ___parseInt(n2*rate))/rate);
}

//两数相除取余: n1 % n2
function mod(n1,n2)
{
	if (isNaN(n1) || isNaN(n2))
	{
		return 0;
	}
	var rate = rateMax(n1,n2);
	return ((___parseInt(n1*rate) % ___parseInt(n2*rate))/rate);
}

//四舍五入保留小数: n1保留n2位小数
function round(n1,n2){
	if (isNaN(n1))
	{
		return 0;
	}
	if (isNaN(n2) || n2 < 0)
	{
		n2 = 0;
	}
	return Math.round(parseFloat(n1) * Math.pow(10,n2))/Math.pow(10,n2);
}

//将浮点数转为整数应该放大的倍数
function rate(n)
{	
	if (isNaN(n))
	{
		return 1;
	}

	var n = n.toString();

	if (n.indexOf(".") == -1)
	{
		return 1;
	}	
	return Math.pow(10,___parseInt(n.length - n.indexOf(".") - 1));
}

//将两个浮点数转为整数应该放大的倍数的最大值
function rateMax(n1,n2)
{
	if (rate(n1) > rate(n2))
	{
		return rate(n1);
	}

	return rate(n2);
}

//数字转换成整型
function ___parseInt(n)
{
	if (isNaN(n))
	{
		return 0;
	}
	return parseInt(n,10);
}