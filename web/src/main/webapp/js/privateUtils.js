/**
 * Created by zhangzuizui on 2017/2/4.
 */
$(function(){
    $('#utilButton1').on('click', function (e) {
        utils_initData.encode();
    });
    $('#utilButton2').on('click', function (e) {
        utils_initData.decode();
    });
    $('#utilButton3').on('click', function (e) {
        utils_initData.decodeAndDeSign();
    });
})
var utils_initData = {
    encode : function(){
        var merchantCode = $("#merchantCode").val();
        var appId = $("#appId").val();
        var privateKey = $("#privateKey").val();
        var version = $("#version").val();
        var tradeType = $("#tradeType").val();
        var charset = $("#charset").val();
        var requestParams = $("#requestParams").val();
        console.info("merchantCode="+merchantCode,"privateKey="+privateKey,"data="+requestParams);
        var url = '/utilService/encode';
        var bizEntity ={
            "merchantCode":merchantCode,
            "appId":appId,
            "privateKey":privateKey,
            "charset":charset,
            "tradeType":tradeType,
            "version":version,
            "data":requestParams
        }
        $.ajax({
            type: "post",
            url: url,
            data:bizEntity,
            success : function(res){
                console.info(JSON.stringify(res));
                $("#responseParams").html("").html(formatJson(JSON.stringify(res)));
            }
        });
    },
    decode : function(){
        var merchantCode = $("#merchantCode").val();
        var charset = $("#charset").val();
        var requestParams = $("#requestParams").val();
        var url = '/utilService/decode';
        var bizEntity ={
            "merchantCode":merchantCode,
            "charset":charset,
            "data":requestParams
        }
        $.ajax({
            type: "post",
            url: url,
            data:bizEntity,
            success : function(res){
                console.info("decode:"+JSON.stringify(res));
                $("#responseParams").html("").html(formatJson(JSON.stringify(res)));
            }
        });
    },
    decodeAndDeSign : function(){
        var merchantCode = $("#merchantCode").val();
        var privateKey = $("#privateKey").val();
        var version = $("#version").val();
        var tradeType = $("#tradeType").val();
        var charset = $("#charset").val();
        var requestParams = $("#requestParams").val();
        var sign = $("#sign").val();
        console.info("merchantCode="+merchantCode,"privateKey="+privateKey,"data="+requestParams);
        var url = '/utilService/decodeAndDeSign';
        var bizEntity ={
            "merchantCode":merchantCode,
            "privateKey":privateKey,
            "charset":charset,
            "tradeType":tradeType,
            "version":version,
            "data":requestParams,
            "sign":sign
        }
        $.ajax({
            type: "post",
            url: url,
            data:bizEntity,
            success : function(res){
                console.info(JSON.stringify(res));
                $("#responseParams").html("").html(formatJson(JSON.stringify(res)));
            }
        });
    }
}