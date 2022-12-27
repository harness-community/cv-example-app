FROM openjdk:19 AS builder
COPY pom.xml /app/pom.xml
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
WORKDIR /app
RUN ./mvnw compile

COPY src /app/src
RUN ./mvnw install

FROM openjdk:19
COPY --from=builder /app/target/cv-example-app-1.0.0.jar /app/cv-example-app-1.0.0.jar
WORKDIR /app
CMD java -jar /app/cv-example-app-1.0.0.jar