FROM maven:3.8-openjdk-8 AS build
COPY src /home/backend/src
COPY pom.xml /home/backend
RUN mvn -f /home/backend/pom.xml clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=build /home/backend/target/*.jar partner-registry.jar
ENTRYPOINT ["java","-jar","/partner-registry.jar"]
