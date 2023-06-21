FROM openjdk:11
VOLUME /tmp
ADD ./target/service-usuario-0.0.1-SNAPSHOT.jar service-usuarios.jar
ENTRYPOINT ["java","-jar","/service-usuarios.jar"]