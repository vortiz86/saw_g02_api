FROM openjdk:8
ADD target/docker-spring-saw.jar docker-spring-saw.jar 
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "docker-spring-saw.jar"]