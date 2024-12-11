package com.example.backend.UnitTests;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    public UserServiceTest(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAuthenticate_ValidCredentials(){

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("$2a$10$encryptedpassword");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", user.getPassword())).thenReturn(true);

        boolean result = userService.authenticate("testuser", "password");

        assertTrue(result);
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testAuthenticate_InvalidCredentials() {
        // Given
        when(userRepository.findByUsername("testuser")).thenReturn(null);

        // When
        boolean result = userService.authenticate("testuser", "password");

        // Then
        assertFalse(result);
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testRegister() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("$2a$10$encryptedpassword");

        // When
        userService.registerUser(user);

        // Then
        verify(userRepository, times(1)).save(user);
        assertEquals("$2a$10$encryptedpassword", user.getPassword());
    }
}
