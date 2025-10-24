package com.info.stu.controller;

import com.info.stu.entity.Course;
import com.info.stu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;


    @PostMapping("/create")
    public String createCourse(@RequestBody Course req){
        return courseService.createCourse(req);
    }

    @GetMapping("/all")
    public List <Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/byid/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourseInstructor(@PathVariable Long id, @RequestBody Course req){
        return courseService.updateCourseInstructor(id, req);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }

    @GetMapping("/count")
    public  List<Map<String, Object>> getCoursesWithStudentCount(){
        return courseService.getCoursesWithStudentCount();
    }

    @GetMapping("/{n}")
    public List <Course> topNCourses(@PathVariable Integer n){
        return courseService.topNCourses(n);
    }

}
