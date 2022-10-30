package com.example.ead_project_frontend.ui.stationOwnerUpdateProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;
import com.example.ead_project_frontend.ui.stationOwnerDashboard.StationOwnerDashboard;
import com.example.ead_project_frontend.ui.stationOwnerProfile.StationOwnerProfile;
import com.example.ead_project_frontend.ui.updateProfile.UpdateProfile;

public class StationOwnerUpdateProfile extends AppCompatActivity {
    private Button cancelUpdateAdmin, UpdateAdmin;
    private EditText updateAdminName, updateAdminContactNumber, updateAdminPassword, updateStationName, updateStationBranch, updateStationOwnerEmail;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_update_profile);

        //Initializing with ID
        updateAdminName = findViewById(R.id.update_userName_Admin);
        updateAdminPassword = findViewById(R.id.Update_Admin_password);
        updateAdminContactNumber = findViewById(R.id.Update_Admin_ContactNumber);
        updateStationName = findViewById(R.id.Update_StationName);
        updateStationBranch = findViewById(R.id.Update_stationBranch_admin);
        updateStationOwnerEmail = findViewById(R.id.Update_Email_registration_admin);

        cancelUpdateAdmin = findViewById(R.id.Update_Cancel_button_admin);
        UpdateAdmin = findViewById(R.id.Update_button_admin);

        //Initializing DB
        DB = new DBHandler(this);

        //Get the data in fields
        Cursor cursor = DB.getUserRegistration();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No any Users Exist", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                updateAdminName.setText(Session.ADMIN_USER_NAME);
                updateAdminPassword.setText(Session.ADMIN_USER_PASSWORD);
                updateAdminContactNumber.setText(Session.ADMIN_USER_CONTACT);
                updateStationName.setText(Session.ADMIN_USER_STATION_NAME);
                updateStationBranch.setText(Session.ADMIN_USER_STATION_BRANCH);
                updateStationOwnerEmail.setText(Session.ADMIN_USER_EMAIL);
//                }
            } catch (Exception exception) {
                Log.e("Error is: ", exception.getMessage());
            }
        }

        //function to happen when clicks update button
        UpdateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Update_adminName = updateAdminName.getText().toString();
                String Update_adminPassword = updateAdminPassword.getText().toString();
                String Update_adminContactNumber = updateAdminContactNumber.getText().toString();
                String Update_stationName = updateStationName.getText().toString();
                String Update_stationBranch = updateStationBranch.getText().toString();
                String Update_stationOwnerEmail = updateStationOwnerEmail.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String phoneNumberPattern  = "[0-9]{10}";

                // ************validations*******************
                //Validation for Name
                if (TextUtils.isEmpty(Update_adminName)) {
                    updateAdminName.setError("Please Enter Your Full Name");
                    return;
                }

                //Validation for StationName
                if (TextUtils.isEmpty(Update_stationName)) {
                    updateStationName.setError("Please Enter the Station Name");
                    return;
                }

                //Validation for StationBranch
                if (TextUtils.isEmpty(Update_stationBranch)) {
                    updateStationBranch.setError("Please Enter the Station Branch");
                    return;
                }

                //validation for email
                if (TextUtils.isEmpty(Update_stationOwnerEmail)) {
                    updateStationOwnerEmail.setError("please enter a Email!!!");
                    return;
                } else if (Update_stationOwnerEmail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    updateStationOwnerEmail.setError("please enter Valid Email address!!!");
                }

                //validation for password
                if (TextUtils.isEmpty(Update_adminPassword)) {
                    updateAdminPassword.setError("please enter Password!!!");
                    return;
                }
                else if (Update_adminPassword.length() < 8) {
                    updateAdminPassword.setError("please enter Strong Password!!!");
                }

                //validation for contactNumber
                if (TextUtils.isEmpty(Update_adminContactNumber)) {
                    updateAdminContactNumber.setError("please enter Phone Number!!!");
                    return;
                } else if (Update_adminContactNumber.matches(phoneNumberPattern)) {
                    Toast.makeText(getApplicationContext(), "Phone number is Valid", Toast.LENGTH_SHORT).show();
                } else {
                    updateAdminContactNumber.setError("Enter a valid Phone number!!!");
                }

                Boolean checkAdminUpdate = DB.updateAdminRegistration(Update_adminName, Update_stationName, Update_stationBranch, Update_stationOwnerEmail, Update_adminPassword, Update_adminContactNumber);
                if (checkAdminUpdate = true) {
                    Toast.makeText(StationOwnerUpdateProfile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                    //overwrite the session values
                    Session.ADMIN_USER_NAME = updateAdminName.getText().toString();
                    Session.ADMIN_USER_PASSWORD = updateAdminPassword.getText().toString();
                    Session.ADMIN_USER_CONTACT = updateAdminContactNumber.getText().toString();
                    Session.ADMIN_USER_STATION_NAME = updateStationName.getText().toString();
                    Session.ADMIN_USER_STATION_BRANCH = updateStationBranch.getText().toString();
                    Session.ADMIN_USER_EMAIL = updateStationOwnerEmail.getText().toString();

                    //setting the value
                    updateAdminName.setText(Session.ADMIN_USER_NAME);
                    updateAdminPassword.setText(Session.ADMIN_USER_PASSWORD);
                    updateAdminContactNumber.setText(Session.ADMIN_USER_CONTACT);
                    updateStationName.setText(Session.ADMIN_USER_STATION_NAME);
                    updateStationBranch.setText(Session.ADMIN_USER_STATION_BRANCH);
                    updateStationOwnerEmail.setText(Session.ADMIN_USER_EMAIL);

                    Intent intent = new Intent(StationOwnerUpdateProfile.this, StationOwnerProfile.class);
                    startActivity(intent);

                } else
                    Toast.makeText(StationOwnerUpdateProfile.this, "Update is failed", Toast.LENGTH_SHORT).show();
            }
        });

        //if canceled then needs to move next page
        cancelUpdateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerUpdateProfile.this, StationOwnerProfile.class);
                startActivity(intent);
            }
        });

    }
}