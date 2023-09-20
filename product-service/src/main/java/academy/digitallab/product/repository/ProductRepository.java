package academy.digitallab.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.digitallab.product.model.Category;
import academy.digitallab.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category category);
}
