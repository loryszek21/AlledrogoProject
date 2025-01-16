package com.example.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import com.example.backend.dto.SubscriberDTO;
import com.example.backend.model.Subscriber;
import com.example.backend.model.User;
import com.example.backend.repository.NewsletterRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {
    private static Logger logger;
    @Autowired
    private NewsletterRepository newsletterRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

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


        try {
            welcomeEmail(subscriberDTO);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void welcomeEmail(SubscriberDTO subscriberDTO) throws MailException, MessagingException {
        String subject = "Witaj w naszym newsletterze!";
        String text = String.format("Cześć %s, dziękujemy za zapisanie się do naszego newslettera!", subscriberDTO.getUserName());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(subscriberDTO.getEmail());
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);

    }
}
