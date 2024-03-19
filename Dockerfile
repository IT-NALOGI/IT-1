FROM openjdk:17

VOLUME /tmp

EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/service1-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} service1.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service1.jar"]
