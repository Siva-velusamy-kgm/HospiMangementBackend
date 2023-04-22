FROM openjdk:19-jdk-alpine
ADD target/GraphQL-1-0.0.1-SNAPSHOT.jar GraphQL-1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jmar", "/mycoolapp-0.0.1-SNAPSHOT.jar"]