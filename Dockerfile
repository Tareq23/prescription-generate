
FROM openjdk:17-jdk-slim

WORKDIR /app

ADD target/prescription-service.jar prescription-service.jar

COPY src/main/resources/static/data/prescription.json /app/data/prescription.json

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "prescription-service.jar"]

