package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUniversityRollNumber(String universityRollNumber);
    Optional<User> findByEmail(String email);

    String findOrderIdById(int id);

}
