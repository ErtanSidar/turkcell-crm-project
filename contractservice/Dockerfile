FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar contractservice.jar
ENTRYPOINT ["java", "-jar", "contractservice.jar"]