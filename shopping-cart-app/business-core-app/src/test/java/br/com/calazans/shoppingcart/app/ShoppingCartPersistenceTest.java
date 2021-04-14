package br.com.calazans.shoppingcart.app;

import br.com.calazans.shoppingcart.app.core.service.impl.ShoppingCartServiceImpl;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import br.com.calazans.shoppingcart.app.persistence.repository.ShoppingCartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartPersistenceTest {

    @Spy
    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl();

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shoppingcartRegisterSuccess() {
        ShoppingCart savedShoppingCart = new ShoppingCart(UUID.randomUUID(), populateProducts(), 140.0, LocalDateTime.now());

        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(savedShoppingCart);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(UUID.randomUUID());
        shoppingCart.setProducts(populateProducts());
        shoppingCart.setGenerationTime(LocalDateTime.now());
        Double total = calculateTotal(shoppingCart.getProducts());

        Assert.assertTrue(total > 50);

        ShoppingCart entity = shoppingCartRepository.save(shoppingCart);

        Assert.assertEquals(savedShoppingCart.getTotal(), entity.getTotal());
        Assert.assertNotNull(entity.getProducts());
    }


    private Map<Product, Integer> populateProducts() {
        Map<Product, Integer> productList = new HashMap<>();

        productList.put(new Product(1, "Mouse", 20.0), 2);
        productList.put(new Product(2, "Teclado", 100.0), 1);

        return productList;
    }

    private Double calculateTotal(Map<Product, Integer> productList) {
        Double total = .0;

        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            total += entry.getKey().getUnitPrice() * entry.getValue();
        }
        return total;
    }

}
