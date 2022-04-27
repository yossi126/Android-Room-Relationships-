package com.example.manytomany.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey() private int studentId;
    private String userName;
    private int age;
    private int studentSchoolId;

    public Student() {
    }


    public Student(int id, String name, int age, int studentSchoolId) {
        this.studentId = id;
        this.userName = name;
        this.age = age;
        this.studentSchoolId = studentSchoolId;
    }

    public int getStudentSchoolId() {
        return studentSchoolId;
    }

    public void setStudentSchoolId(int studentSchoolId) {
        this.studentSchoolId = studentSchoolId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", studentSchoolId=" + studentSchoolId +
                '}';
    }
}
