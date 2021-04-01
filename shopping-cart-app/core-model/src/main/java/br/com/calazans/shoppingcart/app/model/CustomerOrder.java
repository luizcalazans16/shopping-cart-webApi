package br.com.calazans.shoppingcart.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @OneToOne
    @Column(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "total_price")
    private Double total;

}
