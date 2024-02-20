# Use a base image with JDK and Gradle installed
FROM adoptopenjdk/openjdk11:alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle project files to the container
COPY . .

# Build the Spring Boot application using the Gradle wrapper
RUN ./gradlew build

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "build/libs/rest-service-0.0.1-SNAPSHOT.jar"]
