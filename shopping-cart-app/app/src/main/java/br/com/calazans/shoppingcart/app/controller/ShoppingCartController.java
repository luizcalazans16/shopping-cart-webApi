package br.com.calazans.shoppingcart.app.controller;

import br.com.calazans.shoppingcart.app.core.dto.ShoppingCartDto;
import br.com.calazans.shoppingcart.app.core.mapper.ShoppingCartMapper;
import br.com.calazans.shoppingcart.app.core.request.AddToShoppingCartDto;
import br.com.calazans.shoppingcart.app.core.response.ApiResponse;
import br.com.calazans.shoppingcart.app.core.service.ShoppingCartService;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/shopping-cart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCartDto> listCartItems(@PathVariable final UUID shoppingCartId) {
        log.info("Listando produtos do carrinho de id: [{}]", shoppingCartId);

        ShoppingCart storedShoppingCart = shoppingCartService.getShoppingCartById(shoppingCartId);
        return ResponseEntity.ok(ShoppingCartMapper.map(storedShoppingCart));
    }

    @PostMapping("/{shoppingCartId}/add")
    public ResponseEntity<ApiResponse> addProducts(@PathVariable final UUID shoppingCartId,
                                                   @RequestBody AddToShoppingCartDto addToShoppingCartDto) {
        log.info("Adicionando amount[{}] produtos com o id: product[{}] ao carrinho: [{}]",
                addToShoppingCartDto.getAmount(), addToShoppingCartDto.getProductId(), shoppingCartId);

        shoppingCartService.addProduct(shoppingCartId, addToShoppingCartDto.getProductId(), addToShoppingCartDto.getAmount());

        return new ResponseEntity<>(ApiResponse.builder()
                .success(Boolean.TRUE)
                .message("Produto adicionado ao carrinho").build(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCartDto> createShoppingCart() {
        log.info("Criando carrinho de compras...");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart();
        return new ResponseEntity<>(ShoppingCartMapper.map(shoppingCart), HttpStatus.CREATED);
    }

}

