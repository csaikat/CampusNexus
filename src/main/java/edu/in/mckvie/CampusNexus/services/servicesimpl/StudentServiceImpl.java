package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.entities.Department;
import edu.in.mckvie.CampusNexus.entities.StudentDetails;
import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.helper.Helper;
import edu.in.mckvie.CampusNexus.repositories.DepartmentRepositories;
import edu.in.mckvie.CampusNexus.repositories.StudentDetailsRepository;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl {
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;
    @Autowired
    private DepartmentRepositories departmentRepositories;
    @Autowired
    private UserRepository userRepository;
    public void save(MultipartFile file){
        try{
            Helper h=new Helper();
            List<User> students= h.convertExcelToListOfStudentDetails(file.getInputStream());
            this.userRepository.saveAll(students);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Department getDepartment(int id){
        System.out.println(id);
        Department d = this.departmentRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found", "id", String.valueOf(id)));
        System.out.println("dd"+d.getDeptName());
        return d;
    }
    public List<StudentDetails> getAllStudentDetails(){
        return this.studentDetailsRepository.findAll();
    }
}
