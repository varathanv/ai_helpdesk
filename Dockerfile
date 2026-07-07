# Step 1: Build the application using Maven
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Step 2: Run the application using a lightweight image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/helpdesk-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
