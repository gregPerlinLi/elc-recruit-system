FROM openjdk:17-jdk-alpine
#维护者
LABEL maintainer="lihaolin13@outlook.com"
ARG JAR_FILE
#设置环境变量
ENV TIME_ZONE=Asia/Shanghai
ENV LANG=C.UTF-8
ENV SERVER_PORT=${SERVER_PORT} \
    SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE} \
    NACOS_DISCOVERY_SERVER=${NACOS_DISCOVERY_SERVER} \
    NACOS_CONFIG_SERVER=${NACOS_CONFIG_SERVER}
    # JAVA_OPT_EXT=${JAVA_OPT_EXT}

#在容器构建过程中执行的命令
RUN echo "https://mirrors.aliyun.com/alpine/v3.6/main" > /etc/apk/repositories \
    && echo "https://mirrors.aliyun.com/alpine/v3.6/community" >> /etc/apk/repositories \
    #以上两行指定alpine(Linux)源
    #升级包
    && apk update upgrade \
    #安装常用命令
    && apk add --no-cache procps unzip curl bash tzdata \
    #安装宋体
    && apk add ttf-dejavu \
    #同步时区
    && ln -snf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime  \
    && echo ${TIME_ZONE} > /etc/timezone
#声明服务运行的端口
EXPOSE 91
#将当前目录下的jar包复制到docker容器的/目录下
ADD ${JAR_FILE} /app.jar
#指定docker容器启动时执行的命令
ENTRYPOINT ["java", \
            "-jar", \
            "-Dserver.port=${SERVER_PORT}", \
            "-Duser.timezone=Asia/Shanghai", \
            "-Dspring.profiles=${SPRING_PROFILES_ACTIVE}", \
            "-Dspring.cloud.nacos.discovery.server-addr=${NACOS_DISCOVERY_SERVER}", \
            "-Dspring.cloud.nacos.config.server-addr=${NACOS_CONFIG_SERVER}", \
            # "${JAVA_OPT_EXT}", \
            "/app.jar"]