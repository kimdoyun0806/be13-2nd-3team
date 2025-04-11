FROM eclipse-temurin:21-jre-alpine
LABEL maintainer="kimdoyun0806 <gim_doyun@naver.com>"
LABEL version="1.0"
WORKDIR /app
COPY ./build/libs/yyGang-0.0.1-SNAPSHOT.jar ./app.jar
ARG BUILD_PORT=8088
ENV TZ=Asia/Seoul
ENV SERVER_PORT=$BUILD_PORT
EXPOSE $SERVER_PORT
CMD ["sh", "-c", "java -jar app.jar --server.port=$SERVER_PORT"]