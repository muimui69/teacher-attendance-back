FROM maven:3.8.4-openjdk-17 AS BUILD
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-alpine 
WORKDIR /app
COPY --from=BUILD /app/target/teacher-attendance-back-0.0.1-SNAPSHOT.jar ./demo-aws.jar
EXPOSE 8082
CMD ["java","-jar","demo-aws.jar"]
