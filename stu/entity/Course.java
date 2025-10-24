package com.info.stu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String instructor;
    private String name;

    public Course() {
    }

    public Course(String instructor, String name) {
        this.instructor = instructor;
        this.name = name;
    }

    public Course(String name, String instructor, List<StudentProfile> students) {
        this.instructor = instructor;
        this.students = students;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<StudentProfile> getStudents() {
        return students;
    }

    public void setStudents(List<StudentProfile> students) {
        this.students = students;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List <StudentProfile> students;
}
