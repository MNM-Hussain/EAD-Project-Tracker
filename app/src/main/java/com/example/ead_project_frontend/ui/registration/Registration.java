package com.example.ead_project_frontend.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;

public class Registration extends AppCompatActivity {
    private Button RegisterButton;
    private Button CancelRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // If user press cancel button need to navigate to loginPage
        CancelRegisterButton  = (Button) findViewById(R.id.Register_Cancel_button);
        CancelRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackToLogin();
            }
        });

        // If user press register button need to navigate to homePage
        RegisterButton = (Button) findViewById(R.id.Register_button);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoveToHome();
            }
        });
    }

    public void clickRadioButton(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup_fuelType);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(this, radioButton.getText() + " type is selected", Toast.LENGTH_SHORT).show();
    }

    public void BackToLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void MoveToHome(){
        Intent intent = new Intent(this, NavigationBar.class);
        startActivity(intent);
    }
}