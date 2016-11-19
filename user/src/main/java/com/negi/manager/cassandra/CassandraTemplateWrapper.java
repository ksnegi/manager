package com.negi.manager.cassandra;

import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraTemplate;


public class CassandraTemplateWrapper extends CassandraTemplate {

    public CassandraTemplateWrapper() {
        this.setConverter(new MappingCassandraConverter());
    }

    @Override
    public void afterPropertiesSet() {}

}
