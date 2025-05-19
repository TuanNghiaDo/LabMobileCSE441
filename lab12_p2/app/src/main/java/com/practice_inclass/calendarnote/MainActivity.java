package com.practice_inclass.calendarnote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv1;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> arrAdater;
    EditText edtWork, edtHour, edtMinute;
    TextView txtDate;
    Button btnAddWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = findViewById(R.id.lv1);
        edtWork = findViewById(R.id.edt_work);
        edtHour = findViewById(R.id.edt_hour);
        edtMinute = findViewById(R.id.edt_minute);
        txtDate = findViewById(R.id.tv_date);
        btnAddWork = findViewById(R.id.btn_add_work);

        arrayWork = new ArrayList<>();
        //Khai bao adapter, dua mang du lieu vao adpater
        arrAdater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);
        //Dua adapter co du lieu len listview
        lv1.setAdapter(arrAdater);
        //Lay ngay gio he thong
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo dinh dang dd/MM/yyyy
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hon nay: " + simpleDateFormat.format(currentDate));

        //Su kien click nut them cong viec
        btnAddWork.setOnClickListener(new View.OnClickListener() { //onClickListener là một interface
            @Override
            public void onClick(View v) {
                //Neu 1 trong 3 edt khong co noi dung thi hien len thong bao bang hop thoai Dialog
                if (edtWork.getText().toString().equals("") || edtHour.getText().toString().equals("") || edtMinute.getText().toString().equals("")) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Infor missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    //Lấy nội dung công việc và thời gian từ EditText và đưa vào mảng Arraywork, cập nhật lại Adapter
                    String str = edtWork.getText().toString() + " - " + edtHour.getText().toString() + ":" + edtMinute.getText().toString();
                    //Để thêm dữ liệu vào trong listview chúng ta cần hai bước:
                    //Bước 1: thêm dữ liệu vào trong mảng
                    arrayWork.add(str);
                    //Bước 2: cập nhật lại adapter
                    arrAdater.notifyDataSetChanged();
                    edtWork.setText("");
                    edtHour.setText("");
                    edtMinute.setText("");
                }
            }
        });
    }
}
