package academy.digitallap.shoppingservice.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import academy.digitallap.shoppingservice.clientmodel.Customer;

@FeignClient(name = "customer")
@LoadBalancerClient(name = "customer", configuration = CustomLoadBalancerConfiguration.class)
public interface CustomerClient {

	
	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id );
}
