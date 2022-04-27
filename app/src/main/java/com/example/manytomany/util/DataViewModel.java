package com.example.manytomany.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.manytomany.entity.Director;
import com.example.manytomany.entity.School;
import com.example.manytomany.entity.Student;
import com.example.manytomany.entity.Subject;
import com.example.manytomany.entity.relations.SchoolAndDirector;
import com.example.manytomany.entity.relations.SchoolWithStudents;
import com.example.manytomany.entity.relations.SchoolWithStudentsSubset;
import com.example.manytomany.entity.relations.StudentWithSubjectSubset;
import com.example.manytomany.entity.relations.StudentWithSubjects;
import com.example.manytomany.entity.relations.subset.SubjectWithStudentSubset;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    public RoomRepository roomRepository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        roomRepository = new RoomRepository(application);
    }

    public LiveData<List<Student>> getAllStudents() {
        return roomRepository.getAllStudents();
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return roomRepository.getAllSubjects();
    }

    public LiveData<List<School>> getAllSchools() {
        return roomRepository.getAllSchools();
    }

    public LiveData<List<Director>> getAllDirectors(){return roomRepository.getAllDirectors();}

    //one to one
    public LiveData<List<SchoolAndDirector>> getSchoolAndDirector(){return roomRepository.getSchoolAndDirector();}



    // many to many

    // Option 1 to get a student with his list of subjects
    public LiveData<StudentWithSubjects> getStudentWithSubjectsEmbedd(int id) {
        return roomRepository.getStudentWithSubjectsEmbedd(id);
    }

    // Option 2 to get student with his list of subjects - using JOIN
    public LiveData<List<StudentWithSubjectSubset>> getStudentWithSubjectsSubset(int id){
        return roomRepository.getStudentWithSubjectsSubset(id);
    }


    public LiveData<List<SubjectWithStudentSubset>> getSubjectWithStudentsSubset(int subId){
        return roomRepository.getSubjectWithStudentsSubset(subId);
    }


    /*
    one to many

   get all students that learn at spasific school with 2 option: Embedded and JOIN

   */
    public LiveData<SchoolWithStudents> getSchoolWithStudentsEmbedded(int id){
        return roomRepository.getSchoolWithStudentsEmbedded(id);
    }

    public LiveData<List<SchoolWithStudentsSubset>>  getSchoolWithStudentsSubset(int id){
        return roomRepository.getSchoolWithStudentsSubset(id);
    }



    public void resetDatabase(){
        roomRepository.resetDatabase();
    }
}
