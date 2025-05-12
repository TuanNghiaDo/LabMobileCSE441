package com.examples_7.learnintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    Button btnCancel;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        btnCancel = findViewById(R.id.button_cancel);
        tvResult = findViewById(R.id.tv_result);
        Bundle extras = getIntent().getExtras();
        int a = extras.getInt("a");
        int b = extras.getInt("b");
        if(a == 0 && b == 0) {
            tvResult.setText("There are countless solutions");
        }else if(a==0 && b!=0) {
            tvResult.setText("There are no solutions");
        }else{
            DecimalFormat dcf = new DecimalFormat("0.##");
            tvResult.setText(dcf.format(-b*1.0/a));
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}