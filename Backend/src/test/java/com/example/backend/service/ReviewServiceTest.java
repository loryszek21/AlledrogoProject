package com.example.backend.service;

import com.example.backend.dto.ReviewDTO;
import com.example.backend.model.Product;
import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ensure Mockito annotations are processed
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findReviewsByProductId() {
        // Arrange
        long productId = 1L;
        Review review1 = new Review();
        review1.setId(1);
        review1.setRating(4);
        review1.setComment("Good product");
        review1.setReviewDate(Instant.now());

        Review review2 = new Review();
        review2.setId(2);
        review2.setRating(5);
        review2.setComment("Excellent");
        review2.setReviewDate(Instant.now());

        when(reviewRepository.findByProduct_Id(eq(productId), any(Sort.class)))
                .thenReturn(Arrays.asList(review1, review2));

        // Act
        List<ReviewDTO> result = reviewService.findReviewsByProductId(productId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Good product", result.get(0).getDescription());
        verify(reviewRepository, times(1)).findByProduct_Id(eq(productId), any(Sort.class));
    }

    @Test
    void getAverageRating() {
        // Arrange
        long productId = 1L;
        Review review1 = new Review();
        review1.setRating(4);
        Review review2 = new Review();
        review2.setRating(5);

        when(reviewRepository.findByProduct_Id(eq(productId), any(Sort.class)))
                .thenReturn(Arrays.asList(review1, review2));

        // Act
        double averageRating = reviewService.getAvarageRating(productId);

        // Assert
        assertEquals(4.5, averageRating, 0.001);
        verify(reviewRepository, times(1)).findByProduct_Id(eq(productId), any(Sort.class));
    }

    @Test
    void addReview() {
        // Arrange
        int productId = 1;
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setAuthor("testUser");
        reviewDTO.setRating(5);
        reviewDTO.setDescription("Great product");

        Product product = new Product();
        product.setId(1L);

        User user = new User();
        user.setUsername("testUser");

        Review savedReview = new Review();
        savedReview.setId(1);
        savedReview.setRating(5);
        savedReview.setComment("Great product");
        savedReview.setReviewDate(Instant.now());
        savedReview.setUser(user);
        savedReview.setProduct(product);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);

        // Act
        ReviewDTO result = reviewService.addReview(productId, reviewDTO);

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getAuthor());
        assertEquals(5, result.getRating());
        assertEquals("Great product", result.getDescription());
        verify(productRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findByUsername("testUser");
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
}
