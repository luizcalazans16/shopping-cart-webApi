package br.com.calazans.shoppingcart.app.core.service.impl;

import br.com.calazans.shoppingcart.app.core.exceptions.ResourceNotFoundException;
import br.com.calazans.shoppingcart.app.core.service.ProductService;
import br.com.calazans.shoppingcart.app.core.service.ShoppingCartService;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import br.com.calazans.shoppingcart.app.model.constants.ShoppingCartStatusEnum;
import br.com.calazans.shoppingcart.app.persistence.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public ShoppingCart getShoppingCartById(final UUID shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new ResourceNotFoundException(ShoppingCart.class, shoppingCartId));
    }

    @Override
    public ShoppingCart addProduct(UUID shoppingCartId, Integer productId, Integer amount) {
        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
        Product storedProduct = productService.getProductById(productId);

        shoppingCart.getProducts().put(storedProduct, amount);
        shoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public void confirmOrder(ShoppingCart entity) {
        entity.setGenerationTime(LocalDateTime.now());
        entity.setStatus(ShoppingCartStatusEnum.CONFIRMED);

        shoppingCartRepository.save(entity);
    }
}
