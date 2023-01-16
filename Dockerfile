FROM openjdk:17
ADD target/springsecurity-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]