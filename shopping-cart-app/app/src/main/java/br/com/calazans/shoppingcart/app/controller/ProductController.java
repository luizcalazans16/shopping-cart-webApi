package br.com.calazans.shoppingcart.app.controller;

import br.com.calazans.shoppingcart.app.core.dto.ProductDto;
import br.com.calazans.shoppingcart.app.core.mapper.ProductMapper;
import br.com.calazans.shoppingcart.app.core.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/product")
@Slf4j
public class ProductController {

    @Autowired  
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> findAllProducts() {
      log.info("Listando produtos...");
      return productService.findAllProducts().stream()
              .map(ProductMapper::map)
              .collect(Collectors.toList());
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto getProductById(@PathVariable final Integer productId) {
        log.info("Buscando produto pelo id: [{}]", productId);
        return ProductMapper.map(productService.getProductById(productId));
    }
}
