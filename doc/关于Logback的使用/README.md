# 关于Logback的使用
Logback是一个开源的日志组件。logback当前分成三个模块：logback-core,logback- classic和logback-access。    
logback-core: 是其它两个模块的基础。    
logback-classic 模块可以看作是 log4j 的一个优化版本，它天然的支持 SLF4J，所以你可以随意的从其它日志框架（例如：log4j 或者 java.util.logging）切回到 logack。    
logback-access 可以与 Servlet 容器进行整合，例如：Tomcat、Jetty。它提供了 http 访问日志的功能。      
&emsp;
访问[这里](http://www.logback.cn/)获取更多关于logback的配置，以下是项目中常用的logback配置。  

1.pom.xml引入依赖    
```
<properties>
	<!-- slf4j + logback -->
	<slf4j.version>1.7.25</slf4j.version>
	<logback-core.version>1.2.3</logback-core.version>
</properties>

<dependencies>
	<!--jul桥接 用来输出 Java Logging Framework (JUL) -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>jul-to-slf4j</artifactId>
		<version>${slf4j.version}</version>
	</dependency>

	<!-- jcl桥接，用来输出 第三方Jakarta Commons Logging (JCL) -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>jcl-over-slf4j</artifactId>
		<version>${slf4j.version}</version>
	</dependency>

	<dependency>
		<groupId>ch.qos.logback</groupId>
		<artifactId>logback-access</artifactId>
		<version>${logback-core.version}</version>
	</dependency>

	<dependency>
		<groupId>ch.qos.logback</groupId>
		<artifactId>logback-classic</artifactId>
		<version>${logback-core.version}</version>
	</dependency>
</dependencies>
```

2.配置logback.xml文件         
logback默认配置的步骤如下：
(1)尝试在 classpath 下查找文件 logback-test.xml；    
(2)如果 logback-test.xml 文件不存在，则查找文件logback.xml；    
(3)如果两个文件都不存在，logback用BasicConfigurator自动对自己进行配置，这会导致记录输出到控制台。    
&emsp;
在项目中只需要参考如下配置即可:    
(1)将 [app-config.properties](app-config.properties)适当修改后放入 src\main\resources 和 src\test\resources 目录下；        
(2)将 [logback.xml](logback.xml) 放入 src\main\resources 目录下，将 [logback-test.xml](logback-test.xml) 放入 src\test\resources 目录下;       
注意:以上第二步可以只放入 [logback.xml](logback.xml) 到 src\main\resources 目录下。   
