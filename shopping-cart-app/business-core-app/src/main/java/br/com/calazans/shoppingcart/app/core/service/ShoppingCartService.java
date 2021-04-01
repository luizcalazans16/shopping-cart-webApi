package br.com.calazans.shoppingcart.app.core.service;

import br.com.calazans.shoppingcart.app.model.ShoppingCart;

import java.util.UUID;

public interface ShoppingCartService {

    ShoppingCart getShoppingCartById(final UUID shoppingCartId);

    ShoppingCart createShoppingCart();

    void addProduct(final UUID shoppingCartId, final UUID productId, final Integer amount);
}
