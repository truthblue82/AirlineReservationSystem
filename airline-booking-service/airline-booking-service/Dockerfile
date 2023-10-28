FROM eclipse-temurin:17-jdk-jammy

ARG EXTRACTED=target/extracted
EXPOSE 8098

COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/application/ ./
COPY wait-for-it.sh ./
COPY docker-entrypoint.sh ./

ENTRYPOINT ["./docker-entrypoint.sh"]