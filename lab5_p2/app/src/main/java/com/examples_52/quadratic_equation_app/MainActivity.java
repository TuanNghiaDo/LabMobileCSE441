package com.examples_52.quadratic_equation_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextA, editTextB, editTextC;
    Button btnSolve, btnBack, btnContinue;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextA = findViewById(R.id.edt_a);
        editTextB = findViewById(R.id.edt_b);
        editTextC = findViewById(R.id.edt_c);
        btnSolve = findViewById(R.id.btn_solve);
        btnBack = findViewById(R.id.btn_back);
        btnContinue = findViewById(R.id.btn_continue);
        tvResult = findViewById(R.id.tv_result);

        // Set up button click listeners
        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtA = editTextA.getText().toString().trim();
                String edtB = editTextB.getText().toString().trim();
                String edtC = editTextC.getText().toString().trim();
                if(edtA.isEmpty() || edtB.isEmpty() || edtC.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    try {
                        double a = Double.parseDouble(edtA);
                        double b = Double.parseDouble(edtB);
                        double c = Double.parseDouble(edtC);

                        // Calculate the discriminant
                        double discriminant = b * b - 4 * a * c;

                        if (discriminant > 0) {
                            String root1 = String.format("%.2f", (-b + Math.sqrt(discriminant)) / (2 * a));
                            String root2 = String.format("%.2f",(-b - Math.sqrt(discriminant)) / (2 * a));
                            tvResult.setText("The equation has two solutions: x1 = " + root1 + ", x2 = " + root2);
                            return;
                        } else if (discriminant == 0) {
                            String root = String.format("%.2f", -b / (2 * a));
                            tvResult.setText("The equation has a repeated root x = " + root);
                            return;
                        } else {
                            tvResult.setText("The equation has no solution!");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextA.setText("");
                editTextB.setText("");
                editTextC.setText("");
                editTextA.requestFocus();
            }
        });
    }
}