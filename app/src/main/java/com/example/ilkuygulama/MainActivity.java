package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilkuygulama.interfaces.CityStartCallback;
import com.example.ilkuygulama.interfaces.CityEndCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CityStartCallback, CityEndCallback {
    private static List<String> citiesStart;

    private LinearLayout currentLayout,destinationLayout;

    private static TextView txtTo,txtFrom;
    private static String destCity ="";
    private static String currentCity ="";
    private static int startPsitionIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        init();
        setCity();
        goToDestination();
        goToCurrent();

    }

    private void init() {
        currentLayout = findViewById(R.id.currentLayout);
        destinationLayout = findViewById(R.id.destinationLayout);

        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);

        citiesStart = new ArrayList<>();
        citiesStart.add("İSTANBUL");
        citiesStart.add("ANKARA");
        citiesStart.add("İZMİR");
        citiesStart.add("ESKİŞEHİR");
        citiesStart.add("ADANA");
        citiesStart.add("TEKİRDAĞ");
        citiesStart.add("ANTALYA");
        citiesStart.add("MANİSA");
        citiesStart.add("KONYA");
        citiesStart.add("ORDU");

    }

    private void setCity() {
        if(currentCity != null && !currentCity.isEmpty()){
            txtFrom.setText(currentCity);
            if(destCity.isEmpty()){
                txtTo.setText("Nereye ?");
            }
        }
        if(destCity != null && !destCity.isEmpty()){
            txtTo.setText(destCity);
        }
    }

    private void goToCurrent() {
        currentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CurrentCityActivity.class));
            }
        });
    }

    private void goToDestination() {
        destinationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCity.isEmpty()){
                    Toast.makeText(MainActivity.this, "Öncelikle konum şehrinizi seçiniz.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this,DestinationCityActivity.class);
                intent.putExtra("cityStart",currentCity);
                intent.putStringArrayListExtra("cityList", (ArrayList<String>) citiesStart);
                intent.putExtra("position",startPsitionIndex);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStartPosition(String startCity,int position) {
        currentCity = startCity;
        startPsitionIndex = position;
        destCity = "";
    }

    @Override
    public void onEndPosition(String endCity) {
        destCity = endCity;
    }
}
