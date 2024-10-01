#基础镜像
FROM openjdk:8

#指定工作目录
WORKDIR /app

#指定时区为东八区
RUN rm -f /etc/localtime \
&& ln -sv /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone

#openjdk:8 的 Java 可执行程序路径
ENV PATH=${PATH}:/usr/local/openjdk-8/bin

#将本地的jar复制到容器内/app/app.jar路径下
COPY ./lab-service-1.0.jar /app/app.jar

#暴露端口
EXPOSE 8888

#启动命令，注意路径
ENTRYPOINT ["/usr/local/openjdk-8/bin/java","-jar","/app/app.jar","--spring.profiles.active=prod"]