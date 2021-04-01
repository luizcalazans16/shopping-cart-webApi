package br.com.calazans.shoppingcart.app.persistence.repository;

import br.com.calazans.shoppingcart.app.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {

}
