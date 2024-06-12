# Use the official Maven image with JDK 17 for a build environment
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app

# Copy the pom.xml and download dependencies to cache them
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for running the application
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (optional, adjust according to your application)
EXPOSE 8080

# Add a health check (optional, adjust the path according to your application)
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
