FROM openjdk:11 AS builder
LABEL authors="washington"

WORKDIR /app

COPY /config-server/pom.xml .
COPY /config-server/src ./src
COPY /config-server/.mvn ./.mvn
COPY /config-server/mvnw .

#RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
#================================================================
FROM openjdk:11

WORKDIR /app

COPY --from=builder /app/target/config-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]
