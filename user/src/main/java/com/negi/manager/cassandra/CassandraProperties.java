package com.negi.manager.cassandra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CassandraProperties {

    private static final String CLUSTERNAME = "cassandra.clusterName";
    private static final String PORT = "cassandra.cqlPort";
    private static final String KEYSPACE = "cassandra.keyspace";

    @Autowired
    private Environment environment;

    public CassandraProperties() {
    }

    public String getClusterName() {
        return environment.getProperty(CLUSTERNAME);
    }

    public int getPortNumber() {
        return Integer.parseInt(environment.getProperty(PORT));
    }

    public String getKeyspaceName() {
        return environment.getProperty(KEYSPACE);
    }

}