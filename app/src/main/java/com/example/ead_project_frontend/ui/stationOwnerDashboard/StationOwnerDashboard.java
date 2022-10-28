package com.example.ead_project_frontend.ui.stationOwnerDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.ui.adminUpdateFuel.AdminUpdateFuel;
import com.example.ead_project_frontend.ui.stationOwnerLogin.StationOwnerLogin;
import com.example.ead_project_frontend.ui.stationOwnerProfile.StationOwnerProfile;

public class StationOwnerDashboard extends AppCompatActivity {
    private CardView dashboard_fuel_update, dashboard_profile, dashboard_station, dashboard_logout;
    private TextView nameOfUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_dashboard);

        dashboard_fuel_update = findViewById(R.id.dashboard_fuel_update);
        dashboard_profile = findViewById(R.id.dashboard_profile);
        dashboard_station = findViewById(R.id.dashboard_station);
        dashboard_logout = findViewById(R.id.dashboard_logout);
        nameOfUser = findViewById(R.id.nameOfUser);

        //get the currentUser Name
        nameOfUser.setText("Welcome " + Session.ADMIN_USER_NAME + "!");

        //Move to UpdateFuel page
        dashboard_fuel_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerDashboard.this, AdminUpdateFuel.class);
                startActivity(intent);
            }
        });

        //Move to Profile page
        dashboard_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerDashboard.this, StationOwnerProfile.class);
                startActivity(intent);
            }
        });

        dashboard_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
            }
        });

        //Logout
        dashboard_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerDashboard.this, StationOwnerLogin.class);
                startActivity(intent);
            }
        });
    }
}