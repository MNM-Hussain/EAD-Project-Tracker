package com.example.ead_project_frontend.ui.welcomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.stationOwnerLogin.StationOwnerLogin;

public class WelcomePage extends AppCompatActivity {
    private Button stationOwner, User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //Initializing with ID
        stationOwner = findViewById(R.id.btn_login_station_owner);
        User = findViewById(R.id.btn_login_normal_user);

        //Button
        stationOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePage.this, StationOwnerLogin.class);
                startActivity(intent);
            }
        });

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePage.this, Login.class);
                startActivity(intent);
            }
        });

    }
}