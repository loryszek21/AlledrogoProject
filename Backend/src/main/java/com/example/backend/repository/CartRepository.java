package com.example.backend.repository;

import com.example.backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser_Id(Long userId);

    CartItem findByUser_IdAndProduct_Id(Long userId, Long productId);

    List<CartItem> findByUser_username(String userName);
}
