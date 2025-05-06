package com.examples_3.tempertureconvertationapp;

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

    Button btnConvertToCelsius;
    Button btnConvertToFahrenheit, btnClear;
    EditText edtFahrenheit, edtCelsius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConvertToCelsius = findViewById(R.id.btnConvertToCelsius);
        btnConvertToFahrenheit = findViewById(R.id.btnConvertToFahrenheit);
        btnClear = findViewById(R.id.clear);
        edtFahrenheit = findViewById(R.id.edtFahrenheit);
        edtCelsius = findViewById(R.id.edtCelsius);

        btnConvertToCelsius.setOnClickListener(v -> {
            String fahrenheit = edtFahrenheit.getText().toString().trim();
            if (fahrenheit.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui long nhap nhiet do", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    double fahrenheitValue = Double.parseDouble(fahrenheit);
                    double celsiusValue = (fahrenheitValue - 32) * 5 / 9;
                    edtCelsius.setText(String.valueOf(celsiusValue));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Nhap sai dinh dang, vui long nhap lai!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConvertToFahrenheit.setOnClickListener(v -> {
            String celsius = edtCelsius.getText().toString().trim();
            if (celsius.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui long nhap nhiet do", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    double celsiusValue = Double.parseDouble(celsius);
                    double fahrenheitValue = celsiusValue * 9 / 5 + 32;
                    edtFahrenheit.setText(String.valueOf(fahrenheitValue));
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Nhap sai dinh dang, vui long nhap lai!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(v -> {
            edtFahrenheit.setText("");
            edtCelsius.setText("");
        });
    }
}