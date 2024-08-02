FROM maven:3.5.0-jdk-8-alpine AS builder
WORKDIR /app
ADD ./target/*.jar /app/app.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone && touch /app/app.jar
ENV JAVA_OPTS=""
ENV PARAMS=""
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /app/app.jar $PARAMS" ]
# docker run --name=hello4 -e PARAMS="--server.port=8888" hello:4