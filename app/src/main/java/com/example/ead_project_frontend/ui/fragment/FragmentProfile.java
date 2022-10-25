package com.example.ead_project_frontend.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.config.SysConfig;
import com.example.ead_project_frontend.ui.DbHandler.DBHandler;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.updateProfile.UpdateProfile;

public class FragmentProfile extends Fragment {
    private TextView getUsername, getUserEmail, getNIC, getVehicleType, getFuelType;
    private Button btn_editProfile, btn_deleteProfile;

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

        btn_editProfile = ( Button ) view.findViewById(R.id.btn_editProfile);
        btn_deleteProfile = ( Button ) view.findViewById(R.id.btn_deleteProfile);

        //****************************************************************
        //setting the XML fields
//        String nameText_get = getUsername.getText().toString();
//        String emailText_get = getUserEmail.getText().toString();
//        String nicText_get = getNIC.getText().toString();
//        String VehicleTypeText_get = getVehicleType.getText().toString();
//        String FuelTypeText_get = getFuelType.getText().toString();
//        Cursor res = DB.getUserRegistration(emailText_get, nicText_get);
//
//        if (res.getCount() == 0) {
//            Toast.makeText(getActivity(), "No any Users Exist", Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//
//        }
        //***********************************************************************

        // to move to edit profile page
        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("pressed");
//                Intent intent = new Intent(getActivity(), UpdateProfile.class);
//                getActivity().startActivity(intent);
            }
        });
    }
}
