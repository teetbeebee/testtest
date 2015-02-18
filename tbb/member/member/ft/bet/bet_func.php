<?php
class betinfo{
    public $type;
    public $id;
    public $s;

    public $description;
    public $odds;
}

function getBetSrc($tab){
    if($tab == 'zp')
        return '早盘';
    if($tab == 'run')
        return '滚球';
    if($tab == 'today')
        return '今日赛事';

    return '未知赛事类型';

}

function getBetTypeSql($type, $id){
    return 'select * from foot_match_ex_'.strtolower($type).' t where t.id = '.$id;
}

function setBetInfo($bet, $match, $bet_type){
    if($bet->type == 'M'){
        if($bet->s == 'MB'){
            $bet->description = $match[MB_Team];
            $bet->odds = $bet_type[M_MB];

        }
        if($bet->s == 'TG'){
            $bet->description = $match[TG_Team];

        }
    }
}

function getBetType($type){
    if($type == 'R') return '全场 - 让球';
    if($type == 'HR') return '让球 - 上半场';
    if($type == 'OU') return '进球: 大 / 小';
    if($type == 'HOU') return '进球: 大 / 小 - 上半场';
    if($type == 'M') return '全场 - 独赢';
    if($type == 'HM') return '独赢 - 上半场';
    if($type == 'AR') return '15 分钟进球数: 开场 - 14:59分钟 - 让球';
    if($type == 'AOU') return '15 分钟进球数: 开场 - 14:59分钟 - 大 / 小';
    if($type == 'AM') return '15 分钟进球数: 开场 - 14:59分钟 - 独赢';
    if($type == 'BR') return '15 分钟进球数: 15:00 - 29:59分钟 - 让球';
    if($type == 'BOU') return '15 分钟进球数: 15:00 - 29:59分钟 - 大 / 小';
    if($type == 'BM') return '15 分钟进球数: 15:00 - 29:59分钟 - 独赢';
    if($type == 'CR') return '15 分钟进球数: 30:00 - 半场 - 让球';
    if($type == 'COU') return '15 分钟进球数: 30:00 - 半场 - 大 / 小';
    if($type == 'CM') return '15 分钟进球数: 30:00 - 半场 - 独赢';
    if($type == 'DR') return '15 分钟进球数: 下半场开始 - 59:59分钟 - 让球';
    if($type == 'DOU') return '15 分钟进球数: 下半场开始 - 59:59分钟 - 大 / 小';
    if($type == 'DM') return '15 分钟进球数: 下半场开始 - 59:59分钟 - 独赢';
    if($type == 'ER') return '15 分钟进球数: 60:00 - 74:59分钟 - 让球';
    if($type == 'EOU') return '15 分钟进球数: 60:00 - 74:59分钟 - 大 / 小';
    if($type == 'EM') return '15 分钟进球数: 60:00 - 74:59分钟 - 独赢';
    if($type == 'FR') return '15 分钟进球数: 75:00 - 全场 - 让球';
    if($type == 'FOU') return '15 分钟进球数: 75:00 - 全场 - 大 / 小';
    if($type == 'FM') return '15 分钟进球数: 75:00 - 全场 - 独赢';
    if($type == 'PD') return '全场 - 波胆';
    if($type == 'HPD') return '半场 - 波胆';
    if($type == 'T') return '全场 - 总进球数';
    if($type == 'HT') return '总进球数 - 上半场';
    if($type == 'F') return '半场 / 全场';
    if($type == 'WM') return '净胜球数';
    if($type == 'WE') return '赢得任一半场';
    if($type == 'WB') return '赢得所有半场';

    if($type == 'PG') return '最先 / 最后进球';
    if($type == 'TS') return '双方球队进球';
    if($type == 'OUH') return '球队进球数：主队 大 / 小-全场';
    if($type == 'OUC') return '球队进球数：客队 大 / 小-全场';
    if($type == 'HOUH') return '球队进球数：主队 大 / 小-上半场';
    if($type == 'HOUC') return '球队进球数：客队 大 / 小-上半场';
    if($type == 'EO') return '进球数：单双';
    if($type == 'HEO') return '进球数：单双 - 上半场';
    if($type == 'CS') return '零失球';
    if($type == 'WN') return '零失球获胜';
    if($type == 'HG') return '最多进球半场';
    if($type == 'MG') return '最多进球半场独赢';
    if($type == 'SB') return '双半场进球';
    if($type == 'T3G') return '首个进球时间-3项';
    if($type == 'T1G') return '首个进球时间';
    if($type == 'W3') return '三项让球投注';
    if($type == 'BH') return '落后反超获胜';
    if($type == 'SFS') return '进球球员';
    if($type == 'F2G') return '先进2球的一方';
    if($type == 'F3G') return '先进3球的一方';
    if($type == 'FG') return '首个进球方式';
    if($type == 'TK') return '先开球球队';
    if($type == 'PA') return '点球荣获';
    if($type == 'RCD') return '红卡（球员）';

    return '未知玩法';
}


?>