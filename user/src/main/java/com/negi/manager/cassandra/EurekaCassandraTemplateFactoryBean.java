package com.negi.manager.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.Ordered;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

@Component("cassandraTemplate")
public class EurekaCassandraTemplateFactoryBean implements SmartLifecycle, FactoryBean<CassandraTemplate>, Ordered {

    private final EurekaClusterBuilder eurekaClusterBuilder;
    private final CassandraProperties cassandraProperties;
    private final CassandraTemplateWrapper cassandraTemplateWrapper;

    private boolean running;

    @Autowired
    public EurekaCassandraTemplateFactoryBean(
            EurekaClusterBuilder eurekaClusterBuilder, CassandraProperties cassandraProperties) {
        this.eurekaClusterBuilder = eurekaClusterBuilder;
        this.cassandraProperties = cassandraProperties;
        this.cassandraTemplateWrapper = new CassandraTemplateWrapper();
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {

    }

    @Override
    public void start() {
        final Cluster cluster = this.eurekaClusterBuilder.build();
        Session session = cluster.connect(this.cassandraProperties.getKeyspaceName());
        this.cassandraTemplateWrapper.setSession(session);
        running = true;
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public CassandraTemplate getObject() throws Exception {
        return this.cassandraTemplateWrapper;
    }

    @Override
    public Class<?> getObjectType() {
        return CassandraTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}