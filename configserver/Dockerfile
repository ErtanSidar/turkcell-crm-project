FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar configserver.jar
ENTRYPOINT ["java", "-jar", "configserver.jar"]