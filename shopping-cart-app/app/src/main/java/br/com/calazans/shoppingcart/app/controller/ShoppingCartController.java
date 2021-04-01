package br.com.calazans.shoppingcart.app.controller;

import br.com.calazans.shoppingcart.app.core.dto.ShoppingCartDto;
import br.com.calazans.shoppingcart.app.core.mapper.ShoppingCartMapper;
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

    @PostMapping("{shoppingCartId}/add")
    public ResponseEntity<ShoppingCartDto> addProducts(@PathVariable final UUID shoppingCartId, @RequestParam final UUID productId,
                                                       @RequestParam final Integer amount) {
        log.info("Adicionando amount[{}] produtos com o id: product[{}] ao carrinho: [{}]", amount, productId, shoppingCartId);

        ShoppingCart foundShoppingCart = shoppingCartService.getShoppingCartById(shoppingCartId);
        shoppingCartService.addProduct(shoppingCartId, productId, amount);

        foundShoppingCart.calculateSubTotal();
        return ResponseEntity.ok(ShoppingCartMapper.map(foundShoppingCart));
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCartDto> createShoppingCart() {
        log.info("Criando carrinho de compras.");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart();
        return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCart));
    }
}

