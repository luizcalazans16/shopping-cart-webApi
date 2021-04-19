package br.com.calazans.shoppingcart.app.core.service.impl;

import br.com.calazans.shoppingcart.app.config.exceptions.ResourceNotFoundException;
import br.com.calazans.shoppingcart.app.core.request.AddToShoppingCartDto;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public ShoppingCart getShoppingCartById(final UUID shoppingCartId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCartOptional.isPresent()) {
            ShoppingCart storedShoppingCart = shoppingCartOptional.get();
            storedShoppingCart.calculateTotal();
            return storedShoppingCart;
        }
        throw new ResourceNotFoundException(ShoppingCart.class, shoppingCartId);
    }

    @Override
    public ShoppingCart addProduct(UUID shoppingCartId, Integer productId, Integer amount) {
        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
        Product storedProduct = productService.getProductById(productId);

        Map<Product, Integer> shoppingCartProducts = shoppingCart.getProducts();

        if (shoppingCartProducts.isEmpty()) {
            shoppingCartProducts.put(storedProduct, amount);

        } else {
            Integer storedProductAmount = shoppingCartProducts.get(storedProduct);
            if (storedProductAmount != null) {
                storedProductAmount += amount;
                shoppingCartProducts.put(storedProduct, storedProductAmount);
            } else {
                shoppingCartProducts.put(storedProduct, amount);
            }

        }
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setStatus(ShoppingCartStatusEnum.CREATED);
        shoppingCart = shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public void confirmOrder(ShoppingCart entity) {
        entity.setGenerationTime(LocalDateTime.now());
        entity.setStatus(ShoppingCartStatusEnum.CONFIRMED);

        shoppingCartRepository.save(entity);
    }

    @Override
    public void updateCartItem(UUID shoppingCartId, AddToShoppingCartDto dto) {
        ShoppingCart storedShoppingCart = getShoppingCartById(shoppingCartId);
        Product storedProduct = productService.getProductById(dto.getProductId());
        Map<Product, Integer> shoppingCartProducts = storedShoppingCart.getProducts();

        shoppingCartProducts.put(storedProduct, dto.getAmount());
        shoppingCartRepository.save(storedShoppingCart);
    }

    @Override
    public void clearShoppingCart(UUID shoppingCartId) {
        ShoppingCart storedShoppingCart = getShoppingCartById(shoppingCartId);
        storedShoppingCart.getProducts().clear();

        shoppingCartRepository.save(storedShoppingCart);
    }
}
