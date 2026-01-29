FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="medabdelkader.bouafoura@esprit.tn"
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "app.jar"]
