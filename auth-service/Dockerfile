FROM maven:3.9.6-eclipse-temurin-21 AS build
RUN mkdir -p /home/app/backend/auth-service
COPY . /home/app/backend/auth-service
WORKDIR /home/app/backend/auth-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /home/app/backend/auth-service
COPY --from=build /home/app/backend/auth-service/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
