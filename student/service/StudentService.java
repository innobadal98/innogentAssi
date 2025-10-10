package com.example.student.service;

import com.example.student.Repo.StudentRepo;
import com.example.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List <Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepo.findById(id).get();
    }

    public Student addStudent(Student student){
        studentRepo.save(student);
        return student;
    }

    public Student updateStudent(long id, Student student){
        Student exsitingStu = studentRepo.findById(id).orElse(null);

        if(exsitingStu != null){
            exsitingStu.setName(student.getName());
            exsitingStu.setEmail(student.getEmail());
            studentRepo.save(exsitingStu);
        }
        return exsitingStu;
    }

    public void deleteStudent(long id){
        if(!studentRepo.existsById(id)){
            throw new RuntimeException("student not found");
        }else {
            studentRepo.deleteById(id);
        }
    }
}
