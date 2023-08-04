package edu.in.mckvie.CampusNexus.services;

import edu.in.mckvie.CampusNexus.entities.StudentPayment;
import edu.in.mckvie.CampusNexus.payloads.StudentPaymentsDTO;

import java.util.List;

public interface StudentPaymentsService {
    public List<StudentPaymentsDTO> getAllStudentPaymentsRecords();
    public List<StudentPayment> getDuePayments(int semId);
    public StudentPaymentsDTO getStudentPaymentDetailsOfParticularSem(int userId,int semId);
    public List<StudentPaymentsDTO> getStudentPaymentsRecords(String userId);

}
