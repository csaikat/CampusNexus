package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.DefaulterList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaulterListRepository extends JpaRepository<DefaulterList,Integer> {
    public DefaulterList findByUserId(int userId);
}
