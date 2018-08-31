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

$(function(){
    $('#getButton').on('click', function (e) {
        service.getFun();
    });
    $('#postButton').on('click', function (e) {
        service.postFun();
    });
})

var Url = "http://localhost:8080/open_api/gateway0";

var GetUrl = "http://localhost:8080/open_api/gateway1?userId=000001&userName=zzz&userPwd=123456";

var gateway1 = "http://localhost:8080/open_api/gateway1?userId=000001";
var gateway1 = "http://localhost:8080/open_api/gateway1?userId=000001";
var gateway3 = "http://localhost:8080/open_api/gateway3?name=zzz&password=123456";


var userInfo = {
    "userId":"000001",
    "userName":"zzz",
    "userPwd":"123456"
}


$("#buttons001").click(function(){
    testGet001(Url);
})

$("#buttons001").click(function(){
    testGet001(Url);
})
/**
 * List传参： data: JSON.stringify(["123", "456"]),
 */
var service = {
    getFun : function(){
        var url = $("#url").val();
        $.ajax({
            type: "GET",
            url: url,
            data:userInfo,
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    postFun : function(){
        var url = $("#url").val();
        $.ajax({
            type: "post",
            url: url,
            data:userInfo,
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    }
}
var xmlhttp = new XMLHttpRequest();
function testGet001(url){
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
}
function testPost001(url){
    xmlhttp.open("POST",url,true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("userId=000001&userName=zzz&userPwd=123456");
}
