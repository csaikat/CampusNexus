package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUniversityRollNumber(String universityRollNumber);
    Optional<User> findByEmail(String email);


    @Query("SELECT o.orderId from PaymentDetails o INNER JOIN User u WHERE u.id=:userId")
    String findOrderIdByUserId(@Param("userId") int userId);

}
