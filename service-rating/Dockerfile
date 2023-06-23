FROM openjdk:11
VOLUME /tmp
ADD ./target/service-rating-0.0.1-SNAPSHOT.jar service-rating.jar
ENTRYPOINT ["java","-jar","/service-rating.jar"]