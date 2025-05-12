package com.examples_7.learnintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
Button btnResult;
EditText edtA, edtB, edtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnResult = findViewById(R.id.button_result);
        edtA = findViewById(R.id.edt_A);
        edtB = findViewById(R.id.edt_B);
        edtResult = findViewById(R.id.edt_result);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, ResultActivity.class);
                //Pass data from MainActivity to ResultActivity
                Bundle bundle = new Bundle();
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                bundle.putInt("a", a);
                bundle.putInt("b", b);
                intent1.putExtras(bundle);
                //Or use intent1.putExtra("myBundle", bundle);
                //Display the child activity
                startActivityForResult(intent1, 99); //99 is uesed to identify the request code
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33) {
            Bundle bundle = data.getExtras();
            int result = bundle.getInt("result");
            edtResult.setText("Tổng hai số là: "+String.valueOf(result));
        }
        if(requestCode == 99 && resultCode == 34) {
            Bundle bundle = data.getExtras();
            int result = bundle.getInt("result");
            edtResult.setText("Hiệu hai số là: "+String.valueOf(result));
        }
    }
}