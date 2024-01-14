package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepositories extends JpaRepository<Semester,Integer> {
}
