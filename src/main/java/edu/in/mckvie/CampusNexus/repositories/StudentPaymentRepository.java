package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentPaymentRepository extends JpaRepository<StudentPayment,Integer> {

    @Query("from StudentPayment where dueFees=0 and semester.id=:semId")
    public List<StudentPayment> getDuePaymentDetails(@Param("semId") int semId);
    @Query("from StudentPayment where paymentDetails.user.id=:userId and semester.id=:semId")
    public StudentPayment findByUserIdAndSemId(@Param("userId")int userId, @Param("semId")int semId);
}
