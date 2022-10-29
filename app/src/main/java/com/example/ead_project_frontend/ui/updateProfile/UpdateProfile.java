package com.example.ead_project_frontend.ui.updateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.fragment.FragmentProfile;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;

public class UpdateProfile extends AppCompatActivity {
    private Button Update_Cancel_button, Update_button;
    private EditText update_userName, Update_NIC, Update_email_registration, Update_Password_registration, Update_vehicleType, Update_FuelType;
    DBHandler DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //Initialing with ID
        update_userName = findViewById(R.id.update_userName);
        Update_NIC = findViewById(R.id.Update_NIC);
        Update_email_registration = findViewById(R.id.Update_email_registration);
        Update_Password_registration = findViewById(R.id.Update_Password_registration);
        Update_vehicleType = findViewById(R.id.Update_vehicleType);
        Update_FuelType = findViewById(R.id.Update_FuelType);
        Update_button = findViewById(R.id.Update_button);
        Update_Cancel_button = findViewById(R.id.Update_Cancel_button);

        //Initializing DB
        DB = new DBHandler(this);


        //Get the data
        Cursor cursor = DB.getUserRegistration();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No any Users Exist", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                update_userName.setText(Session.USER_NAME);
                Update_NIC.setText(Session.NIC);
                Update_email_registration.setText(Session.USER_EMAIL);
                Update_Password_registration.setText(Session.USER_PASSWORD);
                Update_vehicleType.setText(Session.VECHILE_TYPE);
                Update_FuelType.setText(Session.FUEL_TYPE);
//                }
            } catch (Exception exception) {
                Log.e("Error is: ", exception.getMessage());
            }
        }

        //function to happen when clicks update button
        Update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateName = update_userName.getText().toString();
                String updateNIC = Update_NIC.getText().toString();
                String updateEmail = Update_email_registration.getText().toString();
                String updatePassword = Update_Password_registration.getText().toString();
                String updateVehicleType = Update_vehicleType.getText().toString();
                String updateFuelType = Update_FuelType.getText().toString();

                Boolean checkUpdate = DB.updateUserRegistration(updateName, updateNIC, updateEmail, updatePassword, updateVehicleType, updateFuelType);
                if (checkUpdate = true) {
                    Toast.makeText(UpdateProfile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                    //overwrite the session values
                    Session.USER_NAME = update_userName.getText().toString();
                    Session.NIC = Update_NIC.getText().toString();
                    Session.USER_EMAIL = Update_email_registration.getText().toString();
                    Session.USER_PASSWORD = Update_Password_registration.getText().toString();
                    Session.VECHILE_TYPE = Update_vehicleType.getText().toString();
                    Session.FUEL_TYPE = Update_FuelType.getText().toString();

                    //setting the value
                    update_userName.setText(Session.USER_NAME);
                    Update_NIC.setText(Session.NIC);
                    Update_email_registration.setText(Session.USER_EMAIL);
                    Update_Password_registration.setText(Session.USER_PASSWORD);
                    Update_vehicleType.setText(Session.VECHILE_TYPE);
                    Update_FuelType.setText(Session.FUEL_TYPE);

                    Intent intent = new Intent(UpdateProfile.this, NavigationBar.class);
                    startActivity(intent);

                } else
                    Toast.makeText(UpdateProfile.this, "Update is failed", Toast.LENGTH_SHORT).show();
            }
        });

        //Setting up on clickListener
        Update_Cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Update_Cancel_button.setVisibility(View.GONE);
                MoveToProfileActivity();
                System.out.println("clicked");
            }
        });
    }

    public void MoveToProfileActivity() {

        Intent intent = new Intent(this, NavigationBar.class);
        startActivity(intent);

    }
}