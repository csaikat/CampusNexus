package edu.in.mckvie.CampusNexus.repositories;

import edu.in.mckvie.CampusNexus.entities.Subject;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Query("from Subject WHERE semester.id=:semId and department.id=:deptId")
    public List<Subject> getSubjectBySemId(@Param("semId") Integer semId,@Param("deptId") Integer deptId);

    @Query("from Subject  where semester.id=:semId and department.id=:deptId and isActive=1 and isOptional=1 group by(electiveNo) ")
    public List<Subject> getElective(@Param("semId") Integer semId,@Param("deptId") Integer deptId);
}
