# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# Set the working directory
WORKDIR /app

# Copy the built JAR into the image
COPY target/maintenance-api-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application with Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.main.banner-mode=off"]
