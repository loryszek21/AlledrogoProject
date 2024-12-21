package com.example.backend.controller;

import com.example.backend.dto.CartDTO;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/{user_name}")
    public ResponseEntity<List<CartDTO>> getCart(@PathVariable String user_name) {
        List<CartDTO> cartItems = cartService.getCartItems(user_name);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/cart")
    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cartDTO) {
        cartService.addProductToCart(cartDTO);
        return ResponseEntity.ok(cartDTO);
    }

    @PutMapping("/cart")
    public ResponseEntity<List<CartDTO>> updateCart(@RequestBody CartDTO cartDTO) {
        cartService.UpdateQuantityInCart(cartDTO);
        List<CartDTO> updateCart = cartService.getCartItems(cartDTO.getUserName());

        return ResponseEntity.ok(updateCart);
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<List<CartDTO>> deleteCart(@PathVariable Long productId, @RequestParam String username) {
        // Logika usuwania produktu na podstawie productId
        cartService.removeProductById(productId, username);

        List<CartDTO> updateCart = cartService.getCartItems(username);
        return ResponseEntity.ok(updateCart); // 204 No Content
    }
}

