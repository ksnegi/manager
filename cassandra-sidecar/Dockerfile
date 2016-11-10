FROM java:8
ADD target/manager-cassandra-0.0.1-SNAPSHOT.jar cassandra.jar
CMD /usr/bin/java -Xmx400m -Xms400m -jar cassandra.jar
EXPOSE 8080