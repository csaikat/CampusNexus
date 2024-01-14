package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.entities.Subject;
import edu.in.mckvie.CampusNexus.payloads.ElectiveNo;
import edu.in.mckvie.CampusNexus.repositories.SubjectRepository;
import edu.in.mckvie.CampusNexus.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    public Subject addSubject(Subject subject){
        return this.subjectRepository.save(subject);
    }
    public List<Subject> getSubjectBySemId(Integer semId,Integer deptId){
        return this.subjectRepository.getSubjectBySemId(semId,deptId);
    }
    public List<Subject> getElective(int semId,int deptId){
        return this.subjectRepository.getElective(semId,deptId);
    }
}
