package com.practice_inclass.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailImageActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);
        imageView = findViewById(R.id.detail_image_view);

        //Lấy intent được truyền từ MainActivity
        Intent intent = getIntent();

        //Lấy ID của hình ảnh từ intent
        int imageId = intent.getIntExtra("imageId", 0);
        if (imageId != 0) {
            //Đặt hình ảnh vào ImageView
            imageView.setImageResource(imageId);
        } else {
            //Nếu không có ID hình ảnh, có thể hiển thị một thông báo hoặc xử lý lỗi
        }
    }
}