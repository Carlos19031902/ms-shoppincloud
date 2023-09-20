package academy.digitallab.product.service;

import academy.digitallab.product.model.Category;
import academy.digitallab.product.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public  Product createProduct(Product product);

    public Product updateProduct(Product product);
    public Product deleteProduct(Long product);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id,Double  quantity);
}
