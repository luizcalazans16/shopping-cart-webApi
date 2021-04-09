package br.com.calazans.shoppingcart.app.core.service;

import br.com.calazans.shoppingcart.app.core.dto.ProductDto;
import br.com.calazans.shoppingcart.app.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product getProductById(Integer productId);

    Product registerProduct(Product entity);
}
