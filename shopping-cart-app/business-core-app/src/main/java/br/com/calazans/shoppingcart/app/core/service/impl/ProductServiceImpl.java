package br.com.calazans.shoppingcart.app.core.service.impl;

import br.com.calazans.shoppingcart.app.core.exceptions.ResourceNotFoundException;
import br.com.calazans.shoppingcart.app.core.service.ProductService;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(Product.class, productId));
    }

    @Override
    public Product registerProduct(Product entity) {
        return store(null, entity);
    }

    private Product store(Integer id, Product entity) {
        entity.setId(id);

        return productRepository.save(entity);
    }
}
