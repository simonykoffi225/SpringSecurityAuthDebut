package com.security.SpringSecurityAuth.scheduler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.security.SpringSecurityAuth.service.EmailService;

import jakarta.mail.MessagingException;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 120000)
    public void sendScheduledEmail() {
        try {
            String to = "moonsieurdjodjo@gmail.com";
            String subject = "Scheduled Email Subject";
            String text = "Le mail sera envoyé chaque 2 minutes.";

            String pathToAttachment = null;

            emailService.sendEmailWithAttachment(to, subject, text, pathToAttachment);

            System.out.println("Mail envoyé avec succès.");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            System.err.println("Echec d'envoi d'email.");
        }
    }
}