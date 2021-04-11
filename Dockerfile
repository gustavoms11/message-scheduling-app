FROM openjdk:11
ADD target/message-scheduling-0.0.1-SNAPSHOT.jar message-scheduling-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "message-scheduling-0.0.1-SNAPSHOT.jar"]