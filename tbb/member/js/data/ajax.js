/**
 * XMLHttpRequest Object Pool
 *
 * @author    legend <legendsky@hotmail.com>
 * @Copyright www.ugia.cn
 *
 * @Edit	  majun		2007/09/21
 */ 

var XMLHttp = {
    xmlHttpPool: [],

    _getInstance: function ()
    {
		// 返回未初始化、完成请求对象
        for (var i = 0; i < this.xmlHttpPool.length; i ++)
        {
            if (this.xmlHttpPool[i].readyState == 0 || this.xmlHttpPool[i].readyState == 4)
            {
                return this.xmlHttpPool[i];
            }
        }

        // IE5中不支持push方法
        this.xmlHttpPool[this.xmlHttpPool.length] = this._createObj();

        return this.xmlHttpPool[this.xmlHttpPool.length - 1];
    },

    _createObj: function ()
    {
        if (window.XMLHttpRequest)
        {
			// Mozilla 浏览器
            var objXMLHttp = new XMLHttpRequest();
			if (objXMLHttp.overrideMimeType) 
			{
				// 设置MiME类别
				objXMLHttp.overrideMimeType("text/xml");
			}
        }
        else
        {
            var MSXML = ['MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
            for(var n = 0; n < MSXML.length; n ++)
            {
                try
                {
                    var objXMLHttp = new ActiveXObject(MSXML[n]);
                    break;
                }
                catch(e)
                {
					// 浏览器不支持.这里认为总有一个支持，无错误返馈
                }
            }
         }          

        // mozilla某些版本没有readyState属性
        if (objXMLHttp.readyState == null)
        {
            objXMLHttp.readyState = 0;

            objXMLHttp.addEventListener("load", function ()
                {
                    objXMLHttp.readyState = 4;

                    if (typeof objXMLHttp.onreadystatechange == "function")
                    {
                        objXMLHttp.onreadystatechange();
                    }
                },  false);
        }

        return objXMLHttp;
    },

    // 发送请求(方法[post,get], 地址, 数据, 回调函数)
    sendReq: function (method, url, data, callback)
    {
        var objXMLHttp = this._getInstance();
        
		// 在调用 send() 发送请求之前必须设置该属性，这样服务器在回答完成请求之后才能查看该属性
		if(typeof(callback) == 'function') {	
			objXMLHttp.onreadystatechange = function() {
				// 提供当前 HTML 的就绪状态
				if (objXMLHttp.readyState == 4 && (objXMLHttp.status == 200 || objXMLHttp.status == 304))
                {
                	callback(objXMLHttp);
                }				
			}
		}
		
        with(objXMLHttp)
        {
            try
            {
                // 加随机数防止缓存
                if (url.indexOf("?") > 0)
                {
                    url += "&randnum=" + Math.random();
                }
                else
                {
                    url += "?randnum=" + Math.random();
                }

                open(method, url, true);
				
                // 设定请求编码方式
                	setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=GBK');
                send(data);
            }
            catch(e)
            {
                alert(e);
            }
        }
    }
};

/*
示例： 
<script type="text/javascript" src="xmlhttp.js"></script>
<script type="text/javascript">
function test(obj)
{
    alert(obj.statusText);
}

XMLHttp.sendReq('GET', 'http://www.ugia.cn/wp-data/test.htm', '', test);
XMLHttp.sendReq('GET', 'http://www.ugia.cn/wp-data/test.htm', '', test);
XMLHttp.sendReq('GET', 'http://www.ugia.cn/wp-data/test.htm', '', test);
XMLHttp.sendReq('GET', 'http://www.ugia.cn/wp-data/test.htm', '', test);

alert('Pool length:' + XMLHttp._objPool.length);
</script>  

建议：
1、“对象池”的管理功能需要加强，比如池的容量。
2、“对象池”的内存回收需要加强，比如使用完毕的对象释放内存。
3、请求最好序列一下，以控制由于服务器延时或网络延时导致的先请求的后得到结果。
4、为了代码复用和健壮，回调函数应该验证是否有效，也就是回调函数应该可以为空（可以缺省）。
*/