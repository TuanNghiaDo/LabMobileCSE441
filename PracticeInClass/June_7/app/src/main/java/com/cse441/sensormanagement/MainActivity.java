package com.cse441.sensormanagement;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cse441.sensormanagement.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Sensor accelerometer;
    SensorManager sensorManager;
    TextView txtX;
    TextView txtY;
    TextView txtZ;
    View viewBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtX = (TextView) findViewById(R.id.txtX);
        txtY = (TextView) findViewById(R.id.txtY);
        txtZ = (TextView) findViewById(R.id.txtZ);
        viewBackground = findViewById(R.id.viewBackground);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            txtX.setText("Trục X: " + String.format("%.2f", x) + " m/s²");
            txtY.setText("Trục Y: " + String.format("%.2f", y) + " m/s²");
            txtZ.setText("Trục Z: " + String.format("%.2f", z) + " m/s²");

            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);
            if (acceleration > 2.0f) {
                viewBackground.setBackgroundColor(Color.RED);
            } else {
                viewBackground.setBackgroundColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Không cần xử lý
    }
}
