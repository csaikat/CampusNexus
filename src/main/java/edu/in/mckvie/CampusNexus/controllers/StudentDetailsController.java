package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.StudentDetails;
import edu.in.mckvie.CampusNexus.helper.Helper;
import edu.in.mckvie.CampusNexus.services.servicesimpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class StudentDetailsController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
        Helper h=new Helper();
        if(h.checkExcelFormat(file)){
            this.studentService.save(file);
            return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved to DB"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file");
    }
    @GetMapping("/student-details")
    public List<StudentDetails> getAllStudentsDetails(){
        return this.studentService.getAllStudentDetails();
    }
}
