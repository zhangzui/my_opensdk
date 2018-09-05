###SpringMVC知道的跨域注解：
```
@RequestMapping(value = "/api0")
@ResponseBody
@CrossOrigin
public String api0(UserInfo userInfo) {
    LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
    return JSON.toJSONString(userInfo);
}
```
http://aaa.com:8080/cors/api0?userId=000001&userName=zzz&userPwd=123456
http://bbb.com:8080/cors/api0?userId=000001&userName=zzz&userPwd=123456
返回结果没问题。

###JSONP
第一种方式：
http://aaa.com:8080/cors/api2?callback=111
http://bbb.com:8080/cors/api2?callback=1212
```
@RequestMapping(value = "/api1")
    @ResponseBody
    public String api1(String callback) {
        UserInfo userInfo = buildUserInfo();
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfo);
        mappingJacksonValue.setJsonpFunction(callback);
        return JSON.toJSONString(userInfo);
    }
```

第二种方式：
http://aaa.com:8080/cors/api2
http://bbb.com:8080/cors/api2
```
@RequestMapping("api2")
    public void api2(HttpServletRequest request, HttpServletResponse response) {
        int flag = 222;
        response.setContentType("text/plain");
        //得到js函数名称
        String callbackFunName =request.getParameter("<span style=\"color:#ff6666;\">callbackparam</span>");
        try {
            //返回jsonp数据
            response.getWriter().write(callbackFunName + "([ { flag:\""+flag+"\"}])");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

###反向代理
```
server {
        listen       80;
        server_name  a.com;

	location / {
	    proxy_pass http://a.com:8080/;
        }

        location /bcom/ {
	    proxy_pass http://b.com:8080/;
        }
}
```
###带cookie的跨域请求

###带头信息
自定义头都使用 X- 开头，养成好习惯
```
function getWithHeader() {
	$.ajax({
		type : "GET",
		url : "http://b.com:8080/getWithHeader",
		headers : {
			"X-Custom-Header1" : "can not include zhongwen1111"
		},
		beforeSend : function(xhr) {
			xhr.setRequestHeader("X-Custom-Header2", "can not include zhongwen2222");
			xhr.setRequestHeader("X-Custom-Header3", "can not include zhongwen3333");
		},
		success : function(data) {
			console.log("getWithHeader Loaded: ", data);
		},
		error:function(data) {
			console.log("getWithHeader error: ", data);
		},
	})
}
```