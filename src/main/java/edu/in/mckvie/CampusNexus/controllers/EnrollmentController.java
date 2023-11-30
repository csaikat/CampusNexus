package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Controller
@RequestMapping("/api/v1/permanent")
public class EnrollmentController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${project.image}")
    String basePath;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/enroll")
    public ResponseEntity<byte[]> generateEnrollmentForm(@RequestParam String uRoll) throws IOException, JRException {
        User user=this.userRepository.findByUniversityRollNumber(uRoll).orElseThrow(()->new ResourceNotFoundException("a","a","a"));

        System.out.println("cur: "+user);
        String path=resourceLoader.getResource("classpath:enrollment.jrxml").getURI().getPath();
        System.out.println("path: "+path);
        InputStream employeeReportStream = getClass().getResourceAsStream("/enrollment.jrxml");


        JRBeanCollectionDataSource jrBeanCollectionDataSource=new JRBeanCollectionDataSource(user.getSubject());

        JasperReport compileReport=JasperCompileManager.compileReport(employeeReportStream );



        HashMap<String,Object> map = new HashMap<>();
        map.put("semName",user.getSemester().getName());
        map.put("name",user.getName());
        map.put("deptName",user.getDepartment().getDeptName());
        map.put("universityRollNumber",user.getUniversityRollNumber());
        map.put("examRollNumber",user.getExamRollNumber());

        map.put("subjectDataSet",jrBeanCollectionDataSource);


        JasperPrint report= JasperFillManager.fillReport(compileReport,map,new JREmptyDataSource());


        byte[] pdfData=JasperExportManager.exportReportToPdf(report);
        HttpHeaders headers=new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename="+user.getExamRollNumber()+".pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfData);
    }


}
