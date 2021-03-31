package br.com.calazans.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class ShoppingCart {

    @Id
    private UUID id;

    private LocalDateTime createdAt;

    private Map<Product, Integer> productList = new HashMap<>();
}
