package com.examples_6.personal_infor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtCmnd, edtInforSupplement;
    Button btnSend;
    RadioGroup radioGroup;
    CheckBox readArticle, readBook, readCoding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.name);
        edtCmnd = findViewById(R.id.cmnd);
        edtInforSupplement = findViewById(R.id.infor_supplement);
        btnSend = findViewById(R.id.btn_send);
        radioGroup = findViewById(R.id.group_radiobtn);
        readArticle = findViewById(R.id.read_article);
        readBook = findViewById(R.id.read_book);
        readCoding = findViewById(R.id.read_coding);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowInformation();
            }
        });
    }

    private void doShowInformation() {
        //check if name is valid
        String name = edtName.getText().toString().trim();
        if(name.length() < 3){
            edtName.requestFocus();
            edtName.selectAll();
            Toast.makeText(this, "Name must be at least 3 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        //check if cmnd is valid
        String cmnd = edtCmnd.getText().toString().trim();
        if(cmnd.length() != 9){
            edtCmnd.requestFocus();
            edtCmnd.selectAll();
            Toast.makeText(this, "CMND must be 9 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        //check degree
        String degree = "";
        int id = radioGroup.getCheckedRadioButtonId(); //return id of selected radio button
        if(id == -1){
            Toast.makeText(this, "Must choose degree!", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rbSelected = findViewById(id);
        degree = rbSelected.getText().toString();
        //check hobbies
        String hobbies = "";
        if(readArticle.isChecked()){
            hobbies += readArticle.getText().toString() + "\n";
        }
        if(readBook.isChecked()){
            hobbies += readBook.getText().toString() + "\n";
        }
        if (readCoding.isChecked()){
            hobbies += readCoding.getText().toString() + "\n";
        }
        String inforSupplement = edtInforSupplement.getText().toString().trim();
        //Create a dialog to show information
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Personal Information");
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //Ceate content in dialog
        String msg = name + "\n";
        msg += cmnd + "\n";
        msg += degree + "\n";
        msg += hobbies;
        msg += "----------" + "\n";
        msg += "Infor Supplement:" + "\n";
        msg += inforSupplement + "\n";
        msg += "----------";
        builder.setMessage(msg);
        builder.create().show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.areyousure);
        b.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}