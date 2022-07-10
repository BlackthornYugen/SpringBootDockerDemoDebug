# Step : Test and package
FROM maven:3.5.4-jdk-8 as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /build/src
RUN  mvn package

# Step : Package image
FROM openjdk:8-jre-alpine
COPY --from=target /build/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]


