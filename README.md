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

Redis数据库使用的索引为`5`