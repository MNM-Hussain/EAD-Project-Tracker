package com.example.ead_project_frontend.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;
import com.example.ead_project_frontend.ui.registration.Registration;

public class Login extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button  = (Button) findViewById(R.id.btn_Login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
    }

    public void openHomePage(){
        Intent intent = new Intent(this, NavigationBar.class);
        startActivity(intent);
    }

    public void MoveToRegistrationActivity(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}