package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.services.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/permanent")
@Slf4j
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    //private static final Logger logger = LogManager.getLogger(FileUploadController.class);
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        log.info(file.getContentType());
        log.info(file.getOriginalFilename());
        log.info("{}",file.getSize());
        log.info("{}",file.isEmpty());
        log.info(file.getName());
        try{
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File!");
            }
            if(!file.getContentType().equals("image/jpeg")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG file content type are allowed!");
            }
            //file upload
            return ResponseEntity.ok(this.fileUploadService.uploadFile(file));
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong!");
    }
}
