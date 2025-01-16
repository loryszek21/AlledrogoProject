package com.example.backend.service;

import org.slf4j.Logger;
import com.example.backend.dto.SubscriberDTO;
import com.example.backend.model.Subscriber;
import com.example.backend.model.User;
import com.example.backend.repository.NewsletterRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {
    private static Logger logger;
    @Autowired
    private NewsletterRepository newsletterRepository;
    @Autowired
    private UserRepository userRepository;


    public void addSubscriber(SubscriberDTO subscriberDTO) {
        System.out.println("SubscribersDTO: " + subscriberDTO);
        System.out.println("UserName: " + subscriberDTO.getUserName());
        System.out.println("email: " + subscriberDTO.getEmail());
        User user = userRepository.findByUsername(subscriberDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subscriber subscriber = new Subscriber();
        subscriber.setUser(user);
        subscriber.setEmail(subscriberDTO.getEmail());
System.out.println(subscriber.getUser().getUsername());
        newsletterRepository.save(subscriber);
    }
}