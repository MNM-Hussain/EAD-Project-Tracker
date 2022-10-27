package com.example.ead_project_frontend.ui.stationOwnerLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.adminUpdateFuel.AdminUpdateFuel;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.stationOwnerRegistration.StationOwnerRegistration;

public class StationOwnerLogin extends AppCompatActivity {
    private EditText input_Email_AdminLogin, input_Password_AdminLogin;
    private Button btn_Login_AdminLogin;
    private TextView RegisterNow_AdminLogin;

    //DbHandler class is called here
    DBHandler DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner_login);

        // Initializing the ediText and button with Id
        input_Email_AdminLogin = findViewById(R.id.input_Email_AdminLogin);
        input_Password_AdminLogin = findViewById(R.id.input_Password_AdminLogin);
        RegisterNow_AdminLogin = findViewById(R.id.RegisterNow_AdminLogin);

        btn_Login_AdminLogin = findViewById(R.id.btn_Login_AdminLogin);

        //Initializing DB
        DB = new DBHandler(this);

        //SetOnclickListener
        btn_Login_AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AdminLoginEmail_text = input_Email_AdminLogin.getText().toString();
                String AdminLoginPassword = input_Password_AdminLogin.getText().toString();

                // validation that to make sure all the fields are filled without any empty fields
                if (AdminLoginEmail_text.equals("") || AdminLoginPassword.equals(""))
                    Toast.makeText(StationOwnerLogin.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser_credentials = DB.checkStationOwnerCredentials(AdminLoginEmail_text, AdminLoginPassword);
                    if (checkUser_credentials == true) {
                        Toast.makeText(StationOwnerLogin.this, "Login is successful! ", Toast.LENGTH_SHORT).show();
                        openStationOwnerHomePage();
                    } else {
                        Toast.makeText(StationOwnerLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //no-account then move to registration
        RegisterNow_AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StationOwnerLogin.this, StationOwnerRegistration.class);
                startActivity(intent);
            }
        });

    }

    public void openStationOwnerHomePage() {
        Intent intent = new Intent(this, AdminUpdateFuel.class);
        startActivity(intent);
    }
}