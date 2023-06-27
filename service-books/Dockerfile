FROM maven:3.9.3-eclipse-temurin-17 AS BUILDER

WORKDIR build

COPY src src
COPY pom.xml pom.xml
RUN mvn clean package

FROM openjdk:17

# VOLUME /tmp

COPY --from=BUILDER /builder/target/*.jar service-book.jar
# ADD ./target/service-book-0.0.1-SNAPSHOT.jar service-book.jar

CMD ["java","-jar","/service-book.jar"]