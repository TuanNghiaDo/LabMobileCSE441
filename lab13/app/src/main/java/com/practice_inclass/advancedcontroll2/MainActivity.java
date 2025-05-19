package com.practice_inclass.advancedcontroll2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView selection;
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;
    //Khỏi tạo mảng để test
    String arrProvince[] = {"Hà Nội", "Hà Nam", "Hà Giang", "Hà Tĩnh", "Lào Cai", "Lạng Sơn", "Lai Châu", "Yên Bái", "Hưng Yên", "Nghệ An", "Nam Định"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        singleComplete = (AutoCompleteTextView) findViewById(R.id.edit_auto);
        multiComplete = (MultiAutoCompleteTextView) findViewById(R.id.edit_multi_auto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrProvince);
        singleComplete.setAdapter(adapter);

        multiComplete.setAdapter(adapter);
        //Đối với MultiAutoCompleteTextView bắt buộc phải gọi dòng lệnh này
        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singleComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selection.setText(singleComplete.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}