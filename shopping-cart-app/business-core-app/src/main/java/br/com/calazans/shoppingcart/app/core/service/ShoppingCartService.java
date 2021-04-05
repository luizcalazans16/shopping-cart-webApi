package br.com.calazans.shoppingcart.app.core.service;

import br.com.calazans.shoppingcart.app.model.ShoppingCart;

import java.util.UUID;

public interface ShoppingCartService {

    ShoppingCart getShoppingCartById(final UUID shoppingCartId);

    ShoppingCart createShoppingCart();

    void addProduct(ShoppingCart shoppingCart, final Integer productId, final Integer amount);

    ShoppingCart confirmOrder(ShoppingCart shoppingCart);
}
