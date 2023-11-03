package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepositories extends JpaRepository<Department,Integer> {
    Optional<Department> findById(int id);
}
