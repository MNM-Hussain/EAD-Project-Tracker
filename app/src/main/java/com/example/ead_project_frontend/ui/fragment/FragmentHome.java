package com.example.ead_project_frontend.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.api.JsonPlaceHolder;
import com.example.ead_project_frontend.config.SysConfig;
import com.example.ead_project_frontend.model.FuelStop;
import com.example.ead_project_frontend.ui.adapter.FuelStopRecyclerViewAdapter;
import com.example.ead_project_frontend.ui.login.Login;
import com.example.ead_project_frontend.ui.navigation.NavigationBar;
import com.example.ead_project_frontend.ui.recyclerviewItemClick.RecyclerViewInterface;
import com.example.ead_project_frontend.ui.updateArrivalStation.UpdateArrivalStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentHome extends Fragment implements RecyclerViewInterface {

    RecyclerView recyclerView;
    List<FuelStop> fuelStops;
    SearchView searchView;
    RecyclerViewInterface recyclerViewInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        recyclerViewInterface = this;
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fuelStopRecyclerView);
        searchView = view.findViewById(R.id.search_bar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterFuelStation(newText);
                return true;
            }
        });


        //handling API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SysConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<FuelStop>> call = jsonPlaceHolder.getFuelStations();
        call.enqueue(new Callback<List<FuelStop>>() {
            @Override
            public void onResponse(Call<List<FuelStop>> call, Response<List<FuelStop>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "failed response", Toast.LENGTH_SHORT).show();
                }
                fuelStops = response.body();
                System.out.println(fuelStops);
                System.out.println(fuelStops.size());
                System.out.println(fuelStops.get(1).getCompanyName());
                System.out.println(fuelStops.get(1).getId());
                System.out.println(fuelStops.get(1).getName());
                System.out.println(fuelStops.get(1).getLocation());
                System.out.println(fuelStops.get(1).getFuelDiselCapacity());
                System.out.println(fuelStops.get(1).getFuelPetrolCapacity());
                System.out.println(fuelStops.get(1).getBikeQueue());
                System.out.println(fuelStops.get(1).getCarQueue());
                System.out.println(fuelStops.get(1).getBusQueue());
                System.out.println(fuelStops.get(1).getThreeWheelerQueue());

                //attach adapter


                if (fuelStops != null && fuelStops.size() > 0) {
                    FuelStopRecyclerViewAdapter fuelStopRecyclerViewAdapter = new FuelStopRecyclerViewAdapter(recyclerViewInterface, getActivity(), fuelStops);
                    recyclerView.setAdapter(fuelStopRecyclerViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                } else {
                    System.out.println("cant load fuel stops");
                }

            }

            @Override
            public void onFailure(Call<List<FuelStop>> call, Throwable t) {

//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println(t.getMessage());
            }
        });

        //call up adapter


    }

    private void filterFuelStation(String newText) {
        List<FuelStop> fuelStopslist = new ArrayList<>();
        for (FuelStop fuel : fuelStops) {
            if (fuel.getLocation().toLowerCase().contains(newText.toLowerCase())) {
                fuelStopslist.add(fuel);
            }
        }

        if (fuelStopslist.isEmpty()) {
            Toast.makeText(getActivity(), "No item found", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onItemClick(int postion) {

        System.out.println(fuelStops.get(postion).getCompanyName());
        System.out.println(fuelStops.get(postion).getId());
        System.out.println(fuelStops.get(postion).getName());
        System.out.println(fuelStops.get(postion).getLocation());
        System.out.println(fuelStops.get(postion).getFuelDiselCapacity());
        System.out.println(fuelStops.get(postion).getFuelPetrolCapacity());
        System.out.println(fuelStops.get(postion).getBikeQueue());
        System.out.println(fuelStops.get(postion).getCarQueue());
        System.out.println(fuelStops.get(postion).getBusQueue());

        double dcapacity = fuelStops.get(postion).getFuelDiselCapacity();
        double pcapasity = fuelStops.get(postion).getFuelPetrolCapacity();
        String dcap = Double.toString(dcapacity);
        String pcap = Double.toString(pcapasity);
        Intent intent = new Intent(getActivity(), UpdateArrivalStation.class);
        Bundle bundle = new Bundle();
        bundle.putString("ID", fuelStops.get(postion).getId());
        bundle.putString("COMPANY_NAME", fuelStops.get(postion).getCompanyName());


        bundle.putString("BRANCH", fuelStops.get(postion).getLocation());
        bundle.putString("DISEL_CAPASITY", dcap);
        bundle.putString("PETROL_CAPASITY", pcap);
        int bike = fuelStops.get(postion).getBikeQueue();
        int car = fuelStops.get(postion).getCarQueue();
        int bus = fuelStops.get(postion).getBusQueue();
        int threewheeeler = fuelStops.get(postion).getThreeWheelerQueue();
        int total = bike + car + bus + threewheeeler;
        String totalcount = Integer.toString(total);
        bundle.putString("TOTAL", totalcount);
        intent.putExtras(bundle);
        startActivity(intent);


    }
}
