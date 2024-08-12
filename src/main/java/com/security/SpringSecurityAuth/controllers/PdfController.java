package com.security.SpringSecurityAuth.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.SpringSecurityAuth.GeneratePdf;

@RestController
public class PdfController {

    @Autowired
    private GeneratePdf generatePdfService;

    @GetMapping("/download/pdf")
    public ResponseEntity<InputStreamResource> downloadPdf() throws IOException {
        ByteArrayInputStream pdfStream = generatePdfService.generatePdf();
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=livres.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
}

