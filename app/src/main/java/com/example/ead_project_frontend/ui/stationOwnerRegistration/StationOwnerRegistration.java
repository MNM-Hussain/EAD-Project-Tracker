package com.example.ead_project_frontend.ui.stationOwnerRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.api.ApiCall;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.model.FuelStop;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.adminUpdateFuel.AdminUpdateFuel;
import com.example.ead_project_frontend.ui.registration.Registration;
import com.example.ead_project_frontend.ui.stationOwnerLogin.StationOwnerLogin;

public class StationOwnerRegistration extends AppCompatActivity {
    private Button Station_Register_Cancel_button, Station_Register_button;
    private EditText input_Station_userName, input_Station_Name, input_station_branch, input_Station_email_registration, input_Station_Password_registration, input_station_ContactNumber;

    //DbHandler class is called here
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_registration);

        // Initializing with ID
        input_Station_userName = findViewById(R.id.input_Station_userName);
        input_Station_Name = findViewById(R.id.input_Station_Name);
        input_station_branch = findViewById(R.id.input_station_branch);
        input_Station_email_registration = findViewById(R.id.input_Station_email_registration);
        input_Station_Password_registration = findViewById(R.id.input_Station_Password_registration);
        input_station_ContactNumber = findViewById(R.id.input_station_ContactNumber);

        //Initializing DB
        DB = new DBHandler(this);

        //Initializing Button
        Station_Register_button = findViewById(R.id.Station_Register_button);
        Station_Register_Cancel_button = findViewById(R.id.Station_Register_Cancel_button);

        //cancel button
        Station_Register_Cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent
            }
        });

        //register button
        Station_Register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked");
                String Station_userName_Text = input_Station_userName.getText().toString();
                String Station_Name = input_Station_Name.getText().toString();
                String station_branch = input_station_branch.getText().toString();
                String Station_email_registration = input_Station_email_registration.getText().toString();
                String Station_Password_registration = input_Station_Password_registration.getText().toString();
                String station_ContactNumber = input_station_ContactNumber.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String phoneNumberPattern  = "[0-9]{10}";

                // ************validations*******************
                //Validation for Name
                if (TextUtils.isEmpty(Station_userName_Text)) {
                    input_Station_userName.setError("Please Enter Your Full Name");
                    return;
                }

                //Validation for StationName
                if (TextUtils.isEmpty(Station_Name)) {
                    input_Station_Name.setError("Please Enter the Station Name");
                    return;
                }

                //Validation for StationBranch
                if (TextUtils.isEmpty(station_branch)) {
                    input_station_branch.setError("Please Enter the Station Branch");
                    return;
                }

                //validation for email
                if (TextUtils.isEmpty(Station_email_registration )) {
                    input_Station_email_registration.setError("please enter a Email!!!");
                    return;
                } else if (Station_email_registration.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    input_Station_email_registration.setError("please enter Valid Email address!!!");
                }

                //validation for password
                if (TextUtils.isEmpty(Station_Password_registration)) {
                    input_Station_Password_registration.setError("please enter Password!!!");
                    return;
                }
                else if (Station_Password_registration.length() < 8) {
                    input_Station_Password_registration.setError("please enter Strong Password!!!");
                }

                //validation for contactNumber
                if (TextUtils.isEmpty(station_ContactNumber )) {
                    input_station_ContactNumber.setError("please enter Phone Number!!!");
                    return;
                } else if (station_ContactNumber.matches(phoneNumberPattern)) {
                    Toast.makeText(getApplicationContext(), "Phone number is Valid", Toast.LENGTH_SHORT).show();
                } else {
                    input_station_ContactNumber.setError("Enter a valid Phone number!!!");
                }

                // Check the existing user by nic and email
                Boolean checkExistingStationOwner = DB.checkExistingStationOwner(Station_email_registration);

                // validation that to make sure all the fields are filled without any empty fields
                if (Station_userName_Text.equals("") || Station_Name.equals("") || station_branch.equals("") || Station_email_registration.equals("") || Station_Password_registration.equals("") || station_ContactNumber.equals(""))
                    Toast.makeText(StationOwnerRegistration.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else
                    // if there are no any existing user with same nic and emailText then the data will be inserted
                    if (checkExistingStationOwner == false) {
                        Boolean InsertStationOwnerRegistrationData = DB.insertStationOwnerRegistration(Station_userName_Text, Station_Name, station_branch, Station_email_registration, Station_Password_registration, station_ContactNumber);
                        //api call
                        FuelStop fuelStop = new FuelStop(Station_Name, station_branch,Station_Name, Station_email_registration);
                        ApiCall.createFuelStop(fuelStop);

                        if (InsertStationOwnerRegistrationData == true) {
                            Toast.makeText(StationOwnerRegistration.this, "You have successfully Registered", Toast.LENGTH_SHORT).show();
                               MoveNextActivity();
                        } else {
                            Toast.makeText(StationOwnerRegistration.this, "Error!! Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StationOwnerRegistration.this, "Error!! Already an user exist, please sign-in", Toast.LENGTH_SHORT).show();
                    }



            }
        });

        //cancelButton
        Station_Register_Cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerRegistration.this, StationOwnerLogin.class);
                startActivity(intent);
            }
        });

    }

    public void MoveNextActivity() {
        Intent intent = new Intent(this, StationOwnerLogin.class);
        startActivity(intent);
    }
}