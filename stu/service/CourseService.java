package com.info.stu.service;

import com.info.stu.dao.CourseDao;
import com.info.stu.dto.CourseDto;
import com.info.stu.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public String createCourse(Course course){
       courseDao.save(course);
        return "Course created";
    }

    public List <Course> getAllCourses(){
        return courseDao.findAll();
    }

    public Course getCourseById(Long id){
        Course c = courseDao.findById(id).orElse(null);
        if(c != null){
            return c;
        }else {
            System.out.println("course not found");
            return c;
        }
    }

    public Course updateCourseInstructor(Long id, Course course){
        Course c = courseDao.findById(id).orElse(null);
        if(c!=null){
            c.setInstructor(course.getInstructor());
        }
        courseDao.save(c);
        return c;
    }

    public String deleteCourse(Long id){
        courseDao.deleteById(id);
        return "course deleted";
    }


//    jpql ----

    public List<Map<String, Object>> getCoursesWithStudentCount() {
        List<Object[]> result = courseDao.getCoursesWithStudentCount();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : result) {
            Course course = (Course) row[0];
            Long studentCount = (Long) row[1];
            response.add(Map.of(
                    "course", course,
                    "studentCount", studentCount));
        }
        return response;
    }

    public List <Course> topNCourses(Integer n){
        return courseDao.topNCourses(n);
    }
}
