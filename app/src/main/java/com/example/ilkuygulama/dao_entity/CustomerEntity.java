package com.example.ilkuygulama.dao_entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.ilkuygulama.BaseDao;
import com.example.ilkuygulama.Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerEntity extends BaseDao {
    public static final String tablo_kisiler = "CustomerEntity";
    public static final String col_id = "id";
    public static final String col_name = "name";
    public static final String col_username = "username";
    public static final String col_surname = "surname";
    public static final String col_email = "email";
    public static final String col_phoneNum = "phone";
    public static final String col_password = "password";

    public CustomerEntity(@Nullable Context context) {
        super(context);
    }

    public void addCustomer(String username, String ad, String soyad, String email,String tel, String sifre){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put(col_username, username);
            cv.put(col_name, ad);
            cv.put(col_surname, soyad);
            cv.put(col_email, email);
            cv.put(col_phoneNum, tel);
            cv.put(col_password, sifre);

            db.insert(tablo_kisiler, null, cv);
        }catch (Exception e){}
        db.close();
    }

    public List<Customer> getCustomers(){
        // Bu kısımda List içerisindeki veri tipini oluşturduğun model sınıfı yazarsan verileri tutman daha kolay olur
        List<Customer> veriler = new ArrayList<>();
        Customer customer;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String[] sutunlar = {col_id, col_username, col_name, col_surname,col_email, col_phoneNum, col_password};
            Cursor cursor = db.query(tablo_kisiler,sutunlar,null, null, null, null,null,
                    null);
            while(cursor.moveToNext()){
                customer = new Customer();
                customer.setId(cursor.getString(cursor.getColumnIndex(col_id)));
                customer.setName(cursor.getString(cursor.getColumnIndex(col_name)));
                customer.setSurname(cursor.getString(cursor.getColumnIndex(col_surname)));
                customer.setUsername(cursor.getString(cursor.getColumnIndex(col_username)));
                customer.setEmail(cursor.getString(cursor.getColumnIndex(col_email)));
                customer.setPhone(cursor.getString(cursor.getColumnIndex(col_phoneNum)));
                customer.setPassword(cursor.getString(cursor.getColumnIndex(col_password)));
                veriler.add(customer);
            }
        }catch (Exception e){}
        db.close();
        return veriler;
    }
}
