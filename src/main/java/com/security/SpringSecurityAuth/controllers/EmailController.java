package com.security.SpringSecurityAuth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.security.SpringSecurityAuth.service.EmailService;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmailWithAttachment(
        @RequestParam("to") String to,
        @RequestParam("subject") String subject,
        @RequestParam("text") String text,
        @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            String path = null;
            if (file != null && !file.isEmpty()) {
                path = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getOriginalFilename();
                file.transferTo(new File(path));
            }

            emailService.sendEmailWithAttachment(to, subject, text, path);

            if (path != null) {
                new File(path).delete();
            }

            return "Mail envoyé avec succès";

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "Mail non envoyé";
        }
    }
}