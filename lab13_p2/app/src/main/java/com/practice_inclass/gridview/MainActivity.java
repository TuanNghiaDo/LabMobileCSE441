package com.practice_inclass.gridview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.practice_inclass.gridview.adapter.ImageAdapter;

public class MainActivity extends AppCompatActivity {
    // Tạo ra mảng các ID của các hình ảnh
    private Integer[] mImageIds = {
            R.drawable.images1,
            R.drawable.images2,
            R.drawable.images3,
            R.drawable.images4,
            R.drawable.images5,
            R.drawable.images6
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView selection = (TextView) findViewById(R.id.selection);
        final GridView gridView = (GridView)findViewById(R.id.grid_view1);

        ImageAdapter imageAdapter = new ImageAdapter(this, mImageIds);
        gridView.setAdapter(imageAdapter);
    }
}