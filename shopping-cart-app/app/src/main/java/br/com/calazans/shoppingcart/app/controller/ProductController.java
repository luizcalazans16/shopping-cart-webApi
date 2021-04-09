package br.com.calazans.shoppingcart.app.controller;

import br.com.calazans.shoppingcart.app.core.dto.ProductDto;
import br.com.calazans.shoppingcart.app.core.mapper.ProductMapper;
import br.com.calazans.shoppingcart.app.core.service.ProductService;
import br.com.calazans.shoppingcart.app.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        log.info("Listando produtos...");
        return ResponseEntity.ok(productService.findAllProducts()
                .stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProductById(@PathVariable final Integer productId) {
        log.info("Buscando produto pelo id: [{}]", productId);
        return ResponseEntity.ok(ProductMapper.map(productService.getProductById(productId)));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) {
        log.info("Cadastrando produto... | productDto[{}]", productDto);
        Product entity = ProductMapper.map(productDto);
        return new ResponseEntity<>(ProductMapper.map(productService.registerProduct(entity)),
                HttpStatus.CREATED);

    }
}
