package academy.digitallab.product;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import academy.digitallab.product.model.*;
import academy.digitallab.product.repository.ProductRepository;

@DataJpaTest
public class productRepositoryMockTest {

	@Autowired
	private ProductRepository repo;
	
	@Test
	public void whenFindByCategory_thenListProduct() {
		Product product01 = Product.builder()
				.category(Category.builder().id(1L).build())
				.description("")
				.stock(Double.parseDouble("10"))
				.price(Double.parseDouble("1245.90"))
				.status("Created")
				.createAt(new Date()).build();
		
		repo.save(product01);
		List<Product> products = repo.findByCategory(product01.getCategory());
		
		Assertions.assertThat(products.size()).isEqualTo(2);
				
	}
}
