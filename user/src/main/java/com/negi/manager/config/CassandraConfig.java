package com.negi.manager.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.negi.manager.dao" })
public class CassandraConfig {

    @Autowired
    private Environment env;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(StringUtils.join(getContactPoints(), ","));
        cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));
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
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(env.getProperty("cassandra.keyspace"));
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);

        return session;
    }

    @Bean
    public
    CassandraOperations cassandraTemplate()throws Exception {
        return new CassandraTemplate(session().getObject());
    }

    private List<String> getContactPoints() {
        return toEurekaInstances(discoveryClient, env.getProperty("cassandra.clusterName"))
                .stream().map(eurekaInstancesToHost())
                .collect(toList());
    }

    private List<ServiceInstance> toEurekaInstances(DiscoveryClient discoveryClient, final String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    private final Function<ServiceInstance, String> eurekaInstancesToHost() {
        return instanceInfo -> {
            final String localAddress = instanceInfo.getHost();
            return localAddress;
        };
    }

}
