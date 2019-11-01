## 关于Logback的使用

1.pom引入
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

2.测试使用的Logback
将 



