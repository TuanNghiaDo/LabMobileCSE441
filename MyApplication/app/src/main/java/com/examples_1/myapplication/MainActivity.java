package com.examples_1.myapplication;

import android.os.Bundle;
import android.util.Log;
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
                String textA = edt_A.getText().toString().trim();
                String textB = edt_B.getText().toString().trim();
                if(textA.isEmpty() || textB.isEmpty()){
                    // Parameters in the Toast.makeText() method: which activity display?, message,display time
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ cả hai số a và b", Toast.LENGTH_SHORT).show();
                    edtResult.setText("");
                    return;
                }
                try {
                    int a = Integer.parseInt(edt_A.getText().toString());
                    int b = Integer.parseInt(edt_B.getText().toString());
                    int c = a + b;
                    edtResult.setText(String.valueOf(c));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số nguyên!", Toast.LENGTH_SHORT).show();
                    edtResult.setText("");
                    Log.e("MainActivity", "Lỗi định dạng số: " + e.getMessage());
                }

            }
        });
    }
}