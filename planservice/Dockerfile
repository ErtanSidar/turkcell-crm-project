FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar planservice.jar
ENTRYPOINT ["java", "-jar", "planservice.jar"]