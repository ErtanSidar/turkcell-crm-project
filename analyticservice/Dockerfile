FROM amazoncorretto:21-alpine
WORKDIR /app
COPY ./target/*jar analyticservice.jar
ENTRYPOINT ["java", "-jar", "analyticservice.jar"]


FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
COPY --from=build /app/target/*jar /app/planservice.jar
ENTRYPOINT ["java", "-jar", "/app/planservice.jar"]