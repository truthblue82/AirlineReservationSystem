FROM eclipse-temurin:17-jdk-jammy

ARG EXTRACTED=target/extracted
EXPOSE 8761

COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]