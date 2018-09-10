
###SpringMVC知道的跨域注解：
```
 /**
 *  @CrossOrigin注解需要指定请求方式。get|post
 * @param userInfo
 * @param response
 * @return
 */
@RequestMapping(value = "/api01",method = RequestMethod.POST)
@ResponseBody
@CrossOrigin
public String api01(UserInfo userInfo,HttpServletResponse response) {
    LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
    String result = JSON.toJSONString(userInfo);
    return result;
}
```
请求：

```
var url = $("#url_gateway0").val();
        $.ajax({
            type: "POST",
            url: url,
            data:userInfo,
            dataType : "JSON",
            success : function(res){
                console.info(JSON.stringify(res));
            },
            error:function(e){
                alert("error"+JSON.stringify(e));
                console.info(JSON.stringify(e));
            }
        });
    }
```


###JSONP
第一种方式：

```
@RequestMapping(value = "/api00")
    @ResponseBody
    @CrossOrigin
    public void api00(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) throws IOException {
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        String jsonp = "[" + JSON.toJSONString(userInfo) +"]";
        response.setContentType("text/plain");
        //得到js函数名称
        String callbackFunName = request.getParameter("callbackparam");

        response.getWriter().write(callbackFunName+"(["+jsonp+"])");
    }

@RequestMapping(value = "/api2")
@ResponseBody
public Object api2(HttpServletRequest request,HttpServletResponse response) {
    UserInfo userInfo = buildUserInfo();
    response.setHeader("Access-Control-Allow-Origin", "*");
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfo);
    System.out.println("api2-----"+request.getParameter("callbackparam"));
    mappingJacksonValue.setJsonpFunction(request.getParameter("callbackparam"));
    //得到js函数名称
    return mappingJacksonValue;
}
```
请求：
```
gateway00 : function(){
        var url = $("#url_gateway00").val();
        $.ajax({
            type: "GET",
            url: url,
            data:userInfo,
            async:false,
            dataType : "JSONP",
            jsonp: "callbackparam",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
            jsonpCallback:"callbackFun",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
            success : function(res){
                console.info(JSON.stringify(res));
            },
            error:function(e){
                alert("error"+JSON.stringify(e));
                console.info(JSON.stringify(e));
            }
        });
    }
```
如果设置了callbackFun，则会触发回调函数，如果没有设置，则是浏览器默认的回调方法名，最后也会触发SUCCESS里的逻辑。

####JSONP2
服务端设置头信息：rsp.setHeader("Access-Control-Allow-Origin", "*");这样可能会造成CSRF攻击（跨站请求伪造）
```
    @RequestMapping(value = "/api0")
    @ResponseBody
    public String api0(UserInfo userInfo,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOGGER.info("userInfo="+JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }
```
ajax
```
gateway0 : function(){
        var url = $("#url_gateway0").val();
        $.ajax({
            type: "POST",
            url: url,
            data:userInfo,
            dataType : "JSON",
            success : function(res){
                console.info(JSON.stringify(res));
            },
            error:function(e){
                alert("error"+JSON.stringify(e));
                console.info(JSON.stringify(e));
            }
        });
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