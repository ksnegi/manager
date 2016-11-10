package com.negi.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

@Configuration
public class CassandraConfig {

    private static final String KEYSPACE = "cassandra.keyspace";
    private static final String CONTACT_POINTS = "cassandra.contactPoints";
    private static final String PORT = "cassandra.cqlPort";
    private static final String CLUSTER_NAME = "cassandra.clusterName";

    @Autowired
    private Environment environment;

    public CassandraConfig() {
    }

    private String getKeyspaceName() {
        return environment.getProperty(KEYSPACE);
    }

    private String getContactPoints() {
        return environment.getProperty(CONTACT_POINTS);
    }

    private int getPortNumber() {
        return Integer.parseInt(environment.getProperty(PORT));
    }

    private String getClusterName() {
        return environment.getProperty(CLUSTER_NAME);
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(getContactPoints());
        cluster.setPort(getPortNumber());
        return cluster;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        return new BasicCassandraMappingContext();
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
        cassandraSessionFactoryBean.setCluster(cluster().getObject());
        cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
        cassandraSessionFactoryBean.setConverter(converter());
        cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
        return cassandraSessionFactoryBean;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }

}
