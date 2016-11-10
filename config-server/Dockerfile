FROM java:8
ADD target/manager-config-0.0.1-SNAPSHOT.jar config.jar
CMD /usr/bin/java -Xmx400m -Xms400m -jar config.jar
EXPOSE 8888