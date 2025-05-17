package com.examples_11.advanced_control_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.selected);

        //Khởi tạo dữ liệu cho mảng(fake dữ liệu)
        final String[] data = {"Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730", "Sony Xperia XZ", "Redmi K30 5G", "Redmi Note 12", "Vsmart", "Iphone 14 Promax"};

        //Khai báo Adapter và gán(đổ) dữ liệu cho Adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        //Khai báo listview vả đưa adapter vào listview
        ListView lv1 = findViewById(R.id.lv1);
        lv1.setAdapter(adapter1);

        //Viết sự kiện khi click vào 1 dòng trên listview
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv1.setText("Vị trí: " + position + " : " + data[position]);
            }
        });
    }
}