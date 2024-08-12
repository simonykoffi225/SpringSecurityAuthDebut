package com.security.SpringSecurityAuth.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.security.SpringSecurityAuth.GeneratePdf;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private GeneratePdf generatePdf;

    public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        // Ajouter un fichier en tant que pièce jointe si le chemin est fourni
        if (pathToAttachment != null && !pathToAttachment.isEmpty()) {
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(file.getFilename(), file);
        }

        // Générer le PDF et l'attacher
        ByteArrayInputStream pdf = generatePdf.generatePdf();
        helper.addAttachment("attachment.pdf", new ByteArrayResource(pdf.readAllBytes()));

        // Envoyer l'email
        emailSender.send(message);
    }
}
