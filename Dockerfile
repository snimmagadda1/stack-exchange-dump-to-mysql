FROM adoptopenjdk/openjdk11:alpine

ARG JAR_FILE=target/spring-batch-starter-template-0.0.1.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]