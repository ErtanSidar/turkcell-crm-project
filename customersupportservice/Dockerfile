FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar customersupportservice.jar
ENTRYPOINT ["java", "-jar", "customersupportservice.jar"]