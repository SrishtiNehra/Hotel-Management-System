FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR  /app
COPY pom.xml .
ADD src ./src
RUN mvn clean package

FROM openjdk:17.0.2-jdk
WORKDIR  /app
COPY --from=builder /app/target/hms.jar /app/app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]