# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /springbootprojects

# Copy the Maven wrapper scripts and project files into the container
COPY .mvn/ .mvn
COPY mvnw mvnw
COPY pom.xml .

# Copy the source code
COPY src src

# Run Maven build to create the application jar file
RUN ./mvnw clean package -DskipTests

# Expose the port on which the Spring Boot application runs (default is 8080)
EXPOSE 8080

# Add a health check to ensure the application is running
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "target/stock-quotes-system-0.0.1-SNAPSHOT.jar"]

# Optional: set environment variables for customization
# ENV SPRING_PROFILES_ACTIVE=prod
