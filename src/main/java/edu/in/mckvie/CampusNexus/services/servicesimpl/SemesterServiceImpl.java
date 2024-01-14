package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.entities.Semester;
import edu.in.mckvie.CampusNexus.payloads.SemesterDto;
import edu.in.mckvie.CampusNexus.repositories.SemesterRepositories;
import edu.in.mckvie.CampusNexus.services.SemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepositories semesterRepositories;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SemesterDto> getAllSemesters() {
        List<Semester> semesters=this.semesterRepositories.findAll();
        List<SemesterDto> semesterDtos=semesters.stream().map((s)->this.modelMapper.map(s,SemesterDto.class)).collect(Collectors.toList());
        return semesterDtos;
    }
}
