package com.info.stu.dto;

import com.info.stu.entity.Course;

public class StudentProfileResDto {
    private String name;
    private String email;
    private String city;
    private Course course;

    public StudentProfileResDto(String name, String email, String city, Course course) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

}
