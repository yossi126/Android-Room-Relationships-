package com.example.manytomany.entity.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.manytomany.entity.Student;
import com.example.manytomany.entity.Subject;

import java.util.List;

public class StudentWithSubjects {
    @Embedded
    private Student student;

    @Relation(
            parentColumn = "studentId",
            entityColumn = "subjectId",
            associateBy = @Junction(StudentSubjectCrossRef.class)
    )
    private List<Subject> subjects;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
