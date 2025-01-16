package com.example.backend.service;

import com.example.backend.dto.CartDTO;
import com.example.backend.model.CartItem;
import com.example.backend.model.Product;
import com.example.backend.model.User;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDTO> getCartItems(String userName) {
        List<CartItem> cartItems = cartRepository.findByUser_username(userName);

        cartItems.sort(Comparator.comparing(item -> item.getProduct().getId()));

        return cartItems.stream().map(item -> {
            CartDTO cartDTO = new CartDTO();

//            cartDTO.setUserName(item.getUser().getUsername());
            cartDTO.setId(cartDTO.getId());
            cartDTO.setProductId(item.getProduct().getId()); // ID produktu
            cartDTO.setProductName(item.getProduct().getProductName());
            cartDTO.setQuantity(item.getQuantity());
            cartDTO.setPrice(item.getProduct().getProduct_price());
            return cartDTO;
        }).collect(Collectors.toList());


    }

    public CartItem addProductToCart(CartDTO cartDTO) {
        User user = userRepository.findByUsername(cartDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Use productId from CartDTO to find the product
        Product product = productRepository.findProductById(cartDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Sprawdź, czy ten produkt jest już w koszyku użytkownika
        CartItem existingCartItem = cartRepository.findByUser_IdAndProduct_Id(Long.valueOf(user.getId()), product.getId());

        if (existingCartItem != null) {
            // Jeśli produkt już jest w koszyku, zwiększ ilość
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartDTO.getQuantity());
            return cartRepository.save(existingCartItem);
        } else {
            // Jeśli produkt nie jest w koszyku, utwórz nowy wpis
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(cartDTO.getQuantity());
            newCartItem.setDateAdded(Instant.now());
            return cartRepository.save(newCartItem);
        }
    }

    public void removeProductById(Long productId, String userName) {
        // Znalezienie użytkownika na podstawie nazwy użytkownika
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Znalezienie elementu koszyka powiązanego z użytkownikiem i produktem
        CartItem cartItem = cartRepository.findByUser_IdAndProduct_Id(Long.valueOf(user.getId()), productId);

        if (cartItem != null) {
            // Usunięcie elementu z koszyka
            cartRepository.delete(cartItem);
        } else {
            throw new RuntimeException("Product not found in the user's cart");
        }
    }

    public void UpdateQuantityInCart(CartDTO cartDTO) {
        User user = userRepository.findByUsername(cartDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findProductById(cartDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartRepository.findByUser_IdAndProduct_Id(Long.valueOf(user.getId()), product.getId());

        if (cartItem != null) {
            cartItem.setQuantity(cartDTO.getQuantity());
            cartRepository.save(cartItem);
        } else {
            throw new RuntimeException("Product not found in the user's cart");
        }
    }
}
