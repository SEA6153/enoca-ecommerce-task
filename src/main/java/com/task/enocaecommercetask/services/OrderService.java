package com.task.enocaecommercetask.services;

import com.task.enocaecommercetask.model.Cart;
import com.task.enocaecommercetask.model.Order;
import com.task.enocaecommercetask.model.OrderItem;
import com.task.enocaecommercetask.repostories.CartRepository;
import com.task.enocaecommercetask.repostories.OrderItemRepository;
import com.task.enocaecommercetask.repostories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public void placeOrder(Long customerId){
        Cart cart = cartRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Cart not found!"));

        if (cart.getItems().isEmpty()){
            throw new RuntimeException("Cart is empty!");
        }


        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());

        order.setItems(cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItemRepository.save(orderItem);
        }).toList());

        orderRepository.save(order);

        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

    }



}
