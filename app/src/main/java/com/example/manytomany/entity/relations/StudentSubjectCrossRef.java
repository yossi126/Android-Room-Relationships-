package com.example.manytomany.entity.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"studentId","subjectId"})
public class StudentSubjectCrossRef {

    private int studentId;
    private int subjectId;

    public StudentSubjectCrossRef(int studentId, int subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
