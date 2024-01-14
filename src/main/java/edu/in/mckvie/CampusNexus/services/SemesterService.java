package edu.in.mckvie.CampusNexus.services;

import edu.in.mckvie.CampusNexus.payloads.SemesterDto;

import java.util.List;

public interface SemesterService {
    public List<SemesterDto> getAllSemesters();
}
