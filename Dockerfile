FROM openjdk:21
WORKDIR /app
COPY mvnw mvnw
COPY .mvn .mvn
COPY src src
COPY pom.xml pom.xml
RUN ./mvnw install -Dmaven.test.skip
EXPOSE 8080
CMD ["java", "-jar", "target/VehicleBooker-0.0.1-SNAPSHOT.jar"]