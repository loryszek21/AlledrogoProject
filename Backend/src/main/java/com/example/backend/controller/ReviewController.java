package com.example.backend.controller;

import com.example.backend.dto.ReviewDTO;
import com.example.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReview(@PathVariable int productId) {
        List<ReviewDTO> reviews = reviewService.findReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("review/avarage/{productId}")
    public ResponseEntity<Double> getAvgRating(@PathVariable int productId) {
        double avarageRating = reviewService.getAvarageRating(productId);
        return ResponseEntity.ok(avarageRating);
    }

    @PostMapping("review/{productId}")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable int productId, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO saveReview = reviewService.addReview(productId, reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveReview);
    }
}
