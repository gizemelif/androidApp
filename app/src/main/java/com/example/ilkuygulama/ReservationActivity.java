package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ilkuygulama.Model.Reservation;
import com.example.ilkuygulama.adapter.ReservationAdapter;

import java.util.List;

public class ReservationActivity extends AppCompatActivity {

    private RecyclerView recCampaign;
    private LinearLayoutManager linearLayoutManager;
    private ReservationAdapter reservationAdapter;

    private List<Reservation> listCampaignPost;
    private Reservation reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        init();

    }

    private void init() {

    }
}
