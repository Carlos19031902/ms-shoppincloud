package academy.digitallap.shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import academy.digitallap.shoppingservice.clientmodel.Custumer;

@FeignClient(name = "customer", path = "/customers")
public interface CustomerClient {

	@GetMapping(value = "/{id}")
	public ResponseEntity<Custumer> getCustomer(@PathVariable("id") String id );
}
