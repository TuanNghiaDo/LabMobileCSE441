package com.examples_8.intent_call_sms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallPhoneActivity extends AppCompatActivity {
    ImageButton btnCallPhone;
    Button btnBack;
    EditText edtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_call_phone);
        btnCallPhone = findViewById(R.id.btn_call_phone);
        btnBack = findViewById(R.id.btn_back);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);

        btnCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edtPhoneNumber.getText().toString()));
                // Ask the user to grant access to the calling feature
                if (ContextCompat.checkSelfPermission(CallPhoneActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallPhoneActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                }
                startActivity(callIntent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}