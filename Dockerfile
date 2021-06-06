FROM maven:3.8-openjdk-8 AS build
COPY src /home/src
COPY pom.xml /home
RUN mvn -f /home/pom.xml clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=build /home/target/*.jar partner-registry.jar
ENTRYPOINT ["java","-jar","/partner-registry.jar"]
