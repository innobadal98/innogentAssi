package oct1Assi4FilleAndExceptionHandling;

import oct1Assi4FilleAndExceptionHandling.customExceptions.InvalidAgeException;
import oct1Assi4FilleAndExceptionHandling.customExceptions.InvalidMarksException;

public class Student{
    private int id;
    private String name;
    private int marks;
    private String  gender;
    private int age;
    private int classId;

    //        dynamic data
    private boolean passed;
    private int rank;
    public static final  int MAX_AGE = 20;

    public int getRank() {
        return rank;
    }

    public boolean isPassed() {
        return passed;
    }

    public Student(int id, String name, int marks, String gender, int age, int classId) {
        if(age>MAX_AGE){
            throw new InvalidAgeException("invalid age");
        }
        if(marks<0 || marks>100){
            throw new InvalidMarksException("invalid marks");
        }

        this.id = id;
        this.name = name;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", classId=" + classId +
                ", passed=" + passed +
                ", rank=" + rank +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getClassId() {
        return classId;
    }

    public boolean getPassed(){return passed;}
    public void setPassed(boolean passed){
        this.passed = passed;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
}
