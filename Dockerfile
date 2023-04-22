FROM openjdk:19-jdk-alpine
ADD target/mycoolapp-0.0.1-SNAPSHOT.jar mycoolapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/mycoolapp-0.0.1-SNAPSHOT.jar"]