package br.com.calazans.shoppingcart.app.core.service;

import br.com.calazans.shoppingcart.app.model.Product;

import java.util.UUID;

public interface ProductService {

    Product getProductById(UUID productId);
}
