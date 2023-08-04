package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.DefaulterList;
import edu.in.mckvie.CampusNexus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DefaulterListRepository extends JpaRepository<DefaulterList,Integer> {
    public DefaulterList findByUserId(int userId);
    @Query("select user from DefaulterList dl WHERE SEM1=false")
    public List<User> findBySem1();
    @Query("select user from DefaulterList dl WHERE SEM2=false")
    public List<User> findBySem2();
    @Query("select user from DefaulterList dl WHERE SEM3=false")
    public List<User> findBySem3();
    @Query("select user from DefaulterList dl WHERE SEM4=false")
    public List<User> findBySem4();
    @Query("select user from DefaulterList dl WHERE SEM5=false")
    public List<User> findBySem5();
    @Query("select user from DefaulterList dl WHERE SEM6=false")
    public List<User> findBySem6();
    @Query("select user from DefaulterList dl WHERE SEM7=false")
    public List<User> findBySem7();
    @Query("select user from DefaulterList dl WHERE SEM8=false")
    public List<User> findBySem8();
}
