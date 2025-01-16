package com.example.backend.controller;


import com.example.backend.dto.SubscriberDTO;
import com.example.backend.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NewsletterController {
    @Autowired
    private NewsletterService newsletterService;

    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody  SubscriberDTO subscriberDTO) {
        System.out.println(subscriberDTO.getUserName());
        System.out.println(subscriberDTO.getEmail());

        newsletterService.addSubscriber(subscriberDTO);
        return ResponseEntity.ok("Subscribed");

    }
}
