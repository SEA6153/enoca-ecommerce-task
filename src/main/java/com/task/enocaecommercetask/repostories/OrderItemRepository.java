package com.task.enocaecommercetask.repostories;

import com.task.enocaecommercetask.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
