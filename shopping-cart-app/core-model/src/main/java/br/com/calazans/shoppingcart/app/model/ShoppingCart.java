package br.com.calazans.shoppingcart.app.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "shopping_cart_product_mapping",
            joinColumns = {@JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "description")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

    private Double subTotal;

    private LocalDateTime generationTime;


    public void calculateSubTotal() {
        subTotal = .0;
        for(Map.Entry<Product,Integer> entry : products.entrySet()){
            subTotal += entry.getKey().getUnitPrice() * entry.getValue();
        }
    }

}
