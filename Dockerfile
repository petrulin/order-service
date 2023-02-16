########
# Dockerfile to build order-service container image
#
########
FROM openjdk:17-slim

LABEL maintainer="Petrulin Alexander"

COPY target/order-service-*.jar app.jar

EXPOSE 8010

ENTRYPOINT ["java","-jar","/app.jar"]
