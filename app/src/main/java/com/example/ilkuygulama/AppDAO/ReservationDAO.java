package com.example.ilkuygulama.AppDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends SQLiteOpenHelper {
    private static String database_name = CustomerDAO.getDatabase_name();
    private static Integer db_version = CustomerDAO.getDb_version();
    private static String table_reservation = "Reservation";
    private static final String col_id = "id";
    private static final String col_username = "username";
    private static final String col_plate = "plate";
    private static final String col_firstCity = "firstCity";
    private static final String col_endCity = "endCity"; //varış noktası
    private static final String col_startTime = "startTime";
    private static final String col_endTime = "endTime";


    public ReservationDAO(@Nullable Context context){
        super(context,database_name,null,db_version);
    }

    public void addVehicle(String username, String plaka, String firstCity, String endCity, String startTime, String endTime){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put(col_username, username);
            cv.put(col_plate, plaka);
            cv.put(col_firstCity, firstCity);
            cv.put(col_endCity, endCity);
            cv.put(col_startTime, startTime);
            cv.put(col_endTime, endTime);

            db.insert(table_reservation, null, cv);
        }catch (Exception e){}
        db.close();
    }
    public List<String> getVehicleList(){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        try{
            String[] list = {col_id, col_username, col_plate, col_firstCity, col_endCity, col_startTime, col_endTime};
            Cursor cursor = db.query(table_reservation,list,null,null,null,null,null);

            while(cursor.moveToNext()){
                veriler.add(cursor.getInt(0)+ "\n"
                        +cursor.getInt(1)+"\n"
                        +cursor.getInt(2)+"\n"
                        +cursor.getInt(3)+"\n"
                        +cursor.getInt(4)+"\n"
                        +cursor.getInt(5)+"\n"
                        +cursor.getInt(6)+"\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return veriler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_reservation + "(" + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_username + " TEXT NOT NULL, "
                + col_plate + " TEXT NOT NULL, "
                + col_firstCity + " TEXT NOT NULL, "
                + col_endCity + " TEXT NOT NULL, "
                + col_startTime +" DATE NOT NULL, "
                + col_endTime +" DATE NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +table_reservation);
        onCreate(db);
    }

    public static String getCol_id() {
        return col_id;
    }

    public static String getCol_username() {
        return col_username;
    }

    public static String getCol_firstCity() {
        return col_firstCity;
    }

    public static String getCol_endCity() {
        return col_endCity;
    }

    public static String getCol_startTime() {
        return col_startTime;
    }

    public static String getCol_endTime() {
        return col_endTime;
    }

    public static String getTable_reservation() {
        return table_reservation;
    }

    public static void setTable_reservation(String table_reservation) {
        ReservationDAO.table_reservation = table_reservation;
    }

}
