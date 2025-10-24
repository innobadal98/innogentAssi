package com.info.stu.dto;

public class StudentProfileDto {
    private String name;
    private String email;
    private String city;
    private Long courseId;

    public StudentProfileDto(String name, String email,String city, Long courseId) {
        this.name = name;
        this.email = email;
        this.courseId = courseId;
        this.city = city;
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

    public Long getCourseId() {
        return courseId;
    }
}
