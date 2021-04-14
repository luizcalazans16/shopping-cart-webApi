package br.com.calazans.shoppingcart.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private Double unitPrice;


    public Product(String description, Double unitPrice) {
        this.description = description;
        this.unitPrice = unitPrice;
    }
}


