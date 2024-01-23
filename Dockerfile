# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file and build the project
COPY . /app
RUN ./gradlew build

# Expose the port the app will run on
EXPOSE 8080

# Run the application
# ENTRYPOINT ["sleep", "infinity"]
CMD ["java", "-jar", "build/libs/change-service-0.0.1-SNAPSHOT.jar"]
