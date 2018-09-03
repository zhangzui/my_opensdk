###1.get
```
@RequestMapping(value = "/gateway1")
@ResponseBody
public String gateway1(UserInfo userInfo) {
    LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
    return JSON.toJSONString(userInfo);
}
```
请求：
http://aaa.com:8080/test/gateway1?userId=000001&userName=zzz&userPwd=123456
响应:
```
{"userId":"000001","userName":"zzz","userPwd":"123456"}
```
###post:
```
@RequestMapping(value = "/gateway0")
@ResponseBody
public String gateway0(@RequestBody UserInfo userInfo) {
     LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
     return JSON.toJSONString(userInfo);
}
```
请求：
```
var url=http://aaa.com:8080/test/gateway0
var userInfo = {
    "userId":"000001",
    "userName":"zzz",
    "userPwd":"123456"
}
function postFun(){
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
}
```
响应：
```
"{\"userId\":\"000001\",\"userName\":\"zzz\",\"userPwd\":\"123456\"}"
```
###gateway3
get:http://aaa.com:8080/test/gateway2?name=zzz&password=123456
post:contentType不能是contentType: "application/json;charset=utf-8"

```
 postButton1 : function(){
        $.ajax({
            type: "post",
            url: "http://aaa.com:8080/test/gateway2",
            data:{"name","zzz":"password":"123456"},
            //contentType: "application/json;charset=utf-8",
            dataType : "text",
            success : function(res){
                console.info(JSON.stringify(res));
            }
        });
    },
```


###gateway4
get:http://aaa.com:8080/test/gateway4?name=zzz&password=123456
post:
```
var params = {
    "name":"zxzz",
    "password":"123456"
}

var url = $("#url_postButton2").val();
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

```
###gateway6
post:
```
服务端：
    @RequestMapping(value = "/gateway6")
    @ResponseBody
    public String gateway6(@RequestBody List<String> list) {
        LOGGER.info("gateway6,name="+list.get(0)+",password="+list.get(1));
        return "gateway4-result:name="+list.get(0)+",password="+list.get(1);
    }
ajax请求：
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
```

###gateway7
http://aaa.com:8080/test/gateway7/12?userId=000001&userName=zzz&userPwd=123456
###gateway77
```
@RequestMapping(value = "/gateway77/{userId}",method=RequestMethod.POST,consumes="application/json", produces="application/json")
    @ResponseBody
    public String gateway77(@RequestBody List<String> list,@PathVariable String userId) {
        Map<String,String> map = new HashMap<>();
        map.put("name",list.get(0));
        map.put("pwd",list.get(1));
        map.put("userid",userId);
        LOGGER.info("gateway7,name="+list.get(0)+",password="+list.get(1)+",userid="+userId);
        return JSON.toJSONString(map);
    }

http://aaa.com:8080/test/gateway77/001
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

```



