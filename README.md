# GDUTELC 招新系统

## 本招新系统使用的模块：

- `cloud-api-commons`：通用API模块，用于放置通用的实体类
- `cloud-consumer-recruit91`：消费者模块，放置对外访问的所有接口
- `cloud-provider-apply9001`：生产者模块，放置与面试者报名相关的服务
- `cloud-provider-interview9002`：生产者模块，放置与面试相关的服务
- `cloud-provider-message9003`：生产者模块，放置与微信推送相关的服务

## 本项目使用的技术版本部分参考：
```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <junit.version>4.13.2</junit.version>
    <log4j.version>2.17.2</log4j.version>
    <lombok.version>1.18.24</lombok.version>
    <mysql.version>8.0.28</mysql.version>
    <druid.version>1.2.8</druid.version>
    <mybatis.spring.boot.version>2.2.2</mybatis.spring.boot.version>
  </properties>
```

## 关于数据库：

数据库架构请参考：[./db/recruit-system.sql](./db/recruit-system.sql) 文件

<span style='color:yellow;background:背景颜色;font-size:文字大小;'>注意⚠️：为了数据库安全起见，MySQL数据库账户请使用`recruit`而不是`root`账户进行登录（密码和用户名一致），相应地，`recruit`只授权对`recruit_system`数据库的读写许可！</span>

```sql
CREATE USER 'recruit'@'localhost' IDENTIFIED BY 'recruit';
GRANT ALL PRIVILEGES ON recruit_system.* TO 'recruit'@'localhost';
```

Redis数据库使用的索引为`5`

## 关于Nacos注册中心

此系统的Nacos所使用的版本为 **`2.1.0`**

Nacos采用单机部署，MySQL持久化策略

持久化数据库建表请参阅Nacos包下此目录下的SQL文件: `/mynacos/nacos/conf`目录下找到SQL脚本`nacos-mysql.sql`，并执行，数据库名称为`nacos_config`

持久化数据库请使用`nacos`用户登录

如何设置Nacos持久化: 在Nacos包下的配置文件`application.properties`中的末尾新增如下配置:

```properties
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai
db.user=nacos
db.password=nacos
```
## 关于Sentinel流控

此系统使用的SentinelDashboard版本为 **`1.8.4`**

Sentinel的限流规则通过Nacos进行持久化

由于Java17与Sentinel之间存在兼容性问题，因此在启动SentinelDashboard的时候请使用以下的指令来启用

```shell
$ java -jar --add-exports=java.base/sun.net.util=ALL-UNNAMED sentinel-dashboard-1.8.4.jar  
```

## 关于 Swagger

Swagger API 文档地址:

```http request
http://127.0.0.1:91/swagger-ui/index.html#
```

使用示例可以参考: 

[cloud-consumer-recruit91/src/main/java/com/gdutelc/recruit/controller/DemoController.java](cloud-consumer-recruit91/src/main/java/com/gdutelc/recruit/controller/DemoController.java)