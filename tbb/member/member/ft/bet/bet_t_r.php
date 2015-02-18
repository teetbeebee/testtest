<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <link href="/style/ft_ex/mem_order_ft.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="/js/ex/football_order.js"></script></head>

<body id="OFU" class="bodyset" onselectstart="self.event.returnValue=false" oncontextmenu="self.event.returnValue=false;window.event.returnValue=false;">
<form name="LAYOUTFORM" action="/app/member/FT_order/FT_order_r.php" method="post" onsubmit="return false">
    <div class="ord">
        <div class="title"><h1>足球 - 早盘</h1><div class="tiTimer" onclick="orderReload();"><span id="ODtimer">10</span><input type="checkbox" id="checkOrder" onclick="onclickReloadTime()" checked="" value="10"></div></div>
        <div class="main">
            <div class="leag">澳洲甲组联赛</div>
            <div class="gametype">全场 - 让球</div>
            <div class="teamName">
                <span class="tName">墨尔本胜利 <span class="radio"><span class="radio">0.5</span></span> 中央海岸</span>

            </div>
            <p class="team"><em>中央海岸</em> @ <strong class="light" id="ioradio_id">0.91</strong></p>
            <p class="auto"><input type="checkbox" id="autoOdd" name="autoOdd" onclick="onclickReloadAutoOdd()" checked="" value="Y"><span class="auto_info" title="在方格里打勾表示，如果投注单里的任何选项在确认投注时赔率变佳，系统会无提示的继续进行该下注。">自动接受较佳赔率</span></p>
            <p class="error" style="display: none;"></p>
            <div class="betdata">
                <p class="amount">交易金额：<input name="gold" type="text" class="txt" id="gold" onkeypress="return CheckKey(event)" onkeyup="return CountWinGold()" size="8" maxlength="10"></p>
                <p class="mayWin"><span class="bet_txt">可赢金额：</span><font id="pc">0.91</font></p>
                <p class="minBet"><span class="bet_txt">单注最低：</span>50</p>
                <p class="maxBet"><span class="bet_txt">单注最高：</span>80,000</p>
            </div>
        </div>
        <div id="gWager" style="display: none;position: absolute;"></div>
        <div id="gbutton" style="display: block;position: absolute;"></div>
        <div class="betBox">
            <input type="button" name="btnCancel" value="取消" onclick="parent.close_bet();" class="no">
            <input type="button" name="Submit" value="确定交易" onclick="CountWinGold();return SubChk();" class="yes">
        </div>
    </div>
    <div id="gfoot" style="display: block;position: absolute;"></div>
    <div class="ord" id="line_window" style="display: none;">
        <div class="betChk" id="gdiv_table">
            <span class="notice">*SHOW_STR*</span>
            <input type="button" name="wgCancel" value="取消" onclick="Close_div();" class="no">
            <input type="button" name="wgSubmit" value="确定交易" onmousedown="Sure_wager();" class="yes">
        </div>
    </div>
    <input type="hidden" name="uid" value="26e62529m12941891l146398946">
    <input type="hidden" name="langx" value="zh-cn">
    <input type="hidden" name="active" value="1">
    <input type="hidden" name="strong" value="C">
    <!--input type="hidden" name="ordertype" value="1"-->
    <input type="hidden" name="line_type" value="4">
    <input type="hidden" name="gid" value="1814770"><!--<input type="hidden" name="ouid" value="{OUID}">-->
    <input type="hidden" name="type" value="H">
    <input type="hidden" name="gnum" value="50068">
    <input type="hidden" name="concede_r" value="-1">
    <input type="hidden" name="radio_r" value="-100">
    <input type="hidden" id="ioradio_r_h" name="ioradio_r_h" value="0.91">
    <!--input type="hidden" name="ioradio_ck" value="0.91"-->
    <input type="hidden" name="gmax_single" value="80000">
    <input type="hidden" name="gmin_single" value="50">
    <input type="hidden" name="singlecredit" value="1100000">
    <input type="hidden" name="singleorder" value="330000">
    <input type="hidden" name="restsinglecredit" value="0">
    <input type="hidden" name="wagerstotal" value="1100000">
    <input type="hidden" name="restcredit" value="1">
    <input type="hidden" name="pay_type" value="0">
    <input type="hidden" name="odd_f_type" value="H">
</form>

</body></html>