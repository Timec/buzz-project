FROM openjdk:8-jre
ARG JAR_FILE=build/libs/buzz-project*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
