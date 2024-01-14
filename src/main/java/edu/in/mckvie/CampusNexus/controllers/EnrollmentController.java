package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.helper.FileUploadHelper;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import edu.in.mckvie.CampusNexus.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/permanent")
@Slf4j
public class EnrollmentController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileUploadHelper fileUploadHelper;
    @Autowired
    private StorageService storageService;
    @Value("${application.bucket.name}")
    private String bucketName;
    @GetMapping("/enroll")
    public ResponseEntity<String> generateEnrollmentForm(@RequestParam String uRoll) throws IOException, JRException {
        User user=this.userRepository.findByUniversityRollNumber(uRoll).orElseThrow(()->new ResourceNotFoundException("a","a","a"));

        System.out.println("cur: "+user);
//        String path=resourceLoader.getResource("classpath:enrollment.jrxml").getURI().getPath();
//        System.out.println("path: "+path);
        InputStream employeeReportStream = getClass().getResourceAsStream("/static/report/enrollment.jrxml");
        JRBeanCollectionDataSource jrBeanCollectionDataSource=new JRBeanCollectionDataSource(user.getSubject());
        JasperReport compileReport=JasperCompileManager.compileReport(employeeReportStream );

        HashMap<String,Object> map = new HashMap<>();
        map.put("semName",user.getSemester().getName());
        map.put("name",user.getName());
        map.put("deptName",user.getDepartment().getDeptName());
        map.put("universityRollNumber",user.getUniversityRollNumber());
        map.put("examRollNumber",user.getExamRollNumber());
        map.put("batch",user.getBatch());
        map.put("subjectDataSet",jrBeanCollectionDataSource);
        JasperPrint report= JasperFillManager.fillReport(compileReport,map,new JREmptyDataSource());
        //store in local server and s3
        String pdfName=user.getExamRollNumber()+"_"+user.getSemester().getId()+".pdf";
        String basePath=this.fileUploadHelper.UPLOAD_DIR;
        File file=new File(basePath);
        if(!file.exists()){
            log.info("mkdir");
            file.mkdir();
        }
        String upload_path=basePath+File.separator+pdfName;
        log.info("enroll basepath: {}"+upload_path);
        JasperExportManager.exportReportToPdfFile(report, upload_path);
        log.info("exported");

        File f=new File(upload_path);
        if(this.storageService.uploadFile(f)){
            log.info("upload");
            try{
                Files.deleteIfExists(Paths.get(upload_path));
                log.info("deleted");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        URL serveUrl=this.storageService.serveS3URL(bucketName,f.getName());
//        byte[] pdfData=JasperExportManager.exportReportToPdf(report);
//        HttpHeaders headers=new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename="+user.getExamRollNumber()+".pdf");
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfData);
        return ResponseEntity.ok(f.getName());
    }


}
