package br.com.calazans.shoppingcart.app.persistence.repository;

import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
}