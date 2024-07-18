FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /target/WebApp-1.0-SNAPSHOT.jar /app/connect.jar
ENTRYPOINT ["java", "-jar", "connect.jar"]