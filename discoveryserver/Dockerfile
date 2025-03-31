FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar discoveryserver.jar
ENTRYPOINT ["java", "-jar", "discoveryserver.jar"]