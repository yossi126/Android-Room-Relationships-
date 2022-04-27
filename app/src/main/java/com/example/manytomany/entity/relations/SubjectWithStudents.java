package com.example.manytomany.entity.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.manytomany.entity.Student;
import com.example.manytomany.entity.Subject;

import java.util.List;

public class SubjectWithStudents {
    @Embedded private Subject subject;

    @Relation(
            parentColumn = "subjectId",
            entityColumn = "studentId",
            associateBy = @Junction(StudentSubjectCrossRef.class)
    )
    private List<Student> students;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
