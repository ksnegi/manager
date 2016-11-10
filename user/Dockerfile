FROM java:8
VOLUME /tmp
ADD target/manager-user-0.0.1-SNAPSHOT.jar webapp.jar
EXPOSE 8080
RUN sh -c 'touch /webapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/webapp.jar"]