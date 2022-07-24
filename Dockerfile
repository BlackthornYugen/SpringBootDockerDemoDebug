# Step : Test and package
FROM maven:3-openjdk-18-slim as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /build/src
RUN mvn package

# Step : Package image
FROM openjdk:18-slim
COPY --from=target /build/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]


