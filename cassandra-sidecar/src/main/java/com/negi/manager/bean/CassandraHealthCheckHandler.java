package com.negi.manager.bean;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;

public class CassandraHealthCheckHandler implements HealthCheckHandler {

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus currentStatus) {
        return InstanceInfo.InstanceStatus.UP;
    }

}
