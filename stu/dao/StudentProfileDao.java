package com.info.stu.dao;

import com.info.stu.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentProfileDao extends JpaRepository<StudentProfile, Long> {




    @Query("SELECT s FROM StudentProfile s WHERE LOWER(s.email) = LOWER(:email)")
    StudentProfile findByEmail(String email);

    @Query("SELECT DISTINCT s FROM StudentProfile s LEFT JOIN FETCH s.course")
    List<StudentProfile> getAllStudent_with_courses();

    @Query("SELECT COUNT(s) FROM StudentProfile s JOIN s.course c WHERE c.name = :courseName")
    Integer student_count_for_courses(String courseName);

    @Query("SELECT s FROM StudentProfile s JOIN s.course c WHERE c.name = :courseName")
    List<StudentProfile> findbycourse(String courseName);

    @Query("SELECT s FROM StudentProfile s WHERE s.course IS NULL")
    List<StudentProfile> findStudentsWithoutCourse();


    @Query("SELECT s FROM StudentProfile s WHERE s.city = :city")
    List<StudentProfile> findAllByCity(String city);
}
