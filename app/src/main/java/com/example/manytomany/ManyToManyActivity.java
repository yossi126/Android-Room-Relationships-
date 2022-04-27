package com.example.manytomany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.manytomany.databinding.ActivityMainBinding;
import com.example.manytomany.databinding.ActivityManyToManyBinding;
import com.example.manytomany.entity.Subject;
import com.example.manytomany.entity.relations.StudentWithSubjects;
import com.example.manytomany.entity.relations.subset.SubjectWithStudentSubset;
import com.example.manytomany.util.DataViewModel;

import java.util.List;

public class ManyToManyActivity extends AppCompatActivity {

    private ActivityManyToManyBinding binding;
    private DataViewModel dataViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_many_to_many);
        binding = ActivityManyToManyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        binding.getYossiSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataViewModel.getStudentWithSubjectsEmbedd(1).observe(ManyToManyActivity.this, new Observer<StudentWithSubjects>() {
                    @Override
                    public void onChanged(StudentWithSubjects studentWithSubjects) {
                        StringBuilder sb = new StringBuilder();
                        for(Subject subjects : studentWithSubjects.getSubjects()){
                            sb.append(subjects.getSubjectName()+", ");
                        }
                        binding.getYossiSubTv.setText(sb);
                    }
                });
            }
        });

        binding.getHealthSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataViewModel.getSubjectWithStudentsSubset(4).observe(ManyToManyActivity.this, new Observer<List<SubjectWithStudentSubset>>() {
                    @Override
                    public void onChanged(List<SubjectWithStudentSubset> subjectWithStudentSubsets) {
                        StringBuilder sb = new StringBuilder();
                        for (SubjectWithStudentSubset sqss : subjectWithStudentSubsets){
                            sb.append(sqss.getUserName()+", ");
                        }
                        binding.getHealthSubTv.setText(sb);

                    }
                });
            }
        });





    }
}