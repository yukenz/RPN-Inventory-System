FROM --platform=linux/amd64 maven:3.9-amazoncorretto-21-debian AS build
LABEL authors="yukenz"

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests=true

FROM --platform=linux/amd64 bellsoft/liberica-runtime-container:jdk-all-21-cds-musl

WORKDIR /app

COPY --from=build /build/target/*.jar ./myapp.jar

EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]
