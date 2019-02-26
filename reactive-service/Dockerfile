FROM openjdk:8u171-jre-alpine
# FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar reactive-service-1.0.0-SNAPSHOT.jar
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:10300/actuator/health || exit 1
COPY target/reactive-service-1.0.0-SNAPSHOT.jar .
