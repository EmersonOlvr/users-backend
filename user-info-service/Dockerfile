FROM maven:3.9.6-eclipse-temurin-21 AS build
RUN mkdir -p /home/app/backend/user-info-service
COPY . /home/app/backend/user-info-service
WORKDIR /home/app/backend/user-info-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /home/app/backend/user-info-service
COPY --from=build /home/app/backend/user-info-service/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
