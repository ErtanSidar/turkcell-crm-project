FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar analyticservice.jar
ENTRYPOINT ["java", "-jar", "analyticservice.jar"]