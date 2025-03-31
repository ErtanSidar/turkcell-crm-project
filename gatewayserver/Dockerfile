FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar gatewayserver.jar
ENTRYPOINT ["java", "-jar", "gatewayserver.jar"]