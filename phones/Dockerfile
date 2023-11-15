FROM openjdk:17-jdk-alpine AS builder
LABEL authors="washington"

WORKDIR /app

COPY /phones/pom.xml .
COPY /phones/src ./src
COPY /phones/mvnw .
COPY /phones/.mvn ./.mvn

#RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
#================================================================
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/phones-0.0.1-SNAPSHOT.jar .

EXPOSE 8582

ENTRYPOINT ["java", "-jar", "phones-0.0.1-SNAPSHOT.jar"]
