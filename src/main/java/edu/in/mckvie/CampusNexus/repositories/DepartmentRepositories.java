package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepositories extends JpaRepository<Department,Integer> {
}
