FROM maven:3.9.6-eclipse-temurin-21 as build

WORKDIR /app
COPY . /app/

RUN mvn clean package -DskipTests
# -----------------------------------
FROM eclipse-temurin:21-jdk
COPY --from=build /app/target/planservice-0.0.1-SNAPSHOT.jar /app/planservice.jar

ENTRYPOINT ["java", "-jar", "/app/planservice.jar"]
