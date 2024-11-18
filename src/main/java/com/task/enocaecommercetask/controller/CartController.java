package com.task.enocaecommercetask.controller;

import com.task.enocaecommercetask.dto.CartDTO;
import com.task.enocaecommercetask.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestParam Long customerId,
                                                   @RequestParam Long productId,
                                                   @RequestParam int quantity) {
        try {
            cartService.addProductToCart(customerId, productId, quantity);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product to cart: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Long customerId,
                                                        @RequestParam Long productId) {
        try {
            cartService.removeProductFromCart(customerId, productId);
            return ResponseEntity.ok("Product removed from cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error removing product from cart: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(@RequestParam Long customerId) {
        try {
            CartDTO cartDTO = cartService.getCart(customerId);
            if (cartDTO == null || cartDTO.getItems().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(null);
            }
            return ResponseEntity.ok(cartDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/empty")
    public ResponseEntity<String> emptyCart(@RequestParam Long customerId) {
        try {
            cartService.emptyCart(customerId);
            return ResponseEntity.ok("Cart emptied successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error emptying cart: " + e.getMessage());
        }
    }
}
