package com.example.ead_project_frontend.ui.updateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.ui.fragment.FragmentProfile;
import com.example.ead_project_frontend.ui.login.Login;

public class UpdateProfile extends AppCompatActivity {
    Button Update_Cancel_button, Update_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //Initialing with ID
        Update_Cancel_button = findViewById(R.id.Update_Cancel_button);

        //Setting up on clickListener
        Update_Cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update_Cancel_button.setVisibility(View.GONE);
                MoveToProfileActivity();
            }
        });
    }

    public void MoveToProfileActivity () {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}