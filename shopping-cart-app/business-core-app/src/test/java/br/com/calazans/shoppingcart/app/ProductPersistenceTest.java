package br.com.calazans.shoppingcart.app;

import br.com.calazans.shoppingcart.app.core.service.impl.ProductServiceImpl;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.persistence.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductPersistenceTest {

    @Mock
    private ProductRepository productRepository;

    @Spy
    @InjectMocks
    private ProductServiceImpl productService = new ProductServiceImpl();


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void productRegisterSuccess(){
        Product savedObject = new Product(1, "Mousepad gamer RGB", 50.0);

        when(productRepository.save(any(Product.class))).thenReturn(savedObject);


        Product product = new Product("Mousepad gamer RGB", 50.0);
        Product entity = productRepository.save(product);


        Assert.assertEquals(savedObject.getId(), entity.getId());
        Assert.assertNotNull(entity);
    }

}
