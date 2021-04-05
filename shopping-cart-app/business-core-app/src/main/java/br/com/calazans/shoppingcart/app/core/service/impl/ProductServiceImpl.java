package br.com.calazans.shoppingcart.app.core.service.impl;

import br.com.calazans.shoppingcart.app.core.service.ProductService;
import br.com.calazans.shoppingcart.app.model.Product;
import br.com.calazans.shoppingcart.app.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException());
    }
}