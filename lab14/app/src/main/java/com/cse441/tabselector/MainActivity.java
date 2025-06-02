package com.cse441.tabselector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    EditText edta, edtb;
    Button btncong;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        // TODO Auto-generated method stub
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Xulycong();
            }
        });
    }

    private void Xulycong() {
        // TODO Auto-generated method stub
        String strA = edta.getText().toString();
        String strB = edtb.getText().toString();

        if (strA.isEmpty() || strB.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập cả hai số", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            int a = Integer.parseInt(strA);
            int b = Integer.parseInt(strB);
            int ketquaTinh = a + b;
            String c = a + " + " + b + " = " + ketquaTinh;
            list.add(c);
            myarray.notifyDataSetChanged();

            edta.setText("");
            edtb.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Đầu vào không hợp lệ. Vui lòng nhập số.", Toast.LENGTH_SHORT).show();
        }
    }


    private void addControl() {
        // TODO Auto-generated method stub
        TabHost tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);

        tab1.setIndicator("", getResources().getDrawable(R.drawable.phepcong));
        tab.addTab(tab1);


        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);

        tab2.setIndicator("", getResources().getDrawable(R.drawable.lichsu));
        tab.addTab(tab2);

        edta = (EditText) findViewById(R.id.edta);
        edtb = (EditText) findViewById(R.id.edtb);
        btncong = (Button) findViewById(R.id.btncong);
        lv1 = (ListView) findViewById(R.id.lv1);
        list = new ArrayList<String>();

        myarray = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                list);
        lv1.setAdapter(myarray);
    }
}