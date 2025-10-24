package com.info.stu.service;

import com.info.stu.dao.CourseDao;
import com.info.stu.dao.StudentProfileDao;
import com.info.stu.dto.StudentProfileDto;
import com.info.stu.dto.StudentProfileResDto;
import com.info.stu.entity.Course;
import com.info.stu.entity.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentProfileService {
    @Autowired
    StudentProfileDao studentProfileDao;
    @Autowired
    CourseDao courseDao;
//   @Autowired
//    StudentProfileDto studentProfileDto;

    public List<StudentProfileResDto> getAllStudentProfile (){
       return studentProfileDao.findAll().stream().map(s->
            new StudentProfileResDto(s.getName(), s.getEmail(), s.getCity(), s.getCourse())
        ).collect(Collectors.toList());
    }



    public StudentProfileResDto getStudentById(Long id){
        StudentProfile s = studentProfileDao.findById(id).get();
    return new StudentProfileResDto(s.getName(), s.getEmail(), s.getCity(), s.getCourse());
    }



    public StudentProfileDto createStudentProfile(StudentProfileDto req){
        StudentProfile student = new StudentProfile();
        student.setEmail(req.getEmail());
        student.setName(req.getName());
        student.setCity(req.getCity());
        Course course = courseDao.findById(req.getCourseId()).orElse(null);
        student.setCourse(course);
            studentProfileDao.save(student);
        return new StudentProfileDto(student.getName(), student.getEmail(), student.getCity(), req.getCourseId());
    }

    public String deleteStduentProfile(Long id){
        studentProfileDao.deleteById(id);
        return "student deleted";
    }

//    public StudentProfileResDto updateStudentProfile(Long id, StudentProfileDto req){
//        StudentProfile studentProfile = studentProfileDao.findById(id).orElse(null);
//        assert studentProfile != null;
//        studentProfile.setName(req.getName());
//        studentProfile.setCity(req.getCity());
//        studentProfile.setEmail(req.getEmail());
//        Course course = courseDao.findById(req.getCourseId()).orElse(null);
//        studentProfile.setCourse(course);
//        StudentProfile s =  studentProfileDao.save(studentProfile);
//
//
//        return new StudentProfileResDto(s.getName(), s.getEmail(), s.getCity(), s.getCourse());
//    }

    public StudentProfileResDto updateStudentProfile(Long id, StudentProfileDto req) {


        StudentProfile studentProfile = studentProfileDao.findById(id).orElse(null);


        if (studentProfile == null) {

            return null;
        }


        studentProfile.setName(req.getName());
        studentProfile.setCity(req.getCity());
        studentProfile.setEmail(req.getEmail());


        Course course = courseDao.findById(req.getCourseId()).orElse(null);
        if (course != null) {
            studentProfile.setCourse(course);
        }

        StudentProfile updatedStudent = studentProfileDao.save(studentProfile);

        return new StudentProfileResDto(
                updatedStudent.getName(),
                updatedStudent.getEmail(),
                updatedStudent.getCity(),
                updatedStudent.getCourse()
        );
    }

//    jpql ---------


    public StudentProfile getStudentByEmail(String email){
        StudentProfile studentProfile = studentProfileDao.findByEmail(email);
        if(studentProfile != null){
            System.out.println(studentProfile);
        }else {
            System.out.println("student no found");
        }
        return studentProfile;
    }

    public List <StudentProfile> getStudentWithCourse(){
        return studentProfileDao.getAllStudent_with_courses();
    }

    public Integer getStudentCountForCourse(String courseName){
        return studentProfileDao.student_count_for_courses(courseName);
    }

    public List <StudentProfile> findByCourse(String courseName){
        return studentProfileDao.findbycourse(courseName);
    }

    public List <StudentProfile> findStudentsWithoutCourse(){
        return  studentProfileDao.findStudentsWithoutCourse();
    }

    public List <StudentProfile> findAllByCity(String city){
        return studentProfileDao.findAllByCity(city);
    }

}
