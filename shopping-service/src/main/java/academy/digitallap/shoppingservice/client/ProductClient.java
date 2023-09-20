package academy.digitallap.shoppingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import academy.digitallap.shoppingservice.clientmodel.Product;

@FeignClient(name = "product", path = "/products")
public interface ProductClient {
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct (@PathVariable(name = "id") Long id );

	@GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,@RequestParam(name = "quantity", required = true) Double quantity);
}
