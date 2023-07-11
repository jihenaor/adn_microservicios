FROM maven:3.9.3-eclipse-temurin-17 AS BUILDER

WORKDIR build

COPY src src
COPY pom.xml pom.xml
COPY book-commons-0.0.1-SNAPSHOT.jar /build/book-commons.jar

RUN mvn install:install-file -Dfile=/build/book-commons.jar -DgroupId=com.ceiba.adn.app.commons -DartifactId=book-commons -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

RUN mvn clean package -DskipTests
#RUN mvn clean package
FROM bellsoft/liberica-openjdk-alpine:17.0.7
#FROM openjdk:17

# VOLUME /tmp

COPY --from=BUILDER /build/target/*.jar service-book.jar
# ADD ./target/service-book-0.0.1-SNAPSHOT.jar service-book.jar

CMD ["java","-jar","/service-book.jar"]