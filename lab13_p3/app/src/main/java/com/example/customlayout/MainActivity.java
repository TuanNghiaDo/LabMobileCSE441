package com.example.customlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customlayout.adapter.MyarrayAdapter;
import com.example.customlayout.models.Image;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String[] arrayName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6", "Ảnh 7", "Ảnh 8", "Ảnh 9", "Ảnh 10", "Ảnh 11", "Ảnh 12"};
    public static int[] arrayImage = {
            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
            R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12,};
    GridView gridViewDemo;
    //Sử dụng MyArrayAdapter thay vì ArrayAdapter
    MyarrayAdapter myDapter;
    ArrayList<Image> arrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridViewDemo = (GridView)findViewById(R.id.grid1);
        arrImage = new ArrayList<Image>();
        //khởi tạo đối tượng adapter và gán Data source
        myDapter = new MyarrayAdapter(MainActivity.this, R.layout.listitem, arrImage); //Lấy layout từ listitem.xml

        gridViewDemo.setAdapter(myDapter);

        for(int i = 0; i < arrayName.length; i++){
            Image myimage = new Image();
            myimage.setImg(arrayImage[i]);
            myimage.setName(arrayName[i]);
            arrImage.add(myimage);
            //Gọi hàm cập nhật giao diện
        }
        myDapter.notifyDataSetChanged();

        //Lắng nghe sự kiện click vào item trong GridView
        gridViewDemo.setOnItemClickListener((parent, view, position, id) ->{
            Intent intent1 = new Intent(MainActivity.this, SubActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("TITLE", position);
            intent1.putExtras(bundle);
            startActivity(intent1);
        });
    }
}