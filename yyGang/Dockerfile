FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/yyGang-0.0.1-SNAPSHOT.jar app.jar
ENV TZ=Asia/Seoul
EXPOSE 8088
CMD ["java", "-jar", "app.jar"]