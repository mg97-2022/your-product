########### Build Stage
#FROM maven:3.9.6-eclipse-temurin-17 AS build
#WORKDIR /build
#COPY pom.xml .
#RUN mvn dependency:go-offline # Download dependencies first for caching
#COPY src ./src
#RUN mvn clean package -DskipTests # Build the application
#
############ Runtime stage
#FROM eclipse-temurin:17-jdk
###### Environment variables
##ENV ACTIVE_PROFILE=${PROFILE}
#WORKDIR /app
#COPY --from=build /build/target/your-product.jar app.jar
#EXPOSE 8080
###### Run the application
##ENTRYPOINT ["java", "-jar", "app.jar"]
#CMD java -Dspring.profiles.active=dev -jar app.jar


################################## OLD
FROM openjdk:17-jdk-alpine
#WORKDIR /app
#COPY target/your-product.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
