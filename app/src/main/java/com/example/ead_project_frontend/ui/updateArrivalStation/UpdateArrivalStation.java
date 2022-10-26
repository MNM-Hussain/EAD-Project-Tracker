package com.example.ead_project_frontend.ui.updateArrivalStation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.api.ApiCall;
import com.example.ead_project_frontend.api.JsonPlaceHolder;
import com.example.ead_project_frontend.config.Session;
import com.example.ead_project_frontend.config.SysConfig;
import com.example.ead_project_frontend.model.FuelStop;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;
import com.example.ead_project_frontend.ui.updatePumpedFuelStatus.UpdatePumpedFuelStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateArrivalStation extends AppCompatActivity {
    private Button btn_arrivedStation;
    private Dialog dialog;
    private TextView back_arrow_arrival, stationBranch, queue, availablePetrol, availableDiesel, name;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_arrival_station);
        System.out.println("Inside onCreate method");
        savedInstanceState = getIntent().getExtras();

        // Initializing with Id
        back_arrow_arrival = findViewById(R.id.back_arrow_arrival);
        btn_arrivedStation = findViewById(R.id.btn_arrivedStation);
        stationBranch = findViewById(R.id.getStationBranch);
        queue = findViewById(R.id.getCurrentQueue);
        availablePetrol = findViewById(R.id.getPetrolAvailability);
        availableDiesel = findViewById(R.id.getDieselAvailability);
        name = findViewById(R.id.getStationName);


//Extract the data…

        String ID = savedInstanceState.getString("ID");


        //handling API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SysConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<FuelStop> call = jsonPlaceHolder.getFuelStationbyID(ID);
        call.enqueue(new Callback<FuelStop>() {
            @Override
            public void onResponse(Call<FuelStop> call, Response<FuelStop> response) {
                if (!response.isSuccessful()) {
                    System.out.println("NOT SUCUSSFUL");
                    SysConfig.API_MESSAGE = "NOT SUCUSSFUL RESPONSE";
                }
                FuelStop fuelStop = response.body();
                System.out.println(fuelStop);

                System.out.println(fuelStop.getCompanyName());

                System.out.println(fuelStop.getId());
                System.out.println("INSIDE GET ONE" + fuelStop.getName());
                System.out.println(fuelStop.getLocation());
                System.out.println(fuelStop.getFuelDiselCapacity());
                System.out.println(fuelStop.getFuelPetrolCapacity());
                System.out.println(fuelStop.getBikeQueue());
                System.out.println(fuelStop.getCarQueue());
                System.out.println(fuelStop.getBusQueue());
                System.out.println(fuelStop.getThreeWheelerQueue());

                //get total
                int bike = fuelStop.getBikeQueue();
                int car = fuelStop.getCarQueue();
                int bus = fuelStop.getBusQueue();
                int threewheeeler = fuelStop.getThreeWheelerQueue();
                int total = bike + car + bus + threewheeeler;
                String totalcount = Integer.toString(total);

                SysConfig.fuelStop = new FuelStop(fuelStop.getId(), fuelStop.getName(), fuelStop.getLocation(), fuelStop.getCompanyName(), fuelStop.getFuelDiselCapacity(), fuelStop.getFuelPetrolCapacity(), fuelStop.getBikeQueue(), fuelStop.getCarQueue(), fuelStop.getBusQueue(), fuelStop.getThreeWheelerQueue());
                System.out.println("CALLING CLASS ASSIGNED " + SysConfig.fuelStop.getLocation());
                id = fuelStop.getId();
                stationBranch.setText(fuelStop.getLocation());
                queue.setText(totalcount);
                name.setText(fuelStop.getName());
                if (fuelStop.getFuelPetrolCapacity() == 0) {
                    availablePetrol.setText("FINISHED");
                } else {

                    availablePetrol.setText(Double.toString(fuelStop.getFuelPetrolCapacity()) + "L");
                }

                if (fuelStop.getFuelDiselCapacity() == 0) {
                    availableDiesel.setText("FINISHED");
                } else {

                    availableDiesel.setText(Double.toString(fuelStop.getFuelDiselCapacity()) + "L");
                }


            }


            @Override
            public void onFailure(Call<FuelStop> call, Throwable t) {

//
            }
        });


        // send to previous page
        back_arrow_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateArrivalStation.this, NavigationBar.class);
                startActivity(intent);
            }
        });

        //To get the popup
        btn_arrivedStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

            }
        });

        //Created the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_alert_arrival_station);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_flow_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogPop_Animation; //Setting the animations to dialog

        //Initializing popup buttons
        Button btn_arrived = dialog.findViewById(R.id.btn_arrived);
        Button btn_notArrived = dialog.findViewById(R.id.btn_notArrived);


        btn_arrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateArrivalStation.this, "your arrival is confirmed", Toast.LENGTH_SHORT).show();
                //call api


                dialog.dismiss();

                ApiCall.incremenentQueue(ID, "car");

                //send to pumped status page from popup
                Intent intent = new Intent(UpdateArrivalStation.this, UpdatePumpedFuelStatus.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", ID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_notArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UpdateArrivalStation.this, "not confirmed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}