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
    /*select amount,currency,payment_id,status,s.sem from payment_list pl inner join fees_orders fo on pl.payment_details_id=fo.id
    inner join sem s on s.id=pl.semester_id
    where  fo.user_id=4;*/
    @Query("from StudentPayment WHERE paymentDetails.user.id=:id order by semester.id asc")
    public List<StudentPayment> findPaymentDetailsByUserId(@Param("id")int id);
}
