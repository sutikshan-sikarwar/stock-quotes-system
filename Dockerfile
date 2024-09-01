# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /springbootprojects

# Copy the trigger file and project files into the container
COPY thinkhumble-project /springbootprojects/thinkhumble-project
COPY . /springbootprojects

# Expose the port on which the Spring Boot application runs (default is 8080)
EXPOSE 8080

# Add a health check to ensure the application is running
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/springbootprojects/thinkhumble-project"]

# Optional: set environment variables for customization
# ENV SPRING_PROFILES_ACTIVE=prod

# Run the Spring Boot application
CMD ["java", "-jar", "/springbootprojects/thinkhumble-project"]