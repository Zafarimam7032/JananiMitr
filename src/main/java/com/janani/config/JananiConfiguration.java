package com.janani.config;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JananiConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	        EntityManagerFactoryBuilder builder, DataSource dataSource) {
	    return builder
	            .dataSource(dataSource)
	            .packages("com.janani.entity")
	            .persistenceUnit("yourPersistenceUnit")
	            .build();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
