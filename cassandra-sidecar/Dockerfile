FROM java:8
ADD target/manager-cassandra-sidecar-0.0.1-SNAPSHOT.jar cassandra-sidecar.jar
CMD /usr/bin/java -Xmx400m -Xms400m -jar cassandra-sidecar.jar
EXPOSE 8080