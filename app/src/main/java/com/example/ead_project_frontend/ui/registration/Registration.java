package com.example.ead_project_frontend.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;

public class Registration extends AppCompatActivity {
    private Button RegisterButton;
    private Button CancelRegisterButton;
    EditText userName, nic, email, password, vehicleType;
    RadioGroup radioGroup;
    RadioButton radioButton;
    //DbHandler class is called here
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initializing the ediText with Id
        userName = findViewById(R.id.input_userName);
        nic = findViewById(R.id.input_NIC);
        email = findViewById(R.id.input_email_registration);
        password = findViewById(R.id.input_Password_registration);
        vehicleType = findViewById(R.id.input_vehicleType);
//        radioGroup = findViewById(R.id.radioGroup_fuelType);
//        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        //Initializing DB
        DB = new DBHandler(this);

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
                String nameText = userName.getText().toString();
                String nicText = nic.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String vehicleTypeText = vehicleType.getText().toString();
//                String fuelTypeText = radioButton.getText().toString();

                // Check the existing user by nic and email
                Boolean checkExistingUser = DB.checkExistingUser(nicText, emailText);

                // validation that to make sure all the fields are filled without any empty fields
                if (nameText.equals("") || nicText.equals("") || emailText.equals("") || passwordText.equals("") || vehicleTypeText.equals(""))
                    Toast.makeText(Registration.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else
                    // if there are no any existing user then the data will be inserted
                    if (checkExistingUser == false) {
                        Boolean InsertRegistrationData = DB.insertUserRegistration(nameText, nicText, emailText, passwordText, vehicleTypeText);
                        if (InsertRegistrationData == true) {
                            Toast.makeText(Registration.this, "You have successfully Registered", Toast.LENGTH_SHORT).show();
                            MoveToHome();
                        } else {
                            Toast.makeText(Registration.this, "Error!! Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Registration.this, "Error!! Already an user exist, please sign-in", Toast.LENGTH_SHORT).show();
                    }

//                Boolean checkInsertData = DB.insertUserRegistration(nameText, nicText, emailText, passwordText, vehicleTypeText);
//                if (checkInsertData == true) {
//                    Toast.makeText(Registration.this, "You have successfully Registered", Toast.LENGTH_SHORT).show();
//                    MoveToHome();
//                } else {
//                    Toast.makeText(Registration.this, "Error!! Registration unsuccessful", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    // Initializing Radio Button
    public void clickRadioButton(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup_fuelType);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(this, radioButton.getText() + " type is selected", Toast.LENGTH_SHORT).show();
    }

    //To Navigate Login Activity
    public void BackToLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    //To Navigate Home Page
    public void MoveToHome(){
        Intent intent = new Intent(this, NavigationBar.class);
        startActivity(intent);
    }
}