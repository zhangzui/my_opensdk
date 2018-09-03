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
    $('#gateway0').on('click', function (e) {
        service.gateway0();
    });
    $('#gateway1').on('click', function (e) {
        service.gateway1();
    });
    $('#gateway2').on('click', function (e) {
        service.gateway2();
    });
    $('#gateway3').on('click', function (e) {
        service.gateway3();
    });
    $('#gateway4').on('click', function (e) {
        service.gateway4();
    });
    $('#gateway5').on('click', function (e) {
        service.gateway5();
    });
    $('#gateway6').on('click', function (e) {
        service.gateway6();
    });
    $('#gateway7').on('click', function (e) {
        service.gateway7();
    });
    $('#gateway8').on('click', function (e) {
        service.gateway8();
    });
    $('#gateway9').on('click', function (e) {
        service.gateway9();
    });
    $('#gateway10').on('click', function (e) {
        service.gateway10();
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
var params = {
    "name":"zxzz",
    "password":"123456"
}

var service = {
    gateway0 : function(){
        var url = $("#url_gateway0").val();
        $.ajax({
            type: "post",
            url: url,
            data:JSON.stringify(userInfo),
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * get
     * http://aaa.com:8080/test/gateway1?userId=000001&userName=zzz&userPwd=123456
     */
    gateway1: function(){
        var url = $("#url_gateway1").val();
        $.ajax({
            type: "POST",
            url: url,
            data:userInfo,
            //contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * gateway2:get方式：http://aaa.com:8080/test/gateway2?name=zzz&password=123456
     * gateway2:post方式 contentType使用Content-Type:application/x-www-form-urlencoded; charset=UTF-8
     */
    gateway2 : function(){
        var url = $("#url_gateway2").val();
        $.ajax({
            type: "post",
            url: url,
            data:params,
            //contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * gateway3 直接请求http://aaa.com:8080/test/gateway3?name=zzz&password=123456
     * 或者post请求如下
     */
    gateway3 : function(){
        var url = $("#url_gateway3").val();
        $.ajax({
            type: "post",
            url: url,
            data:params,
            //contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * contentType
     */
    gateway4 : function(){
        var url = $("#url_gateway4").val();
        $.ajax({
            type: "post",
            url: url,
            data: params,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * List参数需要加上@RequestBody
     */
    gateway5 : function(){
        var url = $("#url_gateway5").val();
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(["zzz", "123456"]),
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * data需要时json格式的字符串，contentType：application/json格式才行
     */
    gateway6 : function(){
        var url = $("#url_gateway6").val();
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(["zzz", "123456"]),
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * get：必须带有myParam=myValue
     * http://aaa.com:8080/test/gateway7/001?userId=000001&userName=zzz&userPwd=123456
     * post请求:data: userInfo,contentType默认即可
     *
     */
    gateway7 : function(){
        var url = $("#url_gateway7").val();
        userInfo.userId
        $.ajax({
            type: "post",
            url: url,
            data: userInfo,
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * 请求参数：@RequestBody需要下面的格式：
     *  data: JSON.stringify(["zzz", "123456"]),
        contentType: "application/json;charset=utf-8",

     */
    gateway8 : function(){
        var url = $("#url_gateway8").val();
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(["zzz", "123456"]),
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
    /**
     * 接受List需要加@RequestBody
     */
    gateway9 : function(){
        var url = $("#url_gateway9").val();
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify(["zzz", "123456"]),
            contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },

    gateway10 : function(){
        var url = $("#url_gateway10").val();
        $.ajax({
            type: "get",
            url: url,
            //headers: {
            //    "Referer":"https://zhidao.baidu.com/"
            //},
            ////beforeSend定义全局变量
            //beforeSend: function (xhr) {
            //    xhr.setRequestHeader("Referer", "http://zzz.cn");
            //},
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
