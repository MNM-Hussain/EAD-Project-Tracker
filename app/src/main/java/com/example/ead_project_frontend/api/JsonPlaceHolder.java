package com.example.ead_project_frontend.api;

import com.example.ead_project_frontend.model.FuelStop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {

    @GET("FuelStop")
    Call<List<FuelStop>> getFuelStations();

    @GET("FuelStop/getfuelstation/{id}")
    Call<FuelStop> getFuelStationbyID(@Path("id") String id);

    @GET("FuelStop/getfuelstationforStationOwner/{email}")
    Call<FuelStop> getFuelStationbyemail(@Path("email") String email);

    @POST("FuelStop")
    Call<FuelStop> addNewStation(@Body FuelStop fuelStop);

    @PATCH("FuelStop/updateQueue/{id}")
    Call<FuelStop> patchqueue(@Path("id") String id, @Body String vechiletype);

    @PATCH("FuelStop/updateQueuedecrement/{id}")
    Call<FuelStop> decrementQueue(@Path("id") String id, @Body String vechiletype);

    @PATCH("FuelStop/decreasepetrolfuelquantity/{id}")
    Call<FuelStop> decrementFuel(@Path("id") String id, @Body String fueltype, @Query("fuelQuantity") double amount);

    @PATCH("FuelStop/increasepetrolfuelquantity/{email}")
    Call<FuelStop> incrementFuelQuantity(@Path("email") String email, @Body String fueltype,@Query("fuelQuantity") double amount, @Query("arrivalTime") String arrivalTime);
}
