FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/planservice-0.0.1-SNAPSHOT.jar planservice.jar
ENTRYPOINT ["java", "-jar", "planservice.jar"]