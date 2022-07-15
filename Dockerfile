FROM openjdk:11.0.13-jre-slim-buster

ENV TZ='GMT-3'

VOLUME /tmp

EXPOSE 8083

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} ido-business.jar

ENTRYPOINT ["java","-Xmx512M","-jar","-Dspring.profiles.active=${ENV}","-Dspring.profiles.active=${ENV}","/ido-business.jar"]
