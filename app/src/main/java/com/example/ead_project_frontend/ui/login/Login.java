package com.example.ead_project_frontend.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;
import com.example.ead_project_frontend.ui.registration.Registration;

public class Login extends AppCompatActivity {
    private Button button;
    private EditText userEmail, password;

    //DbHandler class is called here
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initializing the ediText and button with Id
        userEmail = findViewById(R.id.input_Email);
        password = findViewById(R.id.input_Password);

        //Initializing DB
        DB = new DBHandler(this);

        button = (Button) findViewById(R.id.btn_Login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_Text = userEmail.getText().toString();
                String password_Text = password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                String password_Pattern = "^(?=.*[0-9])(?=.*[A-Z])";

                //****validations for email and password********
                //validation for email
                if (TextUtils.isEmpty(email_Text)) {
                    userEmail.setError("please enter Email!!!");
                    return;
                } else if (email_Text.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    userEmail.setError("please enter Valid Email address!!!");
                }

                //validation for password
                if (TextUtils.isEmpty(password_Text)) {
                    password.setError("please enter Password!!!");
                    return;
                }
//                else if (password_Text.matches(password_Pattern)) {
//                    Toast.makeText(getApplicationContext(), "valid Password", Toast.LENGTH_SHORT).show();
//                } else {
//                    password.setError("please enter Strong Password!!!");
//                }

                // validation that to make sure all the fields are filled without any empty fields
                if (email_Text.equals("") || password_Text.equals(""))
                    Toast.makeText(Login.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser_credentials = DB.checkUserCredentials(email_Text, password_Text);
                    if (checkUser_credentials == true) {
                        Toast.makeText(Login.this, "Login is successful! ", Toast.LENGTH_SHORT).show();
                        openHomePage();
                    } else {
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //Move to to Homepage
    public void openHomePage() {
        Intent intent = new Intent(this, NavigationBar.class);
        startActivity(intent);
    }

    //Move to Registration Class
    public void MoveToRegistrationActivity(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}