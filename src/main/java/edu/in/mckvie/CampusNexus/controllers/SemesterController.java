package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.payloads.SemesterDto;
import edu.in.mckvie.CampusNexus.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permanent")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @GetMapping("/all-semester")
    public List<SemesterDto> getAllStudentsDetails(){
        return this.semesterService.getAllSemesters();
    }
}
