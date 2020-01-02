package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ilkuygulama.interfaces.CityEndCallback;

import java.util.List;

public class DestinationCityActivity extends AppCompatActivity {

    public static ListView cityListView;
    private ImageButton btnBack;
    public CityEndCallback cityEndCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_city);

        init();
        setCityList();
        selectCity();
        goToMainActivity();
    }

    private void init() {
        cityListView = findViewById(R.id.cityList);
        btnBack = findViewById(R.id.btnBack);
        cityEndCallback = new MainActivity();
    }

    private void setCityList() {
        Intent intent = getIntent();
        String cityStart = intent.getStringExtra("cityStart");
        List<String> cityList = intent.getStringArrayListExtra("cityList");
        int position = intent.getIntExtra("position",-1);
        if(position != -1){
            cityList.remove(position);
        }
        ArrayAdapter<String> dataAdaptor = new ArrayAdapter<String>
                (DestinationCityActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, cityList);
        cityListView.setAdapter(dataAdaptor);
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
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityEnd = parent.getItemAtPosition(position).toString();
                cityEndCallback.onEndPosition(cityEnd);
                startActivity(new Intent(DestinationCityActivity.this, MainActivity.class));
            }
        });
    }

}
