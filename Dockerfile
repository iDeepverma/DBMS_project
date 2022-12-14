# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy as development

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src

FROM eclipse-temurin:17-jdk-jammy as prod
WORKDIR /app
EXPOSE 8080
COPY --from=development /target/*.jar ./
CMD ["java", "-jar","*.jar"]