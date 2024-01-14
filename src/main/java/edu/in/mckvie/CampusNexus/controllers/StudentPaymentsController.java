package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.StudentPayment;
import edu.in.mckvie.CampusNexus.payloads.StudentPaymentsDTO;
import edu.in.mckvie.CampusNexus.services.StudentPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permanent")
public class StudentPaymentsController {
    @Autowired
    private StudentPaymentsService studentPaymentsService;
    @GetMapping("/all-student-payment-record")
    public List<StudentPaymentsDTO> getAllStudentPaymentRecords(){
        return this.studentPaymentsService.getAllStudentPaymentsRecords();
    }
    @GetMapping("/student-payment-record/{userId}/{semId}")
    public StudentPaymentsDTO getStudentPaymentDetailsOfParticularSem(@PathVariable int userId,@PathVariable int semId){
        return this.studentPaymentsService.getStudentPaymentDetailsOfParticularSem(userId,semId);
    }
    @GetMapping("{semId}/due-payments")
    public List<StudentPayment> getDuePayments(@PathVariable("semId") int semId){
        return this.studentPaymentsService.getDuePayments(semId);
    }

    @GetMapping("/student-payment-record/{userId}")
    public List<StudentPaymentsDTO> getStudentPaymentRecords(@PathVariable("userId")  String userId){
        return this.studentPaymentsService.getStudentPaymentsRecords(userId);
    }
}
