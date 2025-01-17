FROM openjdk:23-slim-bullseye

WORKDIR /app

COPY build/libs/producer-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]