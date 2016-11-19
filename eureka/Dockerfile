FROM java:8
VOLUME /tmp
ADD target/manager-eureka-0.0.1-SNAPSHOT.jar eureka.jar
RUN bash -c 'touch /eureka.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]