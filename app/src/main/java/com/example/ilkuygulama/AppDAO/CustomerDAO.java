package com.example.ilkuygulama.AppDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ilkuygulama.Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends SQLiteOpenHelper {
    private static final String database_name = "bkTurizm";
    private static final int db_version = 1;
    private static final String tablo_kisiler = "Customer";
    public static final String col_id = "id";
    private static final String col_name = "name";
    private static final String col_username = "username";
    private static final String col_surname = "surname";
    private static final String col_email = "email";
    private static final String col_phoneNum = "phone";
    private static final String col_password = "password";

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    public CustomerDAO(@Nullable Context context){
        super(context,database_name,null,db_version);
    }

    public void veriEkle(String username, String ad, String soyad, String email,String tel, String sifre){
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
    public List<Customer> veriListele(){
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
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + tablo_kisiler + "(" + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +col_username + " TEXT NOT NULL, "
                    + col_name + " TEXT NOT NULL, "
                    + col_surname + " TEXT NOT NULL, "
                    + col_email + " TEXT NOT NULL, "
                    + col_phoneNum + " TEXT NOT NULL, "
                    + col_password + " TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + tablo_kisiler);
        onCreate(db);
    }
    public static String getDatabase_name() {
        return database_name;
    }

    public static int getDb_version() {
        return db_version;
    }

    public static String getTablo_kisiler() {
        return tablo_kisiler;
    }

    public static String getCol_id() {
        return col_id;
    }

    public static String getCol_name() {
        return col_name;
    }

    public static String getCol_username() {
        return col_username;
    }

    public static String getCol_surname() {
        return col_surname;
    }

    public static String getCol_email() {
        return col_email;
    }

    public static String getCol_phoneNum() {
        return col_phoneNum;
    }

    public static String getCol_password() {
        return col_password;
    }

}