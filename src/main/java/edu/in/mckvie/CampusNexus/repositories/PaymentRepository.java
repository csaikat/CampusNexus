package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
    public PaymentDetails findByOrderId(String orderId);
    @Query("SELECT o.status from PaymentDetails o WHERE o.orderId=:orderId")
    public String findStatusByOrderId(@Param("orderId") String orderId);
    @Query("SELECT o.orderId from PaymentDetails o WHERE o.user.id=:userId and o.semId=:semId")
    public String findOrderIdByUserIdAndSemId(@Param("userId") int userId,@Param("semId") int semId);
}
