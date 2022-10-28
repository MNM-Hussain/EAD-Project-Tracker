package com.example.ead_project_frontend.ui.updatePumpedFuelStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.api.ApiCall;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;
import com.example.ead_project_frontend.ui.updateArrivalStation.UpdateArrivalStation;

public class UpdatePumpedFuelStatus extends AppCompatActivity {
    private Button btn_Exit_Pumped, btn_Pumped;
    private Dialog dialog;
    private TextView back_arrow;
    private EditText fuelAmount;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pumped_fuel_status);

        savedInstanceState = getIntent().getExtras();
        String ID = savedInstanceState.getString("ID");

        // Initializing with Id
        back_arrow = findViewById(R.id.back_arrow);
        btn_Exit_Pumped = findViewById(R.id.btn_Exit_Pumped);
        btn_Pumped = findViewById(R.id.btn_Pumped);

        //
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePumpedFuelStatus.this, UpdateArrivalStation.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", ID);
                intent.putExtras(bundle);
                startActivity(intent);

                startActivity(intent);
            }
        });

        //Initializing timer and this was referred by a tutorial [5]
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Wait-Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        //setting a listener
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 1000000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(UpdatePumpedFuelStatus.this, "Timeout!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Starting the timer when user arrived to station
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }

        //send to home if exit
        btn_Exit_Pumped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePumpedFuelStatus.this, NavigationBar.class);
                startActivity(intent);

                //stop the timer
                if (running) {
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    System.out.println(pauseOffset + "difference time");
                    running = false;
                }

                //api call
                ApiCall.decrementQueue(ID, Session.VECHILE_TYPE);

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
        fuelAmount = dialog.findViewById(R.id.input_numberOfLitres);


        dialog.setContentView(R.layout.dialog_alert_pumped);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_flow_background));

        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogPop_Animation; //Setting the animations to dialog

        //Initializing popup buttons
        Button btn_ConfirmInsertPump = dialog.findViewById(R.id.btn_ConfirmInsertPump);
        Button btn_CancelInsertPump = dialog.findViewById(R.id.btn_CancelInsertPump);

        btn_ConfirmInsertPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdatePumpedFuelStatus.this, "your pumped is confirmed", Toast.LENGTH_SHORT).show();
                //System.out.println(fuelAmount.getText().toString());
                dialog.dismiss();

                //api call
                ApiCall.decrementQueue(ID, Session.VECHILE_TYPE);
//                ApiCall.decrementfuel(ID,Session.FUEL_TYPE,Session.FUEL_AMOUNT);

                //send to pumped status page from popup
                Intent intent = new Intent(UpdatePumpedFuelStatus.this, NavigationBar.class);
                startActivity(intent);

                //Stop the timer
                if (running) {
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    System.out.println(pauseOffset + "difference time");
                    running = false;
                }
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