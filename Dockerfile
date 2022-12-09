FROM openjdk:17
ADD target/GamingBlog-0.0.1-SNAPSHOT.war .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GamingBlog-0.0.1-SNAPSHOT.war"]