package com.examples_7.learnintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    Button btnAdd, btnSub;
    EditText edtReceiveA, edtReceiveB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        btnAdd = findViewById(R.id.button_add);
        btnSub = findViewById(R.id.button_sub);
        edtReceiveA = findViewById(R.id.edt_receive_A);
        edtReceiveB = findViewById(R.id.edt_receive_B);
        Intent intent2 = getIntent();
        Bundle extras = intent2.getExtras();
        int a = extras.getInt("a");
        int b = extras.getInt("b");
        edtReceiveA.setText(a+"");
        edtReceiveB.setText(b+"");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a + b;
                //Pass data from ResultActivity to MainActivity
                intent2.putExtra("result", sum);
                setResult(33, intent2); //33 is used to identify the result code
                finish();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a - b;
                //Pass data from ResultActivity to MainActivity
                intent2.putExtra("result", sum);
                setResult(34, intent2); //33 is used to identify the result code
                finish();
            }
        });
    }
}