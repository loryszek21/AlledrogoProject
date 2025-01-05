package com.example.backend.repository;

import com.example.backend.model.CartItem;
import com.example.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByUserId(Long userId);

    @Query("SELECT c.product FROM CartItem c WHERE c.product.id = :productId")

    Optional<Product> findProductById(Long productId);

}
