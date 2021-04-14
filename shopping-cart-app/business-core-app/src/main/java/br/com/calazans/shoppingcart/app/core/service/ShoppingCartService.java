package br.com.calazans.shoppingcart.app.core.service;

import br.com.calazans.shoppingcart.app.core.request.AddToShoppingCartDto;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;

import java.util.UUID;

public interface ShoppingCartService {

    ShoppingCart getShoppingCartById(final UUID shoppingCartId);

    ShoppingCart createShoppingCart();

    ShoppingCart addProduct(UUID shoppingCartId, final Integer productId, final Integer amount);

    void confirmOrder(ShoppingCart entity);

    void clearShoppingCart(UUID shoppingCartId);

    void updateCartItem(UUID shoppingCartId, AddToShoppingCartDto dto);
}
