package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    StudentService studentService;

    StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping()
    public List <Student> getAllStudent(){
        return studentService.getAllStudents();
    }

    @GetMapping("/stu/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/stu")
    public Student addStudent(@RequestBody Student student){
        try{
            return studentService.addStudent(student);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException();
        }
    }



    @PutMapping("/stu/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/stu/{id}")
    public void deleteStudent(@PathVariable Long id){
        if(id != null){
            studentService.deleteStudent(id);
        }
    }
}
