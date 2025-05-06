package com.examples_3.bmi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnBMI;
    EditText edtName, edtHeight, edtWeight, edtBMI, edtDiagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnBMI = findViewById(R.id.btnBMI);
        edtName = findViewById(R.id.name);
        edtHeight = findViewById(R.id.height);
        edtWeight = findViewById(R.id.weight);
        edtBMI = findViewById(R.id.BMI);
        edtDiagnosis = findViewById(R.id.diagnosis);

        btnBMI.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String heightStr = edtHeight.getText().toString();
            String weightStr = edtWeight.getText().toString();

            if (name.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }else{
                try {
                    double height = Double.parseDouble(heightStr) / 100; // Convert cm to m
                    double weight = Double.parseDouble(weightStr);
                    double bmi = weight / (height * height);
                    String diagnosis;

                    if (bmi < 18) {
                        diagnosis = "Người gầy";
                    } else if (bmi <= 24.9) {
                        diagnosis = "Người bình thường";
                    } else if (bmi <= 29.9) {
                        diagnosis = "Người béo phì độ I";
                    } else if(bmi <= 34.9){
                        diagnosis = "Người béo phì độ II";
                    }else{
                        diagnosis = "Người béo phì độ III";
                    }
                    edtBMI.setText(String.format("%.2f", bmi));
                    edtDiagnosis.setText(diagnosis);
                    return;
                }catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chiều cao và cân nặng hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}