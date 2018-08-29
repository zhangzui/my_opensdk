var merchantNo = "360087641000000005";
var appId = "360087641000000005";
var version = "1.0.0";
var tradeType = "00";
var charset = "utf-8";
var timestamp = "2018-07-16";
var encryptType = "AsES";
var signType = "RSA2";
var sign = "xxxasas";
var requestParams = '{"name":"xxx"}';

var strData = JSON.stringify(JSON.parse(requestParams));

strData=strData.replace(/\ +/g,"");//去掉空格
strData=strData.replace(/[ ]/g,"");    //去掉空格
strData=strData.replace(/[\r\n]/g,"");//去掉回车换行

var openAPIEntity ={
    "merchantNo":merchantNo,
    "appId":appId,
    "version":version,
    "tradeType":tradeType,
    "charset":charset,
    "version":version,
    "timestamp":timestamp,
    "signType":signType,
    "sign":sign,
    "encryptType":encryptType,
    "data":strData.replace("\\","")
}
$(function(){
    console.info("request:"+JSON.stringify(openAPIEntity));
    console.info("http://localhost:8080/openApi/gateway?" +
        "merchantNo="+merchantNo
        +"&appId="+appId
        +"&version="+version
        +"&tradeType="+tradeType
        +"&charset="+charset
        +"&timestamp="+timestamp
        +"&signType="+signType
        +"&sign="+sign
        +"&encryptType="+encryptType
        +"&data="+strData.replace("\\",""));
    service.execute();
    service.execute2();
})
var service = {
    execute : function(){
        var url = '/openApi/gateway';
        $.ajax({
            type: "post",
            url: url,
            data:JSON.stringify(openAPIEntity),
            datType: "JSON",
            contentType: "application/json; charset=utf-8",
            success : function(res){
                console.info("response111"+ JSON.stringify(res));
                console.info("response111"+ JSON.stringify(res.res));
            }
        });
    },
    execute2 : function(){
        var url = '/openApi/gateway1';
        $.ajax({
            type: "post",
            url: url,
            data:{
                "merchantNo":merchantNo,
                "appId":appId,
                "version":version,
                "tradeType":tradeType,
                "charset":charset,
                "version":version,
                "timestamp":timestamp,
                "signType":signType,
                "sign":sign,
                "encryptType":encryptType,
                "data":strData.replace("\\","")
            },
            success : function(res){
                console.info("response2222:"+ JSON.stringify(res));
                console.info("response2222:"+ JSON.stringify(res.data));
            }
        });
    }
}