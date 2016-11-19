package com.negi.manager.bean;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class CassandraHealthCheckHandler implements HealthCheckHandler, ApplicationContextAware, InitializingBean {

    private final CompositeHealthIndicator healthIndicator;
    private ApplicationContext applicationContext;

    @Autowired
    private DiscoveryClient discoveryClient;

    public CassandraHealthCheckHandler(HealthAggregator healthAggregator) {
        Assert.notNull(healthAggregator, "HealthAggregator must not be null");
        this.healthIndicator = new CompositeHealthIndicator(healthAggregator);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        final Map<String, HealthIndicator> healthIndicators = applicationContext.getBeansOfType(HealthIndicator.class);
        for (Map.Entry<String, HealthIndicator> entry : healthIndicators.entrySet()) {
            healthIndicator.addHealthIndicator(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus currentStatus) {
        List<InetSocketAddress> hostnames = toEurekaInstances(discoveryClient, "cassandra")
                .stream().map(eurekaInstancesToSocket())
                .collect(toList());

        Cluster cluster = Cluster.builder().addContactPointsWithPorts(hostnames).build();

        Session session = cluster.connect("system");

        if (session.isClosed()) {
            return InstanceInfo.InstanceStatus.DOWN;
        } else {
            return InstanceInfo.InstanceStatus.UP;
        }
    }

    protected List<ServiceInstance> toEurekaInstances(DiscoveryClient discoveryClient, final String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    protected final Function<ServiceInstance, InetSocketAddress> eurekaInstancesToSocket() {
        return instanceInfo -> {
            final String localAddress = instanceInfo.getHost();
            final InetSocketAddress address = new InetSocketAddress(localAddress, instanceInfo.getPort());
            return address;
        };
    }

}
