FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/analyticservice-0.0.1-SNAPSHOT.jar analyticservice.jar
ENTRYPOINT ["java", "-jar", "analyticservice.jar"]