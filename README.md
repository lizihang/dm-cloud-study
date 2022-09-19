# dm-cloud-study
spring cloud study project

## 一、端口配置
* 业务底层：18000开始，依次加1
* 业务服务：19000开始，依次加1
* 框架服务：20000开始，依次加1

## 二、报错
### 1.windows下jar包启动报错
```
org.yaml.snakeyaml.error.YAMLException: java.nio.charset.MalformedInputException: Input length = 1
	at org.yaml.snakeyaml.reader.StreamReader.update(StreamReader.java:218) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.reader.StreamReader.ensureEnoughData(StreamReader.java:176) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.reader.StreamReader.ensureEnoughData(StreamReader.java:171) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.reader.StreamReader.peek(StreamReader.java:126) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.scanner.ScannerImpl.scanToNextToken(ScannerImpl.java:1198) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.scanner.ScannerImpl.fetchMoreTokens(ScannerImpl.java:308) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.scanner.ScannerImpl.checkToken(ScannerImpl.java:248) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.parser.ParserImpl$ParseImplicitDocumentStart.produce(ParserImpl.java:213) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.parser.ParserImpl.peekEvent(ParserImpl.java:165) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.parser.ParserImpl.checkEvent(ParserImpl.java:155) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.composer.Composer.checkNode(Composer.java:93) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.constructor.BaseConstructor.checkData(BaseConstructor.java:124) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.Yaml$1.hasNext(Yaml.java:509) ~[snakeyaml-1.29.jar!/:na]
	at org.springframework.beans.factory.config.YamlProcessor.process(YamlProcessor.java:198) ~[spring-beans-5.3.15.jar!/:5.3.15]
	at org.springframework.beans.factory.config.YamlProcessor.process(YamlProcessor.java:166) ~[spring-beans-5.3.15.jar!/:5.3.15]
	at org.springframework.boot.env.OriginTrackedYamlLoader.load(OriginTrackedYamlLoader.java:84) ~[spring-boot-2.6.3.jar!/:2.6.3]
	at org.springframework.boot.env.YamlPropertySourceLoader.load(YamlPropertySourceLoader.java:50) ~[spring-boot-2.6.3.jar!/:2.6.3]
	at com.alibaba.cloud.nacos.parser.NacosDataParserHandler.parseNacosData(NacosDataParserHandler.java:92) ~[spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceBuilder.loadNacosData(NacosPropertySourceBuilder.java:97) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceBuilder.build(NacosPropertySourceBuilder.java:73) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceLocator.loadNacosPropertySource(NacosPropertySourceLocator.java:199) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceLocator.loadNacosDataIfPresent(NacosPropertySourceLocator.java:186) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceLocator.loadNacosConfiguration(NacosPropertySourceLocator.java:158) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceLocator.loadSharedConfiguration(NacosPropertySourceLocator.java:116) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at com.alibaba.cloud.nacos.client.NacosPropertySourceLocator.locate(NacosPropertySourceLocator.java:101) [spring-cloud-starter-alibaba-nacos-config-2021.0.1.0.jar!/:2021.0.1.0]
	at org.springframework.cloud.bootstrap.config.PropertySourceLocator.locateCollection(PropertySourceLocator.java:51) [spring-cloud-context-3.1.1.jar!/:3.1.1]
	at org.springframework.cloud.bootstrap.config.PropertySourceLocator.locateCollection(PropertySourceLocator.java:47) [spring-cloud-context-3.1.1.jar!/:3.1.1]
	at org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration.initialize(PropertySourceBootstrapConfiguration.java:95) [spring-cloud-context-3.1.1.jar!/:3.1.1]
	at org.springframework.boot.SpringApplication.applyInitializers(SpringApplication.java:612) [spring-boot-2.6.3.jar!/:2.6.3]
	at org.springframework.boot.SpringApplication.prepareContext(SpringApplication.java:380) [spring-boot-2.6.3.jar!/:2.6.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:301) [spring-boot-2.6.3.jar!/:2.6.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1303) [spring-boot-2.6.3.jar!/:2.6.3]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1292) [spring-boot-2.6.3.jar!/:2.6.3]
	at com.dm.study.cloud.DmUserApplication.main(DmUserApplication.java:27) [classes!/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_271]
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.0_271]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) ~[na:1.8.0_271]
	at java.lang.reflect.Method.invoke(Unknown Source) ~[na:1.8.0_271]
	at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:49) [dm-user.jar:na]
	at org.springframework.boot.loader.Launcher.launch(Launcher.java:108) [dm-user.jar:na]
	at org.springframework.boot.loader.Launcher.launch(Launcher.java:58) [dm-user.jar:na]
	at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:88) [dm-user.jar:na]
Caused by: java.nio.charset.MalformedInputException: Input length = 1
	at java.nio.charset.CoderResult.throwException(Unknown Source) ~[na:1.8.0_271]
	at sun.nio.cs.StreamDecoder.implRead(Unknown Source) ~[na:1.8.0_271]
	at sun.nio.cs.StreamDecoder.read(Unknown Source) ~[na:1.8.0_271]
	at java.io.InputStreamReader.read(Unknown Source) ~[na:1.8.0_271]
	at org.yaml.snakeyaml.reader.UnicodeReader.read(UnicodeReader.java:125) ~[snakeyaml-1.29.jar!/:na]
	at org.yaml.snakeyaml.reader.StreamReader.update(StreamReader.java:183) ~[snakeyaml-1.29.jar!/:na]
	... 41 common frames omitted
```

命令中增加-Dfile.encoding=UTF-8
```
java -jar -Dfile.encoding=UTF-8 springboot.jar
```
