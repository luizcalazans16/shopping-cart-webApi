package br.com.calazans.shoppingcart.app.core.service.impl;

import br.com.calazans.shoppingcart.app.core.service.ProductService;
import br.com.calazans.shoppingcart.app.core.service.ShoppingCartService;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import br.com.calazans.shoppingcart.app.persistence.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public ShoppingCart getShoppingCartById(final UUID shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public void addProduct(ShoppingCart shoppingCart, final Integer productId, final Integer amount) {
        Product storedProduct = productService.getProductById(productId);

        Map<Product, Integer> shoppingCartProducts = shoppingCart.getProducts();
        shoppingCartProducts.put(storedProduct, amount);

    }

    @Override
    public ShoppingCart confirmOrder(ShoppingCart shoppingCart) {
        shoppingCart.setGenerationTime(LocalDateTime.now());

        return shoppingCartRepository.save(shoppingCart);
    }
}
