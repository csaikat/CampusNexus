package edu.in.mckvie.CampusNexus.controllers;


import edu.in.mckvie.CampusNexus.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/permanent/file")
public class StorageController {

    @Autowired
    private StorageService service;
    @Value("${application.bucket.name}")
    private String bucketName;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadMultipartFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
//    @GetMapping("/send-email")
//    public String sendEmail() {
//        // Replace these values with your S3 bucket and file details
//
//        String key = "1701691082885_031220231330admitcard.pdf";
//        String recipient = "monish.paul2000@gmail.com";
//        String subject = "Subject of the Email";
//        String body = "Body of the Email";
//
//        // Send the email with the attached file
//        this.service.sendFileByEmail(bucketName, key, recipient, subject, body);
//
//        return "Email sent successfully";
//    }
}
