package com.info.stu.dto;

public class CourseDto {
    private String instructor;
    private String name;

    public CourseDto(String instructor, String name) {
        this.instructor = instructor;
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
