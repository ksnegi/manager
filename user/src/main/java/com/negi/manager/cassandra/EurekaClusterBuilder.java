package com.negi.manager.cassandra;

import com.datastax.driver.core.Cluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
public class EurekaClusterBuilder extends Cluster.Builder {

    private final DiscoveryClient discoveryClient;

    private final CassandraProperties cassandraProperties;

    @Autowired
    public EurekaClusterBuilder(DiscoveryClient discoveryClient, CassandraProperties cassandraProperties) {
        this.discoveryClient = discoveryClient;
        this.cassandraProperties = cassandraProperties;
    }

    @Override
    public List<InetSocketAddress> getContactPoints() {
        return toEurekaInstances(discoveryClient, cassandraProperties.getClusterName())
                .stream().map(eurekaInstancesToSocket())
                .collect(toList());
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