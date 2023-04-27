package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.entities.StudentPayment;
import edu.in.mckvie.CampusNexus.payloads.StudentPaymentsDTO;
import edu.in.mckvie.CampusNexus.repositories.StudentPaymentRepository;
import edu.in.mckvie.CampusNexus.services.StudentPaymentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentPaymentsServiceImpl implements StudentPaymentsService {
    @Autowired
    private StudentPaymentRepository studentPaymentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<StudentPaymentsDTO> getAllStudentPaymentsRecords() {
        List<StudentPayment> studentPaymentsList=this.studentPaymentRepository.findAll();
        return studentPaymentsList.stream()
                .map((s)->this.modelMapper.map(s, StudentPaymentsDTO.class))
                .collect(Collectors.toList());
    }

    public List<StudentPayment> getDuePayments(int semId){
        return this.studentPaymentRepository.getDuePaymentDetails(semId);
    }

    @Override
    public StudentPaymentsDTO getStudentPaymentDetailsOfParticularSem(int userId, int semId) {
        return this.modelMapper.map(this.studentPaymentRepository
                .findByUserIdAndSemId(userId,semId),StudentPaymentsDTO.class);
    }
}
