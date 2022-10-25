package com.example.ead_project_frontend.ui.updateArrivalStation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.updatePumpedFuelStatus.UpdatePumpedFuelStatus;

public class UpdateArrivalStation extends AppCompatActivity {
    private Button btn_arrivedStation;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_arrival_station);

        // Initializing with Id
        btn_arrivedStation = findViewById(R.id.btn_arrivedStation);

        //To get the popup
        btn_arrivedStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        //Created the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_alert_arrival_station);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_flow_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogPop_Animation; //Setting the animations to dialog

        //Initializing popup buttons
        Button btn_arrived = dialog.findViewById(R.id.btn_arrived);
        Button btn_notArrived = dialog.findViewById(R.id.btn_notArrived);


        btn_arrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateArrivalStation.this, "your arrival is confirmed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                //send to pumped status page from popup
                Intent intent = new Intent(UpdateArrivalStation.this, UpdatePumpedFuelStatus.class);
                startActivity(intent);
            }
        });

        btn_notArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateArrivalStation.this, "not confirmed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}