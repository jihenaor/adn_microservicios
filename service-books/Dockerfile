FROM openjdk:17
VOLUME /tmp
ADD ./target/service-book-0.0.1-SNAPSHOT.jar service-book.jar

ENTRYPOINT ["java","-jar","/service-book.jar"]