package com.example.ead_project_frontend.ui.updatePumpedFuelStatus;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.ead_project_frontend.ui.updateArrivalStation.UpdateArrivalStation;

public class UpdatePumpedFuelStatus extends AppCompatActivity {
    private Button btn_Exit_Pumped, btn_Pumped;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pumped_fuel_status);

        // Initializing with Id
        btn_Exit_Pumped = findViewById(R.id.btn_Exit_Pumped);
        btn_Pumped = findViewById(R.id.btn_Pumped);

        //send to home if exit
        btn_Exit_Pumped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePumpedFuelStatus.this, Login.class);
                startActivity(intent);
            }
        });

        //to get the popup
        btn_Pumped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        //Created the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_alert_pumped);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_flow_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogPop_Animation; //Setting the animations to dialog

        //Initializing popup buttons
        Button btn_ConfirmInsertPump= dialog.findViewById(R.id.btn_ConfirmInsertPump);
        Button btn_CancelInsertPump = dialog.findViewById(R.id.btn_CancelInsertPump);

        btn_ConfirmInsertPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdatePumpedFuelStatus.this, "your pumped is confirmed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                //send to pumped status page from popup
                Intent intent = new Intent(UpdatePumpedFuelStatus.this, Login.class);
                startActivity(intent);
            }
        });

        btn_CancelInsertPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdatePumpedFuelStatus.this, "you cancelled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}