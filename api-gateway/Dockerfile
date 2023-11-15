FROM openjdk:11 AS builder
LABEL authors="washington"

WORKDIR /app

COPY /api-gateway/pom.xml .
COPY /api-gateway/src ./src
COPY /api-gateway/mvnw .
COPY /api-gateway/.mvn ./.mvn

#RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
#================================================================
FROM openjdk:11

WORKDIR /app

COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8588

ENTRYPOINT ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]
