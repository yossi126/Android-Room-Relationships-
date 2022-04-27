package com.example.manytomany.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.manytomany.dao.allDao;
import com.example.manytomany.entity.Director;
import com.example.manytomany.entity.School;
import com.example.manytomany.entity.Student;
import com.example.manytomany.entity.Subject;
import com.example.manytomany.entity.relations.SchoolAndDirector;
import com.example.manytomany.entity.relations.SchoolWithStudents;
import com.example.manytomany.entity.relations.SchoolWithStudentsSubset;
import com.example.manytomany.entity.relations.StudentSubjectCrossRef;
import com.example.manytomany.entity.relations.StudentWithSubjectSubset;
import com.example.manytomany.entity.relations.StudentWithSubjects;
import com.example.manytomany.entity.relations.subset.SubjectWithStudentSubset;

import java.util.List;

public class RoomRepository {


    private allDao allDao;
    private LiveData<List<Student>> allStudents;
    private LiveData<List<Subject>> allSubjects;


    public RoomRepository(Application application) {
            AppDatabase db = AppDatabase.getDatabase(application);
            allDao = db.allDao();
            allStudents = allDao.getAllStudents();
            allSubjects = allDao.getAllSubjects();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return allSubjects;
    }

    // different approach to gel all the data (without initialize the constructor)
    public LiveData<List<School>> getAllSchools() {
        return allDao.getAllSchools();
    }

    public LiveData<List<Director>> getAllDirectors(){return allDao.getAllDirectors();}





    // one to one

    public LiveData<List<SchoolAndDirector>> getSchoolAndDirector(){return allDao.getSchoolAndDirector();}


    /*

     many to many


     */

    // Option 1 to get a student with his list of subjects
    public LiveData<StudentWithSubjects> getStudentWithSubjectsEmbedd(int id) {
        return allDao.getStudentWithSubjectsEmbedd(id);
    }


    // Option 2 to get student with his list of subjects - using JOIN
    public LiveData<List<StudentWithSubjectSubset>> getStudentWithSubjectsSubset(int id){
        return allDao.getStudentWithSubjectsSubset(id);
    }


    /*

    one to many

     get all students that learn at spasific school with 2 option: Embedded and JOIN

     */

    public LiveData<SchoolWithStudents> getSchoolWithStudentsEmbedded(int id){
        return allDao.getSchoolWithStudentsEmbedded(id);
    }

    public LiveData<List<SchoolWithStudentsSubset>>  getSchoolWithStudentsSubset(int id){
        return allDao.getSchoolWithStudentsSubset(id);
    }

    public LiveData<List<SubjectWithStudentSubset>> getSubjectWithStudentsSubset(int subId){
        return allDao.getSubjectWithStudentsSubset(subId);
    }




    public void resetDatabase(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                resetStudents();
                resetSubjects();
                resetSchools();
                resetStudentSubjectCrossReff();
                resetDirectors();
            }
        });
    }

    private void resetDirectors() {
        final boolean isTableEmpty = (allDao.checkIfTableDirectorIsEmpty() == 0);
        if (!isTableEmpty){
            allDao.deleteAllDirectors();
        }
        allDao.insertDirectors(
                new Director(1,"Moshe Levi",3),
                new Director(2,"Avrahm Dekel",1),
                new Director(3,"Satya Nadella",4),
                new Director(4,"Ellon Mask",2)
        );
    }

    private void resetSchools() {
        final boolean isTableEmpty = (allDao.checkIfTableSchoolIsEmpty() == 0);
        if (!isTableEmpty){
            allDao.deleteAllSchools();
        }

        allDao.insertSchools(
                new School(1,"Ben-Gurion"),
                new School(2,"Tel-Aviv"),
                new School(3,"Ariel"),
                new School(4,"Bar-ilan"),
                new School(5,"Ort")
        );
    }

    private void resetStudentSubjectCrossReff() {
        final boolean isTableEmpty = (allDao.checkIfTabletSudentSubjectCrossRefIsEmpty() == 0);

        if(!isTableEmpty){
            allDao.deleteAllSudentSubjectCrossRef();
        }


        allDao.insertStudentSubjectCrossRef(
                new StudentSubjectCrossRef(1,1),
                new StudentSubjectCrossRef(1,2),
                new StudentSubjectCrossRef(1,3),
                new StudentSubjectCrossRef(2,1),
                new StudentSubjectCrossRef(2,4),
                new StudentSubjectCrossRef(3,4),
                new StudentSubjectCrossRef(3,5)

        );
    }

    private void resetStudents() {
        final boolean isTableEmpty = (allDao.checkIfTableStudentIsEmpty() == 0);

        if(!isTableEmpty){
            allDao.deleteAllStudents();
        }

        allDao.insertStudents(
                new Student(1,"yossi",32,1),
                new Student(2,"Loten",31,2),
                new Student(3,"Nurit",45,1),
                new Student(4,"Marko", 30,3)
        );
    }

    private void resetSubjects() {
        final boolean isTableEmpty = (allDao.checkIfTableSubjectIsEmpty() == 0);

        if(!isTableEmpty){
            allDao.deleteAllSubjects();
        }

        allDao.insertSubjects(
                new Subject(1,"Language Arts"),
                new Subject(2,"Mathematics"),
                new Subject(3,"Science"),
                new Subject(4,"Health"),
                new Subject(5,"Music")

        );
    }
}
