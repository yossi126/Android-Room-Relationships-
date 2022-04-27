package com.example.manytomany.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Director {
    @PrimaryKey()
    private int directorId;
    private String directorName;
    private int directorSchoolId;

    public Director() {
    }

    public Director(int directorId, String directorName, int directorSchoolId) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.directorSchoolId = directorSchoolId;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getDirectorSchoolId() {
        return directorSchoolId;
    }

    public void setDirectorSchoolId(int directorSchoolId) {
        this.directorSchoolId = directorSchoolId;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", directorName='" + directorName + '\'' +
                ", directorSchoolId=" + directorSchoolId +
                '}';
    }
}

