package com.example.backend.repository;

import com.example.backend.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends JpaRepository<Subscriber, Integer> {
}
