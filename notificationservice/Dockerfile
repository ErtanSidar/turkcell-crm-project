FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar notificationservice.jar
ENTRYPOINT ["java", "-jar", "notificationservice.jar"]