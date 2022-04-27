package com.example.manytomany.entity.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.manytomany.entity.School;
import com.example.manytomany.entity.Student;

import java.util.List;

public class SchoolWithStudents {
    @Embedded
    private School school;
    @Relation(
            parentColumn = "schoolId",
            entityColumn = "studentSchoolId"
    )
    private List<Student> students;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
