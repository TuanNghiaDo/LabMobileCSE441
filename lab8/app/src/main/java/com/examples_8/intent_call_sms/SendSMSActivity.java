package com.examples_8.intent_call_sms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SendSMSActivity extends AppCompatActivity {
    EditText edtPhoneNumber;
    ImageButton btnSendSMS;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send_smsactivity);
        btnSendSMS = findViewById(R.id.btn_send_sms);
        btnBack = findViewById(R.id.btn_back);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + edtPhoneNumber.getText().toString()));
                startActivity(smsIntent);
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