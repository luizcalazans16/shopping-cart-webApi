package br.com.calazans.shoppingcart.app.core.service;

import br.com.calazans.shoppingcart.app.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);
}
