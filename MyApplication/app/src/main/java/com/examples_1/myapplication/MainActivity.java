package com.examples_1.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //declare UI variables
    EditText edt_A, edt_B, edtResult;
    Button btnSum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Map Ids to interface variables
        edt_A = findViewById(R.id.edt_A);
        edt_B = findViewById(R.id.edt_B);
        edtResult = findViewById(R.id.result);
        btnSum = findViewById(R.id.sum);
        //Handle user interactions
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edt_A.getText().toString());
                int b = Integer.parseInt(edt_B.getText().toString());
                int c = a + b;
                edtResult.setText(String.valueOf(c));
            }
        });
    }
}