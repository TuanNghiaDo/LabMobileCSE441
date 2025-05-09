package com.examples_3.convertgeogorianyear;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
    Button btnConvert;
    EditText edtGeorianYear;
    TextView tvLunarYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnConvert = findViewById(R.id.btn_convert);
        tvLunarYear = findViewById(R.id.edt_lunar_year);
        edtGeorianYear = findViewById(R.id.edt_geogorian_year);
        btnConvert.setOnClickListener(v -> {
            String georianYear = edtGeorianYear.getText().toString().trim();
            if(georianYear.isEmpty()){
                Toast.makeText(MainActivity.this, "Ban quen nhap du lieu!", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    int georianYearInt = Integer.parseInt(georianYear);
                    String can = "";
                    String chi = "";

                    switch (georianYearInt % 10){
                        case 0:
                            can = "Canh";
                            break;
                        case 1:
                            can = "Tân";
                            break;
                        case 2:
                            can = "Nhâm";
                            break;
                        case 3:
                            can = "Quý";
                            break;
                        case 4:
                            can = "Giáp";
                            break;
                        case 5:
                            can = "Ất";
                            break;
                        case 6:
                            can = "Bính";
                            break;
                        case 7:
                            can = "Đinh";
                            break;
                        case 8:
                            can = "Mậu";
                            break;
                        case 9:
                            can = "Kỷ";
                            break;
                    }

                    switch (georianYearInt % 12){
                        case 0:
                            chi = "Thân";
                            break;
                        case 1:
                            chi = "Dậu";
                            break;
                        case 2:
                            chi = "Tuất";
                            break;
                        case 3:
                            chi = "Hợi";
                            break;
                        case 4:
                            chi = "Tý";
                            break;
                        case 5:
                            chi = "Sửu";
                            break;
                        case 6:
                            chi = "Dần";
                            break;
                        case 7:
                            chi = "Mão";
                            break;
                        case 8:
                            chi = "Thìn";
                            break;
                        case 9:
                            chi = "Tỵ";
                            break;
                        case 10:
                            chi = "Ngọ";
                            break;
                        case 11:
                            chi = "Mùi";
                            break;
                    }
                    String lunarYear = can + " " + chi;
                    tvLunarYear.setText(lunarYear);
                    return;
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Du lieu khong hop le! Hay nhap nam duong lich!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}