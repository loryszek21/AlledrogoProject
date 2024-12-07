package com.example.backend.service;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.backend.util.Crypt;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public boolean authenticate(String username, String password) {

    User user = userRepository.findByUsername(username);
    if (user == null) {
        return false;
    }
    return passwordEncoder.matches(password, user.getPassword());

}

public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
}
}
