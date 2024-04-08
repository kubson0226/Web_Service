FROM eclipse-temurin:17.0.6_10-jre

LABEL org.opencontainers.image.title="gateway"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8082

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_DEFER_DATASOURCE_INITIALIZATION=true

ENV ISA_GATEWAY_HOST=http://localhost:8082
ENV ISA_RESTAURANT_URL=http://localhost:8080
ENV ISA_WORKER_URL=http://localhost:8081

ENV SPRING_H2_CONSOLE_enabled=true

EXPOSE 8082

COPY target/Gateway-${VERSION}.jar /opt/gateway/gateway.jar

CMD ["java", "-jar", "/opt/gateway/gateway.jar"]