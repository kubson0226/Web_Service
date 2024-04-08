FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="worker"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8081

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:worker
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_DEFER_DATASOURCE_INITIALIZATION=true

EXPOSE 80

COPY target/Worker-${VERSION}.jar /opt/worker/worker.jar

CMD ["java", "-jar", "/opt/worker/worker.jar"]