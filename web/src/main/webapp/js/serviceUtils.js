/**
 * Created by zhangzuizui on 2017/2/4.
 */
var privateKey = "012345678901234567890123";
var nowDate = new Date();
var dateUtil = {
    getFromatDate : function(n){
        var date = new Date();
        if(n==1){
            date = new Date(nowDate.setDate(nowDate.getDate()+1));
        }
        var time = date.getFullYear().toString() + dateUtil.pad2(date.getMonth() + 1) + dateUtil.pad2( date.getDate()) + dateUtil.pad2( date.getHours() ) + dateUtil.pad2( date.getMinutes() ) + dateUtil.pad2( date.getSeconds() )
        return time;
    },
    pad2 : function(n) {
        return n < 10 ? '0' + n : n
    }
}
var nowDateStr = dateUtil.getFromatDate();
var expiredDateStr = dateUtil.getFromatDate(1);
var saveOrderReqdemo = {
    "discount":10
};
var payreqdemo = {
    "acceptHeader":"xxxxx"
}
$(function(){
    $("#requestParams").html("").html(formatJson(JSON.stringify(saveOrderReqdemo)));

    $('#buildTest').on('click', function (e) {
        var bizType = $("#bizTypeSelect").val();
        if(bizType == "order"){
            $("#requestParams").html("").html(formatJson(JSON.stringify(saveOrderReqdemo)));
            $("#tradeType").val("01");
        }
        if(bizType == "pay"){
            $("#requestParams").html("").html(formatJson(JSON.stringify(payreqdemo)));
            $("#tradeType").val("02");
        }
    });
    $('#utilButton1').on('click', function (e) {
        service.execute();
    });

})
var service = {
    execute : function(){
        var merchantCode = $("#merchantCode").val();
        var privateKey = $("#privateKey").val();
        var version = $("#version").val();
        var tradeType = $("#tradeType").val();
        var charset = $("#charset").val();
        var appId = $("#appId").val();
        var requestParams = $("#requestParams").val();

        var strData = JSON.stringify(JSON.parse(requestParams));
        strData=strData.replace(/\ +/g,"");//去掉空格
        strData=strData.replace(/[ ]/g,"");    //去掉空格
        strData=strData.replace(/[\r\n]/g,"");//去掉回车换行

        console.info("merchantCode="+merchantCode,"privateKey="+privateKey,"data="+strData);
        var bizEntity ={
            "merchantCode":merchantCode,
            "appId":appId,
            "privateKey":privateKey,
            "charset":charset,
            "tradeType":tradeType,
            "version":version,
            "data":strData.replace("\\","")
        }
        var url = '/utilService/execute';
        $.ajax({
            type: "post",
            url: url,
            data:bizEntity,
            success : function(res){
                console.info(JSON.stringify(res));
                $("#responseParams1").html("").html(formatJson(JSON.stringify(res.res)));
                $("#responseParams2").html("").html(formatJson(res.resDecodeData));
            }
        });
    }
}