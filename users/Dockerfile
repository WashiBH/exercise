FROM openjdk:17-jdk-alpine AS builder
LABEL authors="washington"

WORKDIR /app

COPY /users/pom.xml .
COPY /users/src ./src
COPY /users/mvnw .
COPY /users/.mvn ./.mvn

#RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
#================================================================
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/users-0.0.1-SNAPSHOT.jar .

EXPOSE 8581

ENTRYPOINT ["java", "-jar", "users-0.0.1-SNAPSHOT.jar"]
