package academy.digitallap.shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import academy.digitallap.shoppingservice.clientmodel.Customer;

@FeignClient(name = "customer",  fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

	
	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id );
}
