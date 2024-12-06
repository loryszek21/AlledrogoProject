package com.example.backend.service;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

public boolean authenticate(String username, String password) {

    User user = userRepository.findByUsername(username);
    if (user == null) {
        return false;
    }
    return user.getPassword().equals(password);

}
}
