package com.examples_3.project_cal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSub, btnMul, btnDiv;
    EditText edtA, edtB, edtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtResult.setText("");
                String edtAValue = edtA.getText().toString().trim();
                String edtBValue = edtB.getText().toString().trim();
                if(edtAValue.isEmpty() || edtBValue.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int a = Integer.parseInt(edtAValue);
                    int b = Integer.parseInt(edtBValue);
                    int result = a + b;
                    edtResult.setText(String.valueOf(result));
                    return;
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtResult.setText("");
                String edtAValue = edtA.getText().toString().trim();
                String edtBValue = edtB.getText().toString().trim();
                if(edtAValue.isEmpty() || edtBValue.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int a = Integer.parseInt(edtAValue);
                    int b = Integer.parseInt(edtBValue);
                    int result = a - b;
                    edtResult.setText(String.valueOf(result));
                    return;
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtResult.setText("");
                String edtAValue = edtA.getText().toString().trim();
                String edtBValue = edtB.getText().toString().trim();
                if(edtAValue.isEmpty() || edtBValue.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int a = Integer.parseInt(edtAValue);
                    int b = Integer.parseInt(edtBValue);
                    int result = a * b;
                    edtResult.setText(String.valueOf(result));
                    return;
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtResult.setText("");
                String edtAValue = edtA.getText().toString().trim();
                String edtBValue = edtB.getText().toString().trim();
                if(edtAValue.isEmpty() || edtBValue.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int a = Integer.parseInt(edtAValue);
                    int b = Integer.parseInt(edtBValue);
                    float result = (float)a / b;
                    edtResult.setText(String.valueOf(result));
                    return;
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}