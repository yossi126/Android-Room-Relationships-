package com.example.manytomany.entity.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.manytomany.entity.Director;
import com.example.manytomany.entity.School;

public class SchoolAndDirector {
    @Embedded private School school;
    @Relation(
            parentColumn = "schoolId",
            entityColumn = "directorSchoolId"
    )
    private Director director;


    public SchoolAndDirector() {
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
