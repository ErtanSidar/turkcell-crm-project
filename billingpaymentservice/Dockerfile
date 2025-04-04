FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar billingpaymentservice.jar
ENTRYPOINT ["java", "-jar", "billingpaymentservice.jar"]