## 关于Logback的使用

1.pom引入
```
	<properties>
		<!-- slf4j + logback -->
		<slf4j-core.version>1.7.28</slf4j-core.version>
		<logback-core.version>1.2.3</logback-core.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${slf4j-core.version}</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-access</artifactId>
			<version>${logback-core.version}</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-core</artifactId>
			<version>${logback-core.version}</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-classic</artifactId>
			<version>${logback-core.version}</version>
			<scope>provided</scope>
			<exclusions>
				<exclusion>
					<groupId>org.slf4j</groupId>
					<artifactId>*</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>
```

2.测试使用的Logback
将 



