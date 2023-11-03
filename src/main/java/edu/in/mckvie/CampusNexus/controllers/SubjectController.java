package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.Subject;
import edu.in.mckvie.CampusNexus.services.servicesimpl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    private SubjectServiceImpl subjectService;
    @PostMapping("/addSubject")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject){
        return new ResponseEntity<Subject>(this.subjectService.addSubject(subject), HttpStatus.CREATED);
    }
    @GetMapping("/getSubject/{semId}/{deptId}")
    public ResponseEntity<List<Subject>> getSubjectBySemId(@PathVariable Integer semId,@PathVariable Integer deptId){
        return new ResponseEntity<List<Subject>>(this.subjectService.getSubjectBySemId(semId,deptId),HttpStatus.OK);
    }
    @GetMapping("/elective/{semId}/{deptId}")
    public ResponseEntity<List<Subject>> getSubjectBySemId(@PathVariable int semId, @PathVariable int deptId){
        return new ResponseEntity<List<Subject>>(this.subjectService.getElective(semId,deptId),HttpStatus.OK);
    }
}
