FROM amazoncorretto:21
WORKDIR /app
COPY ./target/*jar customerservice.jar
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5003"
ENTRYPOINT ["java", "-jar", "customerservice.jar"]
