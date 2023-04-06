package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.services.servicesimpl.PdfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    private PdfServiceImpl pdfService;
    @GetMapping("/create-pdf")
    public ResponseEntity<InputStreamResource> createPdf(){
        ByteArrayInputStream pdf =this.pdfService.createPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline;file=lcwd.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}
