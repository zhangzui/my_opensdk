<<<<<<< HEAD
#ocf_th_trade_gateway
泰国交易前端，对外商家网关收单，内部jsf接口
<dependency>
    <groupId>com.jd.jr.overseas.finance</groupId>
    <artifactId>ocf_th_trade_gateway_sdk</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

接口描述：泰国交易系统前置收单接口
接口方法：com.jd.jr.overseas.finance.trade.gateway.sdk.export.TradePayService#payReceiveOrder


接口描述：泰国交易系统前置支付接口
接口方法：com.jd.jr.overseas.finance.trade.gateway.sdk.export.TradePayService#payTransaction

#Jsf配置
<jsf:consumer id="tradePayService" interface="com.jd.jr.overseas.finance.trade.gateway.sdk.export.TradePayService"
    alias="${alias}" protocol="jsf"  timeout="5000" check="false"/>
测试：alias=th_trade_gateway_test
生产：alias=th_trade_gateway

#泰国交易前置网关相关：

sdk.jar和opensdk.jar包上传maven库
内部商家jsf注册th_trade_gateway

#open-sdk使用则需要以下操作
服务器jdk要求1.8，需要替换security下的安全包
外部商家接口域名申请，和开放测试机和线上机器的公网访问权限


zk地址：10.223.37.79:2181,10.223.37.85:2181,10.223.37.186:2181,10.223.37.74:2181,10.223.37.223:2181

JSF地址（柳生辉）：
10.223.37.112   th.jr.jsf.jd.com
<jsf:registry id="jsfRegistry" protocol="jsfRegistry" address="10.223.37.110:40660"/>

ES：http://wiki.cbpmgt.com/confluence/pages/viewpage.action?pageId=20589182

JIMDB（sunshanqing 孙山青）：cfs地址：http://10.223.37.137:6060
目前JSF、JIMDB ES ZK已经可以用了，
但是泰国机房DNS还没有部署好，不能使用域名访问； 所以JSF的管理端host访问不通，大家有服务注册的找柳生辉帮加上，之后jsf配置的index改为address配置即可，机房内是可以互通的；  JIMDB同上
jimdb空间分配找孙山青，cfs地址配置成http://10.223.37.137:6060



=======
# my_opensdk
>>>>>>> de4fa4396fd96591693365c34ecfbad6934c9e77
