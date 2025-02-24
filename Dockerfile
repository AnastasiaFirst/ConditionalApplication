FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY target/conditionalApplication-0.0.1-SNAPSHOT.jar myapp.jar

CMD ["java","-jar","/myapp.jar"]