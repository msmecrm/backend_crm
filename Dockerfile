# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/crm-0.0.1-SNAPSHOT.jar crm.jar

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "crm.jar"]
