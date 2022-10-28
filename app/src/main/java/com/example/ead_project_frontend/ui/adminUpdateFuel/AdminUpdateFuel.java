package com.example.ead_project_frontend.ui.adminUpdateFuel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.stationOwnerDashboard.StationOwnerDashboard;
import com.example.ead_project_frontend.ui.stationOwnerRegistration.StationOwnerRegistration;

import java.util.Locale;

public class AdminUpdateFuel extends AppCompatActivity {
    private EditText input_arrivalTime_admin_fuel, input_numberOfLitres_admin;
    private TextView getStationName_adminFuelUpdate, getStationBranch_adminFuelUpdate, getPetrolAvailability_adminFuel, getDieselAvailability_adminFuel;
    private TextView back_arrow_arrival;
    private Button btn_admin_updateFuel;

    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_fuel);

        //Initialize With ID
        input_arrivalTime_admin_fuel = findViewById(R.id.input_arrivalTime_admin_fuel);
        input_numberOfLitres_admin = findViewById(R.id.input_numberOfLitres_admin);

        back_arrow_arrival = findViewById(R.id.back_arrow_arrival);
        btn_admin_updateFuel = findViewById(R.id.btn_admin_updateFuel);

        getStationName_adminFuelUpdate = findViewById(R.id.getStationName_adminFuelUpdate);
        getStationBranch_adminFuelUpdate = findViewById(R.id.getStationBranch_adminFuelUpdate);
        getPetrolAvailability_adminFuel = findViewById(R.id.getPetrolAvailability_adminFuel);
        getDieselAvailability_adminFuel = findViewById(R.id.getDieselAvailability_adminFuel);


        //setting listener and referred a tutorial to do this reference [4]

        input_arrivalTime_admin_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reference [4]
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        input_arrivalTime_admin_fuel.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                        System.out.println("arival time " + input_arrivalTime_admin_fuel.getText().toString());

                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(AdminUpdateFuel.this, onTimeSetListener, hour, minute, true);
                timePickerDialog.setTitle("Select Fuel Arrival Time");
                timePickerDialog.show();
            }
        });

        //Setting up the arrow to move previous page
        back_arrow_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminUpdateFuel.this, StationOwnerDashboard.class);
                startActivity(intent);
            }
        });

        //The Fuel updating Button
        btn_admin_updateFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });
    }

    // method to listen the radio button
    public void clickRadioAdminUpdateFuelType(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup_fuelType_adminFuel);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(this, radioButton.getText() + " type is selected", Toast.LENGTH_SHORT).show();
        Session.ADMIN_FUEL_TYPE = radioButton.getText().toString();
    }
}