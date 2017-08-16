# swagger2-spring-boot-starter
Spring boot starter for swagger2

**使用版本**

> Spring Boot `1.5.6-RELEASE`

> Springfox Swagger `2.7.0` 

**使用说明**

`注意: 暂时未上传至maven仓库,需自行install到本地仓库`
```xml
<dependency>
    <groupId>com.k4hub</groupId>
    <artifactId>swagger2-spring-boot-starter</artifactId>
    <version>1.0-RELEASE</version>
</dependency>
```
```java
@EnableSwagger2Tools // <--
@SpringBootApplication
public class Swagger2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Swagger2DemoApplication.class, args);
    }
}
```

**使用实例**
```yaml
swagger2:
  api-info: # API信息
    title: haha~ # API标题
    description: hahaha~ # API描述
    license: MIT # 许可证
    license-url: https://opensource.org/licenses/MIT # 许可证URl
    terms-of-service-url: http://www.swagger.io # 服务条款URL
    version: 1.0 # API版本
    contact: # 联系人信息
      email: xtremaplux@gmail.com # 联系人邮箱
      name: xhusky # 联系人姓名
      url: https://github.com/xhusky # 联系人URL
  base-package: com.github, com.oschina # swagger解析包路径
  base-path: /v1/**, /v3/**, /v4/** # swagger解析URL规则(ANT规则)
  exclude-path: /error/** # swagger排除解析URL规则(ANT规则)
  global-operation-parameter: # 全局参数配置,比如token 放在header中
    -
      name: token # 参数名称
      description: access token # 参数说明
      required: true # 是否必须
      model-ref: string # 参数数据类型
      paramType: header # 参数类型,可选 query, path, body, header, 详细查看swagger文档

```

> 参考了didi做的[spring-boot-starter-swagger](https://github.com/dyc87112/spring-boot-starter-swagger)
