package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.ilkuygulama.Model.Customer;
import com.example.ilkuygulama.Model.DatePickerFragment;
import com.example.ilkuygulama.dao_entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private String[] citiesStart={"İSTANBUL","ANKARA","İZMİR","ESKİŞEHİR","ADANA","TEKİRDAĞ","ANTALYA","MANİSA","KONYA","ORDU"};
    private String[] citiesEnd={"İSTANBUL","ANKARA","İZMİR","ESKİŞEHİR","ADANA","TEKİRDAĞ","ANTALYA","MANİSA","KONYA","ORDU"};

    //Spinner'ları ve Adapter'lerini tanımlıyoruz.
    private Spinner spinner1;
    private Spinner spinner2;
    private ArrayAdapter<String> dataAdapterForCities1;
    private ArrayAdapter<String> dataAdapterForCities2;

    @Override // Bu metod uygulama açıldığında çalıştırılan metod.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        //xml kısmında hazırladığımğız spinnerları burda tanımladıklarımızla eşleştiriyoruz.
        spinner1 = (Spinner) findViewById(R.id.spinnerCity1);
        spinner2 = (Spinner) findViewById(R.id.spinnerCity2);

        //Spinner'lar için adapterleri hazırlıyoruz.
        dataAdapterForCities1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesStart);
        dataAdapterForCities2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,citiesEnd);

        //Listelenecek verilerin görünümünü belirliyoruz.
        dataAdapterForCities1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterForCities2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Hazırladğımız Adapter'leri Spinner'lara ekliyoruz.
        spinner1.setAdapter(dataAdapterForCities1);
        spinner2.setAdapter(dataAdapterForCities2);

        // Listelerden bir eleman seçildiğinde yapılacakları tanımlıyoruz.
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Hangi il seçilmişse onun ilçeleri adapter'e ekleniyor.
                for(int i=0; i < citiesStart.length; i++)
                if(parent.getSelectedItem().toString().equals(citiesStart[i]))
                    dataAdapterForCities1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,citiesStart);
                else if(parent.getSelectedItem().toString().equals(citiesEnd[1]))
                    dataAdapterForCities2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,ilceler1);

                dataAdapterForCities2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(dataAdapterForCities1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerIlceler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Seçilen il ve ilçeyi ekranda gösteriyoruz.
                Toast.makeText(getBaseContext(), ""+spinnerIller.getSelectedItem().toString()+"n"+parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
   public void showDatePickerDialog(View v){
       DialogFragment fragment = new DatePickerFragment();
       fragment.show(getSupportFragmentManager(), "datePicker");
   }
}
