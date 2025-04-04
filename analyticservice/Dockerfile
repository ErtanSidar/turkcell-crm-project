
# Aşama 1: Build Aşaması (Maven veya Gradle'a göre uyarlayın)
FROM maven:3.9.5-jdk-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Aşama 2: Çalıştırma Aşaması (Sadece gerekli runtime bileşenlerini içerir)
FROM eclipse-temurin:21-jre-jammy

# Çalışma dizinini ayarla
WORKDIR /app

# Build aşamasından JAR dosyasını kopyala
COPY --from=builder /app/target/*.jar analyticservice.jar

# Ortam değişkenlerini tanımla (isteğe bağlı)
# ENV JAVA_OPTIONS="-Xms128m -Xmx512m"

# Uygulamayı çalıştırmak için komut
ENTRYPOINT ["java", "-jar", "analyticservice.jar"]

# Uygulamanızın dinleyeceği portu belirtin (isteğe bağlı)
# EXPOSE 8080