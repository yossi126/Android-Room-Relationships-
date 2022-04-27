package com.example.manytomany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.manytomany.databinding.ActivityMainBinding;
import com.example.manytomany.entity.Director;
import com.example.manytomany.entity.School;
import com.example.manytomany.entity.Student;
import com.example.manytomany.entity.Subject;
import com.example.manytomany.entity.relations.SchoolAndDirector;
import com.example.manytomany.entity.relations.SchoolWithStudents;
import com.example.manytomany.entity.relations.SchoolWithStudentsSubset;
import com.example.manytomany.entity.relations.StudentWithSubjectSubset;
import com.example.manytomany.entity.relations.StudentWithSubjects;
import com.example.manytomany.util.DataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataViewModel dataViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // view binding
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        dataViewModel.resetDatabase();

        //show to the ui
        getAllStudents();
        getAllSubjects();
        getAllSchools();
        getAllDirectors();

        binding.oneToOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OneToOneActivity.class));
            }
        });

        binding.oneToManyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OneToManyActivity.class));
            }
        });

        binding.manyToManyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ManyToManyActivity.class));
            }
        });


        // Option 1 to get a student with his list of subjects
        dataViewModel.getStudentWithSubjectsEmbedd(1).observe(this, new Observer<StudentWithSubjects>() {
            @Override
            public void onChanged(StudentWithSubjects studentWithSubjects) {
                if(studentWithSubjects != null ){
                    Log.d("getStudentWithSubjectsEmbedd", "onChanged: "+ studentWithSubjects.getStudent().getUserName());
                    for(Subject subjects : studentWithSubjects.getSubjects()){
                        Log.d("getStudentWithSubjectsEmbedd", "onChanged: "+ subjects.getSubjectName());
                    }
                }

            }
        });


        // Option 2 to get student with his list of subjects - using JOIN
        dataViewModel.getStudentWithSubjectsSubset(1).observe(this, new Observer<List<StudentWithSubjectSubset>>() {
            @Override
            public void onChanged(List<StudentWithSubjectSubset> studentWithSubjectSubsets) {
                if(!studentWithSubjectSubsets.isEmpty()){
                    Log.d("getStudentWithSubjectsSubset", "student "+studentWithSubjectSubsets.get(0).getUserName()+" has: ");
                    for (StudentWithSubjectSubset swsSubset : studentWithSubjectSubsets){
                        Log.d("getStudentWithSubjectsSubset", ""+"sub id: "+swsSubset.getSubjectId()+" sub name: "+swsSubset.getSubjectName()+"\t");
                    }
                }

            }
        });




        // one to many - school with students - with 2 option: Embedded and JOIN

        dataViewModel.getSchoolWithStudentsEmbedded(1).observe(this, new Observer<SchoolWithStudents>() {
            @Override
            public void onChanged(SchoolWithStudents schoolWithStudents) {
                Log.d("schoolWithStudents", ""+schoolWithStudents.getSchool().getSchoolName());
                //Log.d("schoolWithStudents", ""+schoolWithStudents.getStudents().isEmpty());
                List<Student> s = schoolWithStudents.getStudents();
                if(s.isEmpty())
                    Log.d("schoolWithStudents", "no student found");
                else{
                    for(Student students : s){
                        Log.d("schoolWithStudents", ""+students.getUserName());
                    }
                }
            }
        });

        dataViewModel.getSchoolWithStudentsSubset(1).observe(this, new Observer<List<SchoolWithStudentsSubset>>() {
            @Override
            public void onChanged(List<SchoolWithStudentsSubset> schoolWithStudentsSubsets) {
                if(schoolWithStudentsSubsets.isEmpty()){
                    Log.d("getSchoolWithStudentsSubset", "no student found");
                }else{
                    for (SchoolWithStudentsSubset obj :  schoolWithStudentsSubsets){
                        Log.d("getSchoolWithStudentsSubset", ""+obj.getUserName());
                    }
                }

            }
        });



        dataViewModel.getSchoolAndDirector().observe(this, new Observer<List<SchoolAndDirector>>() {
            @Override
            public void onChanged(List<SchoolAndDirector> schoolAndDirectors) {
                    for (SchoolAndDirector sad : schoolAndDirectors){
                        if(sad.getDirector() != null){
                            Log.d("getSchoolAndDirector", ""+ sad.getDirector().getDirectorName()+"- "+ sad.getSchool().getSchoolName());
                        }else{
                            Log.d("getSchoolAndDirector", ""+sad.getSchool().getSchoolName()+" has no director");
                        }

                    }



            }
        });




    }

    private void getAllDirectors() {
        dataViewModel.getAllDirectors().observe(this, new Observer<List<Director>>() {
            @Override
            public void onChanged(List<Director> directors) {
                StringBuilder sb = new StringBuilder();
                for (Director director : directors){
                    sb.append(director.getDirectorName()+", ");
                }
                binding.showDirectorsTv.setText(sb);
            }
        });
    }


    private void getAllSubjects() {
        dataViewModel.getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                StringBuilder sb = new StringBuilder();
                for (Subject subject : subjects){
                    sb.append(subject.getSubjectName()+", ");
                }
                binding.showSubjectsTv.setText(sb);
            }
        });
    }

    private void getAllStudents(){
        dataViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                StringBuilder sb = new StringBuilder();
                for (Student student : students){
                    sb.append(student.getUserName()+", ");
                }
                binding.showStudentTv.setText(sb.toString());
                //allStudents.setText(sb.toString());
            }
        });
    }

    private void getAllSchools(){
        dataViewModel.getAllSchools().observe(this, new Observer<List<School>>() {
            @Override
            public void onChanged(List<School> schools) {
                StringBuilder sb = new StringBuilder();
                for(School s : schools){
                    sb.append(s.getSchoolName()+",");
                }
                binding.showSchoolsTv.setText(sb);
            }
        });
    }
}