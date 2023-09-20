package academy.digitallap.shoppingservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import academy.digitallap.shoppingservice.clientmodel.Custumer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{

	@Override
	public ResponseEntity<Custumer> getCustomer(String id) {
		Custumer customer = Custumer.builder()
				.firstName("none")
				.lastName("none")
				.photoUrl("none")
				.email("none")
				.build();
		return ResponseEntity.ok(customer);
	}

}
