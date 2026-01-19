FROM gradle:9.2.1-jdk17-alpine
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew clean bootJar --no-daemon

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/shorto-0.0.1-SNAPSHOT.jar"]