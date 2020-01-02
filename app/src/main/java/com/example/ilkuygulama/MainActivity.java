package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilkuygulama.interfaces.CityStartCallback;
import com.example.ilkuygulama.interfaces.CityEndCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CityStartCallback, CityEndCallback {
    private static List<String> citiesStart;

    private LinearLayout currentLayout,destinationLayout,dateReturningLayout,dateStartingLayout;

    private static TextView txtTo,txtFrom,txtReturningDate,txtStartingDate;
    
    private Button btnSave;

    private static String destCity ="";
    private static String currentCity ="";
    private static int startPsitionIndex = -1;

    private static String startingTravelDate = "";
    private static String returningTravelDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        init();
        setCity();
        goToDestination();
        goToCurrent();
        startingDate();
        returningDate();
        saveReservation();
    }

    private void saveReservation() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startingTravelDate.isEmpty() || returningTravelDate.isEmpty() || currentCity.isEmpty() || destCity.isEmpty()){
                    Toast.makeText(MainActivity.this, "Gerekli bilgileri giriniz...", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void returningDate() {
        dateReturningLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startingTravelDate.isEmpty()){
                    Toast.makeText(MainActivity.this, "Başlangıç tarihini seçiniz...", Toast.LENGTH_SHORT).show();
                    return;
                }
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String yearOf = Integer.toString(year);
                        String strMonth = Integer.toString(month+1);
                        String mountOf = month > 8 ? strMonth : "0"+ strMonth;
                        String day = dayOfMonth > 9 ? Integer.toString(dayOfMonth) : "0"+ dayOfMonth;
                        returningTravelDate = yearOf + "/" + mountOf + "/" + day;
                        txtReturningDate.setText(returningTravelDate);
                    }
                }, yil, ay, gun);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date date = null;
                try {
                    date = sdf.parse(startingTravelDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long millis = date.getTime();
                dpd.getDatePicker().setMinDate(millis);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();
            }
        });
    }

    private void startingDate() {
        dateStartingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String yearOf = Integer.toString(year);
                        String strMonth = Integer.toString(month+1);
                        String mountOf = month > 8 ? strMonth : "0"+ strMonth;
                        String day = dayOfMonth > 9 ? Integer.toString(dayOfMonth) : "0"+ dayOfMonth;
                        startingTravelDate = yearOf + "/" + mountOf + "/" + day;
                        txtStartingDate.setText(startingTravelDate);
                    }
                }, yil, ay, gun);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();
            }
        });
    }

    private void init() {
        
        btnSave = findViewById(R.id.btnSave);
        
        currentLayout = findViewById(R.id.currentLayout);
        destinationLayout = findViewById(R.id.destinationLayout);
        dateReturningLayout = findViewById(R.id.dateReturningLayout);
        dateStartingLayout = findViewById(R.id.dateStartingLayout);

        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
        txtReturningDate = findViewById(R.id.txtReturningDate);
        txtStartingDate = findViewById(R.id.txtStartingDate);

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
