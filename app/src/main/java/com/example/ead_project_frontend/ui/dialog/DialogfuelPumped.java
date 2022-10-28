package com.example.ead_project_frontend.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;

public class DialogfuelPumped extends Activity {
EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.dialog_alert_pumped);
        editText= findViewById(R.id.input_numberOfLitres);
        System.out.println(editText.getText().toString());
        Session.FUEL_AMOUNT= new Double(editText.getText().toString());
        System.out.println(Session.FUEL_AMOUNT);


    }
}
