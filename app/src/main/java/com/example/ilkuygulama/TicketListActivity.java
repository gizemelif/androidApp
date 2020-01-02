package com.example.ilkuygulama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TicketListActivity extends AppCompatActivity {
    private ListView cityList;
    private ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_ticket);

        init();
        goToCurrentCityActivity();
    }
    private void init() {
        cityList = findViewById(R.id.cityList);
        btnBack = findViewById(R.id.btnBack);
    }
    private void goToCurrentCityActivity() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TicketListActivity.this,CurrentCityActivity.class));
            }
        });
    }
}
