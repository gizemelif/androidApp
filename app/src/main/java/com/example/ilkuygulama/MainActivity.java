package com.example.ilkuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ilkuygulama.AppDAO.CustomerDAO;
import com.example.ilkuygulama.Model.Customer;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private EditText etUsername, etAd, etSoyad, etEmail, etCepTel, etSifre;
    private Button btnKaydet;

   @Override
    public void onCreate(Bundle savedIntstanceState){
       super.onCreate(savedIntstanceState);
       setContentView(R.layout.sign_up);
       // onCreate içerisini mümkün olduğunca boş bırakmak lazım
       init();
       saveUser();
       Listele();
   }

    private void init() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etAd = (EditText) findViewById(R.id.etAd);
        etSoyad = (EditText) findViewById(R.id.etSoyad);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCepTel = (EditText) findViewById(R.id.etTel);
        etSifre = (EditText) findViewById(R.id.etSifre);
        btnKaydet = (Button) findViewById(R.id.btnKaydet);
    }

    private void saveUser() {
        btnKaydet.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CustomerDAO vt = new CustomerDAO(MainActivity.this);

                String username = etUsername.getText().toString();
                String ad = etAd.getText().toString();
                String soyad = etSoyad.getText().toString();
                String email = etEmail.getText().toString();
                String cepTel = PhoneNumberUtils.normalizeNumber(etCepTel.getText().toString());
                String sifre = etSifre.getText().toString();
                if( etUsername.length() != 0 || etAd.length() !=0 || etSoyad.length() != 0 || etEmail.length() != 0 || etCepTel.length() != 0 || etSifre.length() != 0 ){
                    vt.veriEkle(username,ad,soyad,email,cepTel,sifre);
                    etUsername.setText("");
                    etAd.setText("");
                    etSoyad.setText("");
                    etEmail.setText("");
                    etCepTel.setText("");
                    etSifre.setText("");
                }else{
                    showToast("Lütfen tüm alanları doldurunuz !");
                }
                if(btnKaydet.isPressed()){
                    showToast("Doğrulama emailindeki linke tıklayarak işlemi tamalayınız.");
                }


            }
        });
    }

    public void Listele(){
       CustomerDAO vt = new CustomerDAO(MainActivity.this);
       List<Customer> liste = vt.veriListele();
       // liste.get(0) şeklinde 0 yazan yere index numrasını yazarak ilgili veriye ulaşabilirsin.
       String a = "";
       /*ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1,liste);
       listView.setAdapter(adapter);*/
   }

   //Kullanıcı kayıt olduktan sonra mailini kontrol etmesi için mesaj gelir.
   public void showToast(String message){
       Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
       toast.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
       toast.show();


   }
}
