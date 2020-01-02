package com.example.ilkuygulama.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilkuygulama.Model.Reservation;
import com.example.ilkuygulama.R;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {

    private static List<Reservation> reservationList ;
    private static Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_vehicle,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvPlaka.setText(reservationList.get(position).getPlate());
        holder.tvCurrentCity.setText(reservationList.get(position).getFirstCity());
        holder.tvDestCity.setText(reservationList.get(position).getEndCity());
        holder.tvDateGo.setText(reservationList.get(position).getStartTime());
        holder.tvDateBack.setText(reservationList.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvPlaka;
        public TextView tvCurrentCity;
        public TextView tvDestCity;
        public TextView tvDateGo;
        public TextView tvDateBack;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlaka = itemView.findViewById(R.id.tvPlaka);
            tvCurrentCity = itemView.findViewById(R.id.tvCurrentCity);
            tvDestCity = itemView.findViewById(R.id.tvDestCity);
            tvDateGo = itemView.findViewById(R.id.tvDateGo);
            tvDateBack = itemView.findViewById(R.id.tvDateBack);
        }
    }

}
