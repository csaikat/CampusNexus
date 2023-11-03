package edu.in.mckvie.CampusNexus.services;

import edu.in.mckvie.CampusNexus.entities.Subject;

import java.util.List;

public interface SubjectService {
    public Subject addSubject(Subject subject);
    public List<Subject> getSubjectBySemId(Integer semId,Integer deptId);
    public List<Subject> getElective(int semId,int deptId);
}
