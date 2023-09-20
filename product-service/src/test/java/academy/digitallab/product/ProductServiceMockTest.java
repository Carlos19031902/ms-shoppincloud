package academy.digitallab.product;

import academy.digitallab.product.model.Category;
import academy.digitallab.product.model.Product;
import academy.digitallab.product.repository.ProductRepository;
import academy.digitallab.product.service.ProductService;
import academy.digitallab.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository repository;

    private ProductService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        service = new ProductServiceImpl(repository);
        Product computer = Product.builder()
                .id(1L)
                .category(Category.builder().id(1L).build())
                .name("computer")
                .description("")
                .stock(Double.parseDouble("5"))
                .price(Double.parseDouble("12.5")).build();

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(computer));

        Mockito.when(repository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProducty(){
        Product found = service.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = service.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
