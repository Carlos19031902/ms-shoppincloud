package academy.digitallab.product.service;

import academy.digitallab.product.model.Category;
import academy.digitallab.product.model.Product;
import academy.digitallab.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{


    private final ProductRepository repository;

    @Override
    public List<Product> listAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("Created");
        product.setCreateAt(new Date());
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if(null == productDB){
            return null;
        }else{
            productDB.setName(product.getName());
            productDB.setDescription(product.getDescription());
            productDB.setPrice(product.getPrice());

            return repository.save(productDB);
        }
    }



    @Override
    public Product deleteProduct(Long product) {
        Product productDB = getProduct(product);
        if(null == productDB){
            return null;
        }
        productDB.setStatus("Deleted");
        return repository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);
        if(null == productDB){
            return null;
        }
        Double quantity1 = productDB.getStock()+ quantity;
        productDB.setStock(quantity1);
        return repository.save(productDB);
    }
}
