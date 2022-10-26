package com.example.ead_project_frontend.ui.adminUpdateFuel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.stationOwnerRegistration.StationOwnerRegistration;

import java.util.Locale;

public class AdminUpdateFuel extends AppCompatActivity {
    private EditText input_arrivalTime_admin_fuel;
    private TextView back_arrow_arrival;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_fuel);

        //Initialize With ID
        input_arrivalTime_admin_fuel = findViewById(R.id.input_arrivalTime_admin_fuel);
        back_arrow_arrival = findViewById(R.id.back_arrow_arrival);

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
                Intent intent = new Intent(AdminUpdateFuel.this, StationOwnerRegistration.class);
                startActivity(intent);
            }
        });
    }

    // method to listen the radio button
    public void clickRadioAdminUpdateFuelType(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup_fuelType_adminFuel);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(this, radioButton.getText() + " type is selected", Toast.LENGTH_SHORT).show();
//        Session.FUEL_TYPE = radioButton.getText().toString();
    }
}