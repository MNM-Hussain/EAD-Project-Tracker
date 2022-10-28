package com.example.ead_project_frontend.ui.stationOwnerProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.stationOwnerDashboard.StationOwnerDashboard;
import com.example.ead_project_frontend.ui.stationOwnerLogin.StationOwnerLogin;

public class StationOwnerProfile extends AppCompatActivity {
    private TextView getUsername_adminProfile, getUserEmail_adminProfile, getStationName_adminProfile,getStationBranch_adminProfile, getContactNumber;
    private TextView back_arrow_AdminProfile;
    private Button btn_editProfile_adminProfile, btn_deleteProfile_adminProfile;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_profile);

        //Initializing with respective ID's
        getUsername_adminProfile = findViewById(R.id.getUsername_adminProfile);
        getUserEmail_adminProfile = findViewById(R.id.getUserEmail_adminProfile);
        getStationName_adminProfile = findViewById(R.id.getStationName_adminProfile);
        getStationBranch_adminProfile = findViewById(R.id.getStationBranch_Text_adminProfile);
        getContactNumber = findViewById(R.id.getContactNumber);
        back_arrow_AdminProfile = findViewById(R.id.back_arrow_AdminProfile);

        //Initializing DB
        DB = new DBHandler(this);

        //Initializing Buttons
        btn_deleteProfile_adminProfile = findViewById(R.id.btn_deleteProfile_adminProfile);
        btn_editProfile_adminProfile = findViewById(R.id.btn_editProfile_adminProfile);

        //************************Get the data of the login users to textView UI****************************************
        Cursor cursor = DB.getAdminRegistration();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No any Users Exist", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                getUsername_adminProfile.setText(Session.ADMIN_USER_NAME);
                getUserEmail_adminProfile.setText(Session.ADMIN_USER_EMAIL);
                getStationName_adminProfile.setText(Session.ADMIN_USER_STATION_NAME);
                getStationBranch_adminProfile.setText(Session.ADMIN_USER_STATION_BRANCH);
                getContactNumber.setText(Session.ADMIN_USER_CONTACT);
//                }
            } catch (Exception exception) {
                Log.e("Error is: ", exception.getMessage());
            }
        }

        btn_editProfile_adminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_deleteProfile_adminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //To navigate previous page
        back_arrow_AdminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerProfile.this, StationOwnerDashboard.class);
                startActivity(intent);
            }
        });
    }
}