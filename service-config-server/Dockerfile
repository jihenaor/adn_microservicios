FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD ./target/service-config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]
RUN rm -rf /tmp/*
