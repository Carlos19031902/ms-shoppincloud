package academy.digitallap.shoppingservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomLoadBalancerConfiguration {
	
	
	private static final Logger log = LoggerFactory.getLogger(CustomLoadBalancerConfiguration.class);

	
	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		log.info("configuratin");
		return ServiceInstanceListSupplier.builder()
					.withBlockingDiscoveryClient()
					.withSameInstancePreference()
					.build(context);
	}
}
