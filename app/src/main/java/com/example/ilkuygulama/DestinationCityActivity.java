package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ilkuygulama.interfaces.CityEndCallback;
import com.example.ilkuygulama.interfaces.DestinationCitySetCallback;

import java.util.ArrayList;
import java.util.List;

public class DestinationCityActivity extends AppCompatActivity implements DestinationCitySetCallback {

    public static List<String> citiesEnd;
    public static ListView cityList;
    private ImageButton btnBack;
    private CityEndCallback cityEndCallback;

    private static int startPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_city);

        init();
        selectCity();
        goToMainActivity();
    }

    private void goToMainActivity() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DestinationCityActivity.this, MainActivity.class));
            }
        });
    }

    private void selectCity() {
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityEnd = parent.getItemAtPosition(position).toString();
                cityEndCallback.onEndPosition(cityEnd);
                startActivity(new Intent(DestinationCityActivity.this, MainActivity.class));
            }
        });
    }

    private void setCityToListView(Context context) {
        if (startPosition != -1) {
            citiesEnd.remove(startPosition);
        }
        ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>
                (context, android.R.layout.simple_list_item_1, android.R.id.text1, citiesEnd);
        cityList.setAdapter(veriAdaptoru);
    }

    private void init() {
        cityList = findViewById(R.id.cityList);
        btnBack = findViewById(R.id.btnBack);
        cityEndCallback = new MainActivity();

        citiesEnd = new ArrayList<>();
    }

    @Override
    public void onSetDestinationCity(String cityStart, int position, List<String> citiesStart,Context context) {
        startPosition = position;
        citiesEnd = citiesStart;
        setCityToListView(context);
    }
}
