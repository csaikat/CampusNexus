package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.services.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/api/v1/permanent")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateInvoice() throws JRException, FileNotFoundException {
        HttpHeaders headers=new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=invoice.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(this.invoiceService.generateAndServeInvoice());
    }
}
