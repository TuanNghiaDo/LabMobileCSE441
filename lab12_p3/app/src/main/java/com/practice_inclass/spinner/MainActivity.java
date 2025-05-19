package com.practice_inclass.spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String arr1 [] = {"Hàng điện tử", "Hàng hóa chất", "Hàng gia dụng", "Hàng xây dựng"};
    TextView tv1;
    Spinner spn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        spn1 = (Spinner) findViewById(R.id.spn1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr1);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn1.setAdapter(adapter);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //id là id của đối tượg ở vị trí position
                tv1.setText(arr1[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}