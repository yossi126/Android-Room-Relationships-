package com.example.manytomany.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class School {

    @PrimaryKey()
    private int schoolId;
    private String SchoolName;

    public School() {
    }

    @Ignore()
    public School(int schoolId, String schoolName) {
        this.schoolId = schoolId;
        SchoolName = schoolName;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

}
