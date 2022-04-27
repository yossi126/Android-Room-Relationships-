package com.example.manytomany.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

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

@Dao
public interface allDao {

    // subject DAO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubjects(Subject ... subjects);

    @Delete
    void deleteSubjects(Subject ... subjects);

    // DELETE ALL?
    @Query("DELETE FROM Subject")
    void deleteAllSubjects();

    @Query("SELECT * FROM Subject" )
    LiveData<List<Subject>> getAllSubjects();

    @Query("SELECT EXISTS (SELECT subjectId FROM Subject)")
    int checkIfTableSubjectIsEmpty();


    // Student DAO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudents(Student... students);

    @Delete
    void deleteStudents(Student... students);

    // DELETE ALL?
    @Query("DELETE FROM Student")
    void deleteAllStudents();

    @Query("SELECT * FROM Student" )
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT EXISTS (SELECT studentId FROM Student)")
    int checkIfTableStudentIsEmpty();


    // studentSubjectCrossRef DAO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudentSubjectCrossRef(StudentSubjectCrossRef ... studentSubjectCrossRefs);

    @Query("SELECT EXISTS (SELECT studentId FROM StudentSubjectCrossRef)")
    int checkIfTabletSudentSubjectCrossRefIsEmpty();

    @Query("DELETE FROM StudentSubjectCrossRef")
    void deleteAllSudentSubjectCrossRef();


    // School DAO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSchools(School ... schools);

    @Query("DELETE FROM School")
    void deleteAllSchools();

    @Query("SELECT * FROM School" )
    LiveData<List<School>> getAllSchools();

    @Query("SELECT EXISTS (SELECT schoolId FROM School)")
    int checkIfTableSchoolIsEmpty();


    // Director DAO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDirectors(Director... directors);

    @Query("DELETE FROM Director")
    void deleteAllDirectors();

    @Query("SELECT * FROM Director" )
    LiveData<List<Director>> getAllDirectors();

    @Query("SELECT EXISTS (SELECT directorId FROM Director)")
    int checkIfTableDirectorIsEmpty();



    // one to one

    @Transaction
    @Query("SELECT * FROM School")
    LiveData<List<SchoolAndDirector>> getSchoolAndDirector();




    // one to many Query's - school with students - with 2 option

    // with Embedded
    @Transaction
    @Query("SELECT * FROM School WHERE schoolId =:id")
    LiveData<SchoolWithStudents> getSchoolWithStudentsEmbedded(int id);

    // with subset and join
    @Query("SELECT stu.userName " +
            "FROM school sch " +
            "INNER JOIN student stu ON sch.schoolId = stu.studentSchoolId " +
            "WHERE sch.schoolId =:id")
    LiveData<List<SchoolWithStudentsSubset>> getSchoolWithStudentsSubset(int id);




    // Many to Many Query's for Student and Subject relation

    // Option 1 to get a student with his list of subjects
    @Transaction
    @Query("SELECT * FROM STUDENT WHERE studentId =:id")
    LiveData<StudentWithSubjects> getStudentWithSubjectsEmbedd(int id);

    // Option 2 to get student with his list of subjects - using JOIN
    @Query("SELECT sub.subjectName, sub.subjectId, s.userName " +
            "FROM Student s " +
            "INNER JOIN StudentSubjectCrossRef cr ON s.studentId = cr.studentId " +
            "INNER JOIN Subject sub ON cr.subjectId = sub.subjectId " +
            "WHERE s.studentId =:id")
    LiveData<List<StudentWithSubjectSubset>> getStudentWithSubjectsSubset(int id);


    // get all students that learn specific subject using join
    @Query("SELECT s.userName " +
            "FROM subject sub " +
            "INNER JOIN StudentSubjectCrossRef cr ON sub.subjectId = cr.subjectId " +
            "INNER JOIN student s ON cr.studentId = s.studentId " +
            "WHERE sub.subjectId=:subId")
    LiveData<List<SubjectWithStudentSubset>> getSubjectWithStudentsSubset(int subId);





}


//SELECT sub.subjectName FROM Student s INNER JOIN StudentSubjectCrossRef cr ON s.studentId = cr.studentId INNER JOIN Subject sub ON cr.subjectId = sub.subjectId WHERE s.user_id = 1