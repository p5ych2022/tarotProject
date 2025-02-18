# Dockerfile for Backend (Spring Boot)
FROM mirror.ccs.tencentyun.com/library/openjdk:17-jdk-slim
# Set working directory
WORKDIR /app

# Copy the entire backend project into the container
#COPY . /app
COPY .env .env


# Copy jar from the previous build stage
COPY /target/tarot-0.0.1-SNAPSHOT.jar app.jar

# Expose the backend port
EXPOSE 55551

# Set the entry point to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]