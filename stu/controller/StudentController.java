package com.info.stu.controller;

import com.info.stu.dto.StudentProfileDto;
import com.info.stu.dto.StudentProfileResDto;
import com.info.stu.entity.StudentProfile;
import com.info.stu.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<List<StudentProfileResDto>> getAllStudent(){
        return ResponseEntity.ok(studentProfileService.getAllStudentProfile());
    }

    @GetMapping("/stu/{id}")
    public ResponseEntity <StudentProfileResDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentProfileService.getStudentById(id));
    }

    @PostMapping("/stu")
    public ResponseEntity <StudentProfileDto> createStudent(@RequestBody StudentProfileDto studentProfileDto){
        return ResponseEntity.ok(studentProfileService.createStudentProfile(studentProfileDto));
    }

    @PutMapping("/stu/{id}")
    public ResponseEntity <StudentProfileResDto> updateStudentProfile(@PathVariable Long id, @RequestBody StudentProfileDto req){
        return ResponseEntity.ok(studentProfileService.updateStudentProfile(id, req));
    }

    @DeleteMapping("/stu/{id}")
    public ResponseEntity <String> deleteStudentProfile(@PathVariable Long id){
        return ResponseEntity.ok(studentProfileService.deleteStduentProfile(id));
    }







//    jpql -------------
@GetMapping("/students/email/{email}")
public ResponseEntity<StudentProfile> getStudentByEmail(@PathVariable String email) {
    StudentProfile student = studentProfileService.getStudentByEmail(email);
    if (student != null) {
        return ResponseEntity.ok(student);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/allStudentsWithCourses")
    public List<StudentProfile> getAllStudentsWithCourses() {
        return studentProfileService.getStudentWithCourse();
    }

    @GetMapping("/studentCountForCourse/{courseName}")
    public Integer getStudentCountForCourse(@PathVariable String courseName) {
        return studentProfileService.getStudentCountForCourse(courseName);
    }

    @GetMapping("/studentsByCourse/{courseName}")
    public List<StudentProfile> getStudentsByCourse(@PathVariable String courseName) {
        return studentProfileService.findByCourse(courseName);
    }

    @GetMapping("/studentsWithNoCourses")
    public List<StudentProfile> getStudentsWithNoCourses() {
        return studentProfileService.findStudentsWithoutCourse();
    }

    @GetMapping("/student/{city}")
    public List<StudentProfile> getStudentsByCity(@PathVariable String city) {
        return studentProfileService.findAllByCity(city);
    }

}
