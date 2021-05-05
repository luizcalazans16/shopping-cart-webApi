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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/v1/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        log.info("Listando produtos...");

        List<Product> products = productService.findAllProducts();
        for (Product product : products) {
            createHateoas(product);
        }
        return ResponseEntity.ok(products
                .stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProductById(@PathVariable final Integer productId) {
        log.info("Buscando produto pelo id: [{}]", productId);
        Product storedProduct = productService.getProductById(productId);
        createHateoas(storedProduct);
        return ResponseEntity.ok(ProductMapper.map(storedProduct));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) {
        log.info("Cadastrando produto... | productDto[{}]", productDto);
        Product entity = productService.registerProduct(ProductMapper.map(productDto));
        createHateoas(entity);
        return new ResponseEntity<>(ProductMapper.map(entity),
                HttpStatus.CREATED);

    }

    private void createHateoas(Product product) {
        product.add(
                linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel()
        );
        product.add(
                linkTo(methodOn(ProductController.class).findAllProducts()).withRel("GET")
        );
    }
}
