FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar customerservice.jar
ENTRYPOINT ["java", "-jar", "customerservice.jar"]