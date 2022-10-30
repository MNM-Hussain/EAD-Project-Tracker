package com.example.ead_project_frontend.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.config.SysConfig;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.stationOwnerLogin.StationOwnerLogin;
import com.example.ead_project_frontend.ui.stationOwnerProfile.StationOwnerProfile;
import com.example.ead_project_frontend.ui.updateProfile.UpdateProfile;

public class FragmentProfile extends Fragment {
    private TextView getUsername, getUserEmail, getNIC, getVehicleType, getFuelType;
    private Button btn_editProfile, btn_deleteProfile;
    AlertDialog.Builder builder;
    DBHandler DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initializing with respective ID's
        getUsername = view.findViewById(R.id.getUsername);
        getUserEmail = view.findViewById(R.id.getUserEmail);
        getNIC = view.findViewById(R.id.getNIC);
        getVehicleType = view.findViewById(R.id.getVehicleType);
        getFuelType = view.findViewById(R.id.getFuelType);

        //Initializing DB
        DB = new DBHandler(getActivity());

        //initializing alertBox
        builder = new AlertDialog.Builder(getActivity());

        //Initializing Button
        btn_editProfile = (Button) view.findViewById(R.id.btn_editProfile);
        btn_deleteProfile = (Button) view.findViewById(R.id.btn_deleteProfile);

        //************************Get the data of the login users to textView UI****************************************
        Cursor cursor = DB.getUserRegistration();

        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No any Users Exist", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                getUsername.setText(Session.USER_NAME);
                getNIC.setText(Session.NIC);
                getUserEmail.setText(Session.USER_EMAIL);
                getVehicleType.setText(Session.VECHILE_TYPE);
                getFuelType.setText(Session.FUEL_TYPE);
//                }
            } catch (Exception exception) {
                Log.e("Error is: ", exception.getMessage());
            }
        }

        //*****************To move to update profile******************************************************
        // to move to edit profile page
        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("pressed");
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
            }
        });

        //***********popup to confirm delete the Profile*********************
        btn_deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Confirmation Alert")
                        .setMessage("Can you confirm that you wants to delete your Profile?")
                        .setCancelable(true)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                finish();
                                //Implementation to delete the Admin-User
                                String userNICText = getNIC.getText().toString();

                                Boolean checkDeleteUser = DB.deleteUserRegistration(userNICText);

                                if (checkDeleteUser == true) {
                                    Intent intent = new Intent(getActivity(), Login.class);
                                    startActivity(intent);

                                    //Confirmation message
                                    Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getActivity(), "Delete failed", Toast.LENGTH_SHORT).show();

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
    }
}
