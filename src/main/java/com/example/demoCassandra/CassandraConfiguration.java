package com.example.demoCassandra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
public class CassandraConfiguration extends AbstractCassandraConfiguration {

	@Override
	protected String getKeyspaceName() {
		return "mykeyspace";
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints("127.0.0.1");
		cluster.setPort(9044);
		cluster.setJmxReportingEnabled(false);
		return cluster;
	}

	@Bean
	public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
		return new CassandraMappingContext();
	}
}
