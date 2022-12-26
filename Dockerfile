FROM openjdk:17.0-jdk
ADD target/gamingblog-0.0.1-SNAPSHOT.war .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gamingblog-0.0.1-SNAPSHOT.war"]