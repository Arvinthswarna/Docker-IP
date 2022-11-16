FROM openjdk:18.0.2
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} my-app.jar
ENTRYPOINT ["java","-jar","/my-app.jar"]
EXPOSE 4000