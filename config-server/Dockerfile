FROM openjdk:11
LABEL authors="washington"
WORKDIR /app
COPY ./target/config-server-0.0.1-SNAPSHOT.jar /app
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]
