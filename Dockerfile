FROM adoptopenjdk/openjdk11:alpine

ARG JAR_FILE=target/stacke-dump-0.0.1.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]