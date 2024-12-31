FROM openjdk:17-jdk-alpine
#FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/your-product.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]