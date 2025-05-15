package com.practice_inclass.may_14;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice_inclass.may_14.adapter.StudentAdapter;
import com.practice_inclass.may_14.models.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter adapter;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentList = new ArrayList<>();
        studentList.add(new Student("Nguyễn Văn A", 17, "12A1"));
        studentList.add(new Student("Lê Thị B", 18, "12A2"));
        studentList.add(new Student("Phạm Văn C", 17, "12A3"));

        adapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(adapter);
    }

}