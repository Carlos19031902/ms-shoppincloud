package com.spring.client.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadBalancerConfiguration {

	
	private static final Logger log = LoggerFactory.getLogger(LoadBalancerConfiguration.class);

	@Bean
	ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
		                        
		log.info("Configurable load balancer to prefer same instance");
		return ServiceInstanceListSupplier.builder()
				.withDiscoveryClient()
				.withCaching()
				.build(context);
	}
	
}
