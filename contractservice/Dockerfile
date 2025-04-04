FROM maven:3.9.6-eclipse-temurin-21 as build

WORKDIR /app
COPY . /app/

RUN mvn clean package -DskipTests
# -----------------------------------
FROM eclipse-temurin:21-jdk
COPY --from=build /app/target/*.jar /app/contractservice.jar

ENTRYPOINT ["java", "-jar", "/app/contractservice.jar"]