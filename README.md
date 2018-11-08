# netty-plus
#### 服务通信框架
> client-communication 客户端通信服务框架

> netty-communication 服务端通信服务框架

> communication-message-protocol 通信消息协议框架

> spring-boot-starter-netty-communication 支持spring boot

> spring-boot-netty-test 测试项目

#### 如何使用

#### 1.如果直接开启服务端，则使用如下代码：
```java
SocketServerConfig serverConfig = new SocketServerConfig();
serverConfig.setBasePackages("com.tigerjoys.onion.communication.server.command");
serverConfig.setPort(9527);
serverConfig.setProxyFactory(new DefaultProxyFactory());
serverConfig.setReaderIdleTime(20);//可选
serverConfig.setSlowMethod(true);//可选
serverConfig.setSlowMethodMillis(1000);//可选
serverConfig.setReconnectTime(2);//可选

NettyBootstrap boot = new NettyBootstrap(serverConfig);
boot.start();
```

#### 2.使用spring boot：
maven引入jar包

```maven
<dependency>
	<groupId>com.tigerjoys.onion.netty.communication</groupId>
	<artifactId>spring-boot-starter-netty-communication</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

application.yml配置如下：

```yml
socket: 
  server:
    enable: true
    port: 9527
    basePackages: com.tigerjoys.onion.communication.server.command
    readerIdleTime : 20 #设定读空闲时间，如果N秒内没有获取到Client的读取时间，则将Client踢出(可选，默认20)
    slowMethod : true #是否打印慢接口(可选，默认false)
    slowMethodMillis : 1000 #慢接口的阈值(可选，默认1000)
```

#### 3.client端开启
maven引入jar包

```maven
<dependency>
	<groupId>com.tigerjoys.onion.netty.communication</groupId>
	<artifactId>client-communication</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

Client端开启服务代码：

```java
SocketClientConfig config = new SocketClientConfig();
config.setDeviceId("100001");//设备编号
config.setBasePackages("com.andrcid.process.client.command");//设置扫描的代理包路径
config.setServerHost("127.0.0.1");//设置PC SERVER的HOST
config.setServerPort(9527);//设置PC SERVER的PORT
config.setRequestTimeout(300);//设置SOCKET通信获取数据超时时间，可选，默认300秒
config.setWriterIdleTime(5);//设置客户端写空闲的时候，多久发送一次心跳包，可选，默认5秒
config.setReconnectTime(5);//服务器连接断开后的重试间隔，单位(秒)，可选，默认5秒
config.setIdleHeartTimeout(20);//心跳空闲超时时间，单位(秒) 可选，默认20秒

ClientBootstrap client = new ClientBootstrap(config);
//添加连接监听器，必须在start()之前设置
client.addSessionConnectionListener(new ISessionConnectionListener() {
	public boolean onConnection(SessionConnectionEvent event) {
		System.out.println("与服务器第一次建立了Session连接通道！！！！");
		return true;//true继续执行一下个监听器代码，false跳出监听器逻辑
	}
});
client.start();
```

客户端如何调用PC Server远程接口

```java
@ProxyModule //添加动态代理标注
@Controller //标注为远程接口
public interface IServiceRemote {
	
	//远程接口URL
	@RequestMapping("/api/test/123")
	public List<String> sayHello(@Param("aa") String x , @Param("b") int b);//@Param请求的参数
	
	//异步接口，注意，List<String>将返回的永远是空，仅作为返回对象的解析使用。回调将在IAsyncCallBackListener中传递。
	//如果IAsyncCallBackListener中处理方法return false，则不会传递给第二个IAsyncCallBackListener。IAsyncCallBackListener可以为任意参数位置。
	//CallBackEvent内部有是否成功的字段。。
	@RequestMapping(value="/api/test/911" , method = RequestMethod.ASYNC)
	public List<String> sayAsyncHello(IAsyncCallBackListener callback , @Param("aa") String x , @Param("b") int b , IAsyncCallBackListener callback2);
	
	//支持Future模式
	@RequestMapping(value="/api/test/888")
	public Future sayFuture(@Param("aa") String x , @Param("b") int b);

}
```
类和方法说明：<br/>
@ProxyModule 必须加，标注为自动代理<br/>
@Controller 标注为远程接口<br/>
@RequestMapping	请求的API接口映射<br/>
<br/>
调用参数说明：<br/>
1.如果调用的是基本对象，或者多参数方法，则必须要加@Param("value") value代表请求的参数名称<br/>
2.如果调用没有参数，则无须添加任何注解<br/>
3.如果调用的是复杂对象类型，并且有且仅有一个参数，则无须添加@Param注解，自动请求的是此对象的JSON序列化后的参数体<br/>
<br/>

```java
//客户端Future模式的调用方式

IServiceRemote serviceRemote = Global.getInstance().resolve(IServiceRemote.class);
		
Future f = serviceRemote.sayFuture("Hello", 520);
long s = System.currentTimeMillis();
try {
	JSONArray jsonArray = f.get(JSONArray.class);
	for(Object o : jsonArray) {
		System.out.println(o);
	}
} catch (RemoteException e1) {
	e1.printStackTrace();
}
long e = System.currentTimeMillis();
System.out.println("总计花费：" + (e - s) + "毫秒!");

```

如何使用动态代理：<br/>

```java
//接口类
public interface ITestCommand {
	
	public void sayHello();

}
```

```java
//实现类，注意远程API类和此动态代理类可以不放在一个包下。
@ProxyModule(from=ITestCommand.class)//from必须填写接口名称
public class TestCommandImpl implements ITestCommand {
	
	@Inject//注入远程代理对象
	private IServiceRemote serviceRemote;

	@Override
	public void sayHello() {
		//调用远程代理方法
		System.out.println(serviceRemote.sayHello("abc" , 101));
	}

}

```

如果不想写 Command接口和实现类等，可以通过全局对象拿到远程调用实体

```java
Global.getInstance().resolve(ITestCommand.class).sayHello();
```

## 其他

如有问题，随时联系。
