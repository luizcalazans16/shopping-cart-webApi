package br.com.calazans.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product {

    @Id
    private UUID id;

    private String name;

    private String description;

    private BigDecimal unitPrice;
}
