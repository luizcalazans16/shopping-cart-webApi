package br.com.calazans.shoppingcart.app.model;

import javax.persistence.*;

import br.com.calazans.shoppingcart.app.model.constants.ShoppingCartStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "shopping_cart_product_mapping",
            joinColumns = {@JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "description")
    @Column(name = "quantity")
    private Map<Product, Integer> products;

    private Double total;

    private LocalDateTime generationTime;

    private ShoppingCartStatusEnum status;

    public void calculateTotal() {
        total = .0;
        for(Map.Entry<Product,Integer> entry : products.entrySet()){
            total += entry.getKey().getUnitPrice() * entry.getValue();
        }
    }

}
