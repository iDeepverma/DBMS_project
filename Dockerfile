# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy AS development
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package
EXPOSE 8080
CMD ["java", "-jar","./target/dbms.jar"]