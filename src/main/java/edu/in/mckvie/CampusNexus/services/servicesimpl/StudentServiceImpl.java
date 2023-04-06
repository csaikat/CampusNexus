package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.entities.StudentDetails;
import edu.in.mckvie.CampusNexus.helper.Helper;
import edu.in.mckvie.CampusNexus.repositories.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl {
    @Autowired
    StudentDetailsRepository studentDetailsRepository;
    public void save(MultipartFile file){
        try{
            List<StudentDetails> students= Helper.convertExcelToListOfStudentDetails(file.getInputStream());
            this.studentDetailsRepository.saveAll(students);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<StudentDetails> getAllStudentDetails(){
        return this.studentDetailsRepository.findAll();
    }
}
