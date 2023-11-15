FROM openjdk:11 AS builder
LABEL authors="washington"

WORKDIR /app

COPY /discovery-server/pom.xml .
COPY /discovery-server/src ./src
COPY /discovery-server/mvnw .
COPY /discovery-server/.mvn ./.mvn

#RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
#================================================================
FROM openjdk:11

WORKDIR /app

COPY --from=builder /app/target/discovery-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "discovery-server-0.0.1-SNAPSHOT.jar"]
