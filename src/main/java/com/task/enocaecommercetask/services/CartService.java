package com.task.enocaecommercetask.services;

import com.task.enocaecommercetask.dto.CartDTO;
import com.task.enocaecommercetask.dto.CartItemDTO;
import com.task.enocaecommercetask.model.Cart;
import com.task.enocaecommercetask.model.CartItem;
import com.task.enocaecommercetask.model.Customer;
import com.task.enocaecommercetask.model.Product;
import com.task.enocaecommercetask.repostories.CartItemRepository;
import com.task.enocaecommercetask.repostories.CartRepository;
import com.task.enocaecommercetask.repostories.CustomerRepository;
import com.task.enocaecommercetask.repostories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;


    public CartDTO getCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepository.save(newCart);
                });

        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setItems(cart.getItems().stream().map(cartItem -> {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProductNameInCart(cartItem.getProduct().getProductName());
            cartItemDTO.setQuantity(cartItem.getQuantity());
            cartItemDTO.setPrice(cartItem.getPrice());
            return cartItemDTO;
        }).toList());

        return cartDTO;
    }

        public void addProductToCart(Long customerId, Long productId, int quantity){

        Cart cart = cartRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Cart not found!"));

            if(product.getProductStock() < quantity){
                throw new RuntimeException("Not enough stock!");
            }

            Optional<CartItem> existingItem = cart.getItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                    .findFirst();

            if(existingItem.isPresent()){
                CartItem cartItem = existingItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setPrice(cartItem.getQuantity() * product.getProductPrice());
                cartItemRepository.save(cartItem);
            }else {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setProduct(product);
                newItem.setQuantity(quantity);
                newItem.setPrice(quantity * product.getProductPrice());
                cartItemRepository.save(newItem);
                cart.getItems().add(newItem);

            }

            product.setProductStock(product.getProductStock() - quantity);
            productRepository.save(product);

            double totalPrice = cart.getItems().stream().mapToDouble(CartItem::getPrice).sum();
            cart.setTotalPrice(totalPrice);
            cartRepository.save(cart);
        }

        public void removeProductFromCart(Long customerId, Long productId){
        Cart cart = cartRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product is not in cart!"));
            cart.getItems().remove(item);
            cartItemRepository.delete(item);

            double totalPrice = cart.getItems().stream().mapToDouble(CartItem::getPrice).sum();
            cart.setTotalPrice(totalPrice);
            cartRepository.save(cart);
        }

        public void emptyCart(Long customerId){
        Cart cart = cartRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Cart not found!"));

        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
        }



}
