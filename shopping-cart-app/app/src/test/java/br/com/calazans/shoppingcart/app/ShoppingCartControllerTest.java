package br.com.calazans.shoppingcart.app;

import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingCartControllerTest {

    @Test
    public void mustCreateShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(UUID.randomUUID());
        shoppingCart.setProducts(populateProducts());
        shoppingCart.setGenerationTime(LocalDateTime.now());

        Double total = calculateTotal(shoppingCart.getProducts());

        Assert.assertEquals(shoppingCart.getProducts().entrySet().size(), 2);
        Assertions.assertTrue(total > 50);

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
