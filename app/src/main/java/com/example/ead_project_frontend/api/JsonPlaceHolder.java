package com.example.ead_project_frontend.api;

import com.example.ead_project_frontend.model.FuelStop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {

    @GET("FuelStop")
    Call<List<FuelStop>> getFuelStations();

    @GET("FuelStop/{id}")
    Call<FuelStop> getFuelStationbyID();
}
