FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar userservice.jar
ENTRYPOINT ["java", "-jar", "userservice.jar"]