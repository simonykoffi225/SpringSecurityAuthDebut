package com.security.SpringSecurityAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.security.SpringSecurityAuth.models.Livre;
import com.security.SpringSecurityAuth.repository.LivreRepository;

@Service
public class GeneratePdf {

    @Autowired
    private LivreRepository livreRepository;

    public ByteArrayInputStream generatePdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfWriter pdfWriter = new PdfWriter(out);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument)) {

            pdfDocument.setDefaultPageSize(PageSize.A4);

            // Configurer la table pour le logo
            float threecol = 190f;
            float twocol = 285f;
            float twocol150 = twocol + 150f;
            float[] twocolumnWith = {twocol150, twocol};
            float[] fourColumnWidth = {threecol, threecol, threecol, threecol};
            float[] fullwidth = {threecol * 3};

            // Ajouter un logo ou un en-tête ici si nécessaire
            Table logoTable = new Table(twocolumnWith);
            String imagePath = "wirelogo.png"; 
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);
            image.setHeight(100); 
            image.setWidth(150);  
            logoTable.addCell(new Cell().add(image).setBorder(Border.NO_BORDER));
            logoTable.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour équilibrer
            document.add(logoTable);

            // Diviseur
            Table tableDivider = new Table(fullwidth);
            DashedBorder dgb = new DashedBorder(new DeviceRgb(128, 128, 128), 0.5f);
            document.add(tableDivider.setBorder(dgb));

            Paragraph producPara = new Paragraph("Bibliothèque").setBold().setTextAlignment(TextAlignment.CENTER);
            document.add(producPara);

            document.add(new Paragraph("\n"));

            // Ajouter l'entête de la table
            Table headerTable = new Table(fourColumnWidth);
            headerTable.setBackgroundColor(new DeviceRgb(0, 0, 0));
            headerTable.addCell(new Cell().add(new Paragraph("ID")).setBold().setFontColor(new DeviceRgb(255, 255, 255)).setBorder(Border.NO_BORDER));
            headerTable.addCell(new Cell().add(new Paragraph("Titre")).setBold().setFontColor(new DeviceRgb(255, 255, 255)).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            headerTable.addCell(new Cell().add(new Paragraph("Auteur")).setBold().setFontColor(new DeviceRgb(255, 255, 255)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
            headerTable.addCell(new Cell().add(new Paragraph("Quantité")).setBold().setFontColor(new DeviceRgb(255, 255, 255)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
            document.add(headerTable);

            // Récupérer les livres depuis la base de données
            List<Livre> livreList = livreRepository.findAll();

            // Remplir la table avec les données des livres
            Table livreTable = new Table(fourColumnWidth);
            for (Livre livre : livreList) {
                livreTable.addCell(new Cell().add(new Paragraph(String.valueOf(livre.getId()))));
                livreTable.addCell(new Cell().add(new Paragraph(livre.getTitre())).setTextAlignment(TextAlignment.CENTER));
                livreTable.addCell(new Cell().add(new Paragraph(livre.getAuteur())).setTextAlignment(TextAlignment.RIGHT));
                livreTable.addCell(new Cell().add(new Paragraph(String.valueOf(livre.getQuantite()))).setTextAlignment(TextAlignment.RIGHT));
            }
            document.add(livreTable);

            // Ajouter les paragraphes pour la signature
            Paragraph userNameParagraph = new Paragraph("Nom: ____________________")
                    .setBold()
                    .setMarginTop(80f)
                    .setMarginBottom(10f)
                    .setMarginRight(100f);

            Paragraph userDateParagraph = new Paragraph("Date: ____________________")
                    .setBold()
                    .setMarginBottom(10f);

            Paragraph userSignatureParagraph = new Paragraph("Signature:")
                    .setBold()
                    .setMarginBottom(10f);

            Paragraph directorNameParagraph = new Paragraph("DG.Kossi Amekoudji ")
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(80f)
                    .setMarginBottom(10f)
                    .setMarginRight(50f);

            Paragraph directorDateParagraph = new Paragraph("Date: ____________________")
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginBottom(10f)
                    .setMarginRight(50f);

            Paragraph directorSignatureParagraph = new Paragraph("Signature du directeur :")
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginBottom(10f)
                    .setMarginRight(50f);

            // Créer une table pour les signatures
            float[] twoColumnWidth = {1, 1}; 
            Table signatureTable = new Table(twoColumnWidth);

            // Cellule pour l'utilisateur
            Cell userCell = new Cell().add(userNameParagraph)
                    .add(userDateParagraph)
                    .add(userSignatureParagraph)
                    .setBorder(Border.NO_BORDER);

            // Cellule pour le directeur
            Cell directorCell = new Cell().add(directorNameParagraph)
                    .add(directorDateParagraph)
                    .add(directorSignatureParagraph)
                    .setBorder(Border.NO_BORDER);

            signatureTable.addCell(userCell);
            signatureTable.addCell(directorCell);

            // Ajouter la table des signatures au document
            document.add(signatureTable);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
