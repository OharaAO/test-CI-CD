# 1. Étape de build
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
#COPY /.env ./
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


# 2. Étape runtime
FROM openjdk:21-jdk-slim AS run
WORKDIR /app
# On récupère le jar optimisé
#COPY --from=build /app/.env .
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
