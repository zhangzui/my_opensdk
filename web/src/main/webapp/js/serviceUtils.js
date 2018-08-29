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
    "discount":10,
    "finCustomerId":"0062002000000116",
    "merchantNo":"360087641000000005",
    "merchantUserId":"000001",
    "notifyUrl":"http://xxx",
    "orderCountry":"TH",
    "orderCurrency":"THB",
    "orderExpireTime":expiredDateStr,
    "orderId":"order_"+nowDateStr,
    "orderItemVos":[
        {
            "currency":"AAA",
            "itemCategory":"sss",
            "itemLogo":"xxx",
            "itemName":"zzzz",
            "itemPrice":1000,
            "itemQuantity":1,
            "itemSku":"0000"
        }
    ],
    "orderType":"WLT_CHARGE",
    "productType":"EWALLET",
    "returnUrl":"http://xxx",
    "shippingCost":5,
    "shippingDetail":{
        "shippingCity":"sasasas",
        "shippingCountry":"th",
        "shippingCountryName":"sdfd",
        "shippingEmail":"xxx@jd.com",
        "shippingFirstName":"asa",
        "shippingLastName":"sdfd",
        "shippingPhone":"123456664",
        "shippingPostal":"dadas",
        "shippingState":"sa",
        "shippingStateName":"sdfd",
        "shippingStreet1":"dasd",
        "shippingStreet2":"dasd"
    },
    "shopId":"36008764100000000501",
    "subTotal":1000,
    "tax":5,
    "transactionAmount":1000
};
var payreqdemo = {
    "acceptHeader":"xxxxx",
    "billingDetailVo":{
        "billAreaCode":"00000",
        "billingCity":"xxxxCity",
        "billingCountry":"xxxxCountry",
        "billingCountryName":"xxxxCountryName",
        "billingEmail":"xxx@jd.com",
        "billingFirstName":"xxxFirstName",
        "billingLastName":"xxxLastName",
        "billingPhone":"18501959595",
        "billingPostal":"00000",
        "billingState":"00000",
        "billingStateName":"xxxxStateName",
        "billingStreet1":"xxxStreet1",
        "billingStreet2":"xxxStreet2"
    },
    "cardCenterId":"8B5F5E88AFBAAB48A4D0132AD624940E",
    "clientIp":"127.0.0.1",
    "deviceType":"H5",
    "finCustomerId":"0062002000000116",
    "language":"th_TH",
    "languageEncoding":"UTF-8",
    "merchantNo":"360087641000000005",
    "orderId":"20180706165728",
    "payAmount":1000,
    "paymentMethod":"BALA",
    "transactionNo":"20180706104012070616572818223160",
    "userAgent":"sasas"
};
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