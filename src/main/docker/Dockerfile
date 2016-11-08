FROM java:8
VOLUME /tmp
ADD negi-manager-0.0.1-SNAPSHOT.jar manager.jar
EXPOSE 8080
RUN sh -c 'touch /manager.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/manager.jar"]