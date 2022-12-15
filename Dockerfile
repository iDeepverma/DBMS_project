# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
COPY projectImage ./projectImage
COPY product-photos ./product-photos
RUN ./mvnw package
EXPOSE 8080
CMD ["java", "-jar","./target/dbms.jar"]