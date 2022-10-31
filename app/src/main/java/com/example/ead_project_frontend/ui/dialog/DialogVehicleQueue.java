package com.example.ead_project_frontend.ui.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ead_project_frontend.R;

public class DialogVehicleQueue extends AppCompatActivity {
    TextView getTotalQueue, getCarQueue, getBikeQueue, getThreeWheelerQueue, getBusQueue;
    Button btn_Queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_vehicle_queue);

        // Initialization of ID's
        getTotalQueue = findViewById(R.id.getTotalQueue);
        getCarQueue = findViewById(R.id.getCarQueue);
        getBikeQueue = findViewById(R.id.getBikeQueue);
        getThreeWheelerQueue = findViewById(R.id.getThreeWheelerQueue);
        getBusQueue = findViewById(R.id.getBusQueue);
    }
}