package com.example.ead_project_frontend.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.example.ead_project_frontend.R;
import com.example.ead_project_frontend.model.FuelStop;
import com.example.ead_project_frontend.ui.recyclerviewItemClick.RecyclerViewInterface;

import java.util.List;

public class FuelStopRecyclerViewAdapter extends RecyclerView.Adapter<FuelStopRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<FuelStop> fuelStops;

    public FuelStopRecyclerViewAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<FuelStop> fuelStops) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.fuelStops = fuelStops;
    }

    public void setFilteredList(List<FuelStop> filteredList){
        this.fuelStops =filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FuelStopRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_ceypetco, parent, false);
        return new FuelStopRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FuelStopRecyclerViewAdapter.MyViewHolder holder, int position) {
        //get total
        int bike = fuelStops.get(position).getBikeQueue();
        int car = fuelStops.get(position).getCarQueue();
        int bus = fuelStops.get(position).getBusQueue();
        int threewheeeler = fuelStops.get(position).getThreeWheelerQueue();
        int total = bike + car + bus + threewheeeler;
        String totalcount = Integer.toString(total);

        //get provider
        String fuelProvider = fuelStops.get(position).getCompanyName();

        //assign values
        holder.nameOfFuelstation.setText(fuelStops.get(position).getName());
        holder.locationOfFuelStation.setText(fuelStops.get(position).getLocation());
        holder.numberofQueue.setText(totalcount);

        if (fuelProvider.equalsIgnoreCase("ceypetco")) {
            holder.fuelstationlogo.setImageResource(R.drawable.ceypetco);
        } else if (fuelProvider.equalsIgnoreCase("ioc")) {
            holder.fuelstationlogo.setImageResource(R.drawable.ioc);
        } else {
            holder.fuelstationlogo.setImageResource(R.drawable.laughs);
        }


    }

    @Override
    public int getItemCount() {
        if (fuelStops.size() > 0) {
            return fuelStops.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView fuelstationlogo;
        TextView nameOfFuelstation;
        TextView locationOfFuelStation;
        TextView numberofQueue;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            fuelstationlogo = itemView.findViewById(R.id.fuelProviderlogo);
            nameOfFuelstation = itemView.findViewById(R.id.fuelStationName);
            locationOfFuelStation = itemView.findViewById(R.id.fuelStationLocation);
            numberofQueue = itemView.findViewById(R.id.totalNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int postion = getAdapterPosition();

                        if (postion != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(postion);
                        }
                    }
                }
            });

        }
    }
}
