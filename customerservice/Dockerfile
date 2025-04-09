FROM amazoncorretto:21
WORKDIR /app
COPY ./target/*jar customerservice.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5003", "-jar", "customerservice.jar"]
