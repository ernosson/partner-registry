FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} partner-registry.jar
ENTRYPOINT ["java","-jar","/partner-registry.jar"]
