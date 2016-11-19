FROM java:8
ADD target/manager-config-server-0.0.1-SNAPSHOT.jar config-server.jar
CMD /usr/bin/java -Xmx400m -Xms400m -jar config-server.jar
EXPOSE 8888