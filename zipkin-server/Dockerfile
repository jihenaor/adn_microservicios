FROM openjdk:11
VOLUME /tmp
ADD ./zipkin-server-2.12.9-exec.jar zipkin.jar

ENTRYPOINT ["java","-jar","/zipkin.jar"]