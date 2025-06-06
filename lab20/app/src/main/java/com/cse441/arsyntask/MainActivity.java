package com.cse441.arsyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cse441.arsyntask.R;
import com.cse441.arsyntask.model.MyAsyncTask;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    MyAsyncTask mytt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.button1);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doStart();
            }
        });
    }

    private void doStart() {
        mytt = new MyAsyncTask(this);
        mytt.execute();
    }
}