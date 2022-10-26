package com.example.ead_project_frontend.api;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ead_project_frontend.config.SysConfig;
import com.example.ead_project_frontend.model.FuelStop;
import com.example.ead_project_frontend.ui.adapter.FuelStopRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {


public static void getstattioninformationByID(String id){
    //handling API call
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SysConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
    Call<FuelStop> call =jsonPlaceHolder.getFuelStationbyID(id);
    call.enqueue(new Callback<FuelStop>() {
        @Override
        public void onResponse(Call<FuelStop> call, Response<FuelStop> response) {
            if(!response.isSuccessful()){
                System.out.println("NOT SUCUSSFUL");
                SysConfig.API_MESSAGE="NOT SUCUSSFUL RESPONSE";
            }
           FuelStop fuelStop =response.body();
            System.out.println(fuelStop);

            System.out.println(fuelStop.getCompanyName());

            System.out.println(fuelStop.getId() );
            System.out.println("INSIDE GET ONE"+fuelStop.getName() );
            System.out.println(fuelStop.getLocation() );
            System.out.println(fuelStop.getFuelDiselCapacity() );
            System.out.println(fuelStop.getFuelPetrolCapacity() );
            System.out.println(fuelStop.getBikeQueue() );
            System.out.println(fuelStop.getCarQueue() );
            System.out.println(fuelStop.getBusQueue() );
            System.out.println(fuelStop.getThreeWheelerQueue() );

           SysConfig.fuelStop = new FuelStop(fuelStop.getId(),fuelStop.getName(),fuelStop.getLocation(), fuelStop.getCompanyName(), fuelStop.getFuelDiselCapacity(),fuelStop.getFuelPetrolCapacity(),fuelStop.getBikeQueue() ,fuelStop.getCarQueue(),fuelStop.getBusQueue(),fuelStop.getThreeWheelerQueue()  );
            System.out.println("CALLING CLASS ASSIGNED "+SysConfig.fuelStop.getLocation() );



        }



        @Override
        public void onFailure(Call<FuelStop> call, Throwable t) {

//
        }
    });


}

    public static void incremenentQueue(String id,String vechiletype){
        {
            //handling API call
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SysConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<FuelStop> call =jsonPlaceHolder.patchqueue(id,vechiletype.toLowerCase());
            call.enqueue(new Callback<FuelStop>() {
                @Override
                public void onResponse(Call<FuelStop> call, Response<FuelStop> response) {
                    if(!response.isSuccessful()){
                        System.out.println("NOT SUCUSSFUL");
                        SysConfig.API_MESSAGE="NOT SUCUSSFUL RESPONSE";
                    }
                    System.out.println(vechiletype+"incremented");



                }



                @Override
                public void onFailure(Call<FuelStop> call, Throwable t) {
                    System.out.println(t.getMessage());
//
                }
            });


        }
    }

    public static void decrementQueue(String id,String vechiletype){
        {
            //handling API call
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SysConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<FuelStop> call =jsonPlaceHolder.decrement(id,vechiletype.toLowerCase());
            call.enqueue(new Callback<FuelStop>() {
                @Override
                public void onResponse(Call<FuelStop> call, Response<FuelStop> response) {
                    if(!response.isSuccessful()){
                        System.out.println("NOT SUCUSSFUL");
                        SysConfig.API_MESSAGE="NOT SUCUSSFUL RESPONSE";
                    }
                    System.out.println(vechiletype+"incremented");



                }



                @Override
                public void onFailure(Call<FuelStop> call, Throwable t) {
                    System.out.println(t.getMessage());
//
                }
            });


        }
    }


    public static void decrementfuel(String id,String fuelType,double amount){
        {
            //handling API call
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SysConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<FuelStop> call =jsonPlaceHolder.decrementFuel(id,fuelType,amount);
            call.enqueue(new Callback<FuelStop>() {
                @Override
                public void onResponse(Call<FuelStop> call, Response<FuelStop> response) {
                    if(!response.isSuccessful()){
                        System.out.println("NOT SUCUSSFUL");
                        SysConfig.API_MESSAGE="NOT SUCUSSFUL RESPONSE";
                    }
                    System.out.println(fuelType+"decremented");



                }



                @Override
                public void onFailure(Call<FuelStop> call, Throwable t) {
                    System.out.println(t.getMessage());
//
                }
            });


        }
    }
}
