package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ilkuygulama.interfaces.CityStartCallback;

public class CurrentCityActivity extends AppCompatActivity {
    private String[] citiesStart={"İSTANBUL","ANKARA","İZMİR","ESKİŞEHİR","ADANA","TEKİRDAĞ","ANTALYA","MANİSA","KONYA","ORDU"};
    private ListView cityList;
    private ImageButton btnBack;
    private CityStartCallback cityCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_city);

        init();
        setCityToListView();
        selectCity();
        goToMainActivity();
    }

    private void goToMainActivity() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CurrentCityActivity.this,MainActivity.class));
            }
        });
    }

    private void selectCity() {
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityStart = parent.getItemAtPosition(position).toString();
                cityCallback.onStartPosition(cityStart,position);
                Intent intent= new Intent(CurrentCityActivity.this,MainActivity.class);
                intent.putExtra("cityStart",cityStart);
                startActivity(intent);
            }
        });
    }

    private void setCityToListView() {
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, citiesStart);

        //(C) adımı
        cityList.setAdapter(veriAdaptoru);
    }

    private void init() {
        cityList = findViewById(R.id.cityList);
        btnBack = findViewById(R.id.btnBack);
        cityCallback = new MainActivity();
    }
}
