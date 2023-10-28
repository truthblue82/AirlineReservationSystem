FROM eclipse-temurin:17-jdk-jammy

ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY $JAR_FILE application.jar
EXPOSE 8443

ENTRYPOINT ["java", "-jar", "/application.jar"]