package com.example.backend.service;

import com.example.backend.dto.ReviewDTO;
import com.example.backend.model.Product;
import com.example.backend.model.Review;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    public List<ReviewDTO> findReviewsByProductId(long productId) {
        List<Review> reviews = reviewRepository.findByProduct_Id(productId, Sort.by(Sort.Order.desc("reviewDate")));

        return reviews.stream().map(review -> mapToDTO(review)).collect(Collectors.toList());
    }

    // Metoda do mapowania encji Review na ReviewDTO
    private ReviewDTO mapToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setAuthor(review.getUser().getUsername());
        dto.setRating(review.getRating());
        dto.setDescription(review.getComment());

        // Formatowanie daty
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(Date.from(review.getReviewDate()));
        dto.setReviewDate(formattedDate);

        return dto;
    }

    public double getAvarageRating(long productId) {
        List<Review> reviews = reviewRepository.findByProduct_Id(productId, Sort.by(Sort.Order.desc("reviewDate")));
        if (reviews.isEmpty()) {
            return 0; // Jeśli brak recenzji, zwróć 0
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size(); // Zwróć średnią ocenę
    }

    public ReviewDTO addReview(int productId, ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setProduct(productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new IllegalArgumentException("Product not found")));
        review.setUser(userRepository.findByUsername(reviewDTO.getAuthor())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getDescription());
        review.setReviewDate(Instant.now());

        Review savedReview = reviewRepository.save(review);
        return mapToDTO(savedReview);
    }
}
