package academy.digitallap.shoppingservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import academy.digitallap.shoppingservice.clientmodel.Customer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{

	@Override
	public ResponseEntity<Customer> getCustomer(Long id) {
		Customer customer = Customer.builder()
				.firstName("none")
				.lastName("none")
				.photoUrl("none")
				.email("none")
				.build();
		return ResponseEntity.ok(customer);
	}

}
