package com.task.enocaecommercetask.repostories;

import com.task.enocaecommercetask.model.Cart;
import com.task.enocaecommercetask.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
}
