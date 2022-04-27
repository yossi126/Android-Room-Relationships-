package com.example.manytomany.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subject {

    @PrimaryKey() private int subjectId;

    private String subjectName;

    public Subject() {
    }

    public Subject(int subjectId, String name) {
        this.subjectId = subjectId;
        this.subjectName = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + subjectName + '\'' +
                '}';
    }
}
