package br.com.calazans.shoppingcart.app.core.service.impl;

import br.com.calazans.shoppingcart.app.core.dto.ProductDto;
import br.com.calazans.shoppingcart.app.core.service.ProductService;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void registerProduct(Product entity) {
        store(null, entity);
    }

    private void store(Integer id, Product entity) {
        entity.setId(id);

        productRepository.save(entity);

    }
}
