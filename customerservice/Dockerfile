FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar customerservice.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5003", "-jar", "customerservice.jar"]