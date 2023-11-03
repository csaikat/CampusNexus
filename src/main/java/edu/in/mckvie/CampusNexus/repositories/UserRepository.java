package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUniversityRollNumber(@Param("universityRollNumber") String universityRollNumber);
    Optional<User> findByEmail(String email);
    @Query("select id from User where universityRollNumber=:universityRollNumber")
    int findIdByUniversityRollNumber(String universityRollNumber);
    //find users information who payed fees and not enrolled
    @Query("from DefaulterList dl WHERE dl.SEM1=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem1(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM2=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem2(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM3=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem3(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM4=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem4(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM5=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem5(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM6=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem6(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM7=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem7(@Param("id") int id);
    @Query("from DefaulterList dl WHERE dl.SEM8=1 and dl.user.isEnrolled=0 and dl.user.id=:id")
    Optional<User> getUserForEnrollForSem8(@Param("id") int id);
}
