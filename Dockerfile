FROM amazoncorretto:23.0.2-alpine3.21
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
