package com.example.ead_project_frontend.api;

import com.example.ead_project_frontend.model.FuelStop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface JsonPlaceHolder {

    @GET("FuelStop")
    Call<List<FuelStop>> getFuelStations();

    @GET("FuelStop/getfuelstation/{id}")
    Call<FuelStop> getFuelStationbyID(@Path("id") String id);


    @PATCH("FuelStop/updateQueue/{id}")
    Call<FuelStop> patchqueue(@Path("id") String id, @Body String vechiletype);

    @PATCH("FuelStop/updateQueuedecrement/{id}")
    Call<FuelStop> decrement(@Path("id") String id, @Body String vechiletype);

    @PATCH("FuelStop/{id}")
    Call<FuelStop> patchpetrolAvailablity(@Path("id") String id, @Body FuelStop fuelStop);

    @PATCH("FuelStop/{id}")
    Call<FuelStop> patchDiselAvailability(@Path("id") String id, @Body FuelStop fuelStop);
}
