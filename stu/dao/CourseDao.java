package com.info.stu.dao;

import com.info.stu.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepository <Course, Long> {

    @Query("SELECT c, COUNT(s) AS studentCount FROM Course c LEFT JOIN c.students s GROUP BY c")
    List<Object[]> getCoursesWithStudentCount();

    @Query("SELECT c FROM Course c LEFT JOIN c.students s GROUP BY c ORDER BY COUNT(s) DESC")
    List<Course> topNCourses(Integer n);

}
