package com.example.ead_project_frontend.ui.stationOwnerProfile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.example.ead_project_frontend.ui.stationOwnerUpdateProfile.StationOwnerUpdateProfile;

public class StationOwnerProfile extends AppCompatActivity {
    private TextView getUsername_adminProfile, getUserEmail_adminProfile, getStationName_adminProfile, getStationBranch_adminProfile, getContactNumber;
    private TextView back_arrow_AdminProfile;
    private Button btn_editProfile_adminProfile, btn_deleteProfile_adminProfile;
    DBHandler DB;
    AlertDialog.Builder builder;

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

        //initializing alertBox
        builder = new AlertDialog.Builder(this);

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
                Intent intent = new Intent(StationOwnerProfile.this, StationOwnerUpdateProfile.class);
                startActivity(intent);
            }
        });

        btn_deleteProfile_adminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Confirmation Alert")
                        .setMessage("Can you confirm that you wants to delete your Admin Profile?")
                        .setCancelable(true)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                finish();

                                //Implementation to delete the Admin-User
                                String adminEmailText = getUserEmail_adminProfile.getText().toString();

                                Boolean checkDelete = DB.deleteAdminRegistration(adminEmailText);

                                if (checkDelete == true) {
                                    Intent intent = new Intent(StationOwnerProfile.this, StationOwnerLogin.class);
                                    startActivity(intent);
                                    Toast.makeText(StationOwnerProfile.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(StationOwnerProfile.this, "Delete failed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
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