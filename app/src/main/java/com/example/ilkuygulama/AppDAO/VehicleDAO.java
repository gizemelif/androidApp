package com.example.ilkuygulama.AppDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO extends SQLiteOpenHelper {
    private static final String database_name = CustomerDAO.getDatabase_name();
    private static final int db_version = CustomerDAO.getDb_version();
    private static final String table_vehicles = "Vehicle";
    private static final String col_id = "id";
    private static final String col_plate = "plate";
    private static final String col_driver = "driverName";
    private static final String col_firstCity = "firstCity";
    private static final String col_endCity = "endCity"; //varış noktası
    private static final Date col_startTime = Date.valueOf("startTime");
    private static final Date col_endTime = Date.valueOf("endTime");


    public VehicleDAO(@Nullable Context context){
        super(context,database_name,null,db_version);
    }

    public void addVehicle(String plaka, String driverName, String firstCity, String endCity, Date startTime, Date endTime){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put(col_plate, plaka);

            db.insert(table_vehicles, null, cv);
        }catch (Exception e){}
        db.close();
    }
    public List<String> getVehicleList(){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        try{
            String[] list = {col_id, col_plate};
            Cursor cursor = db.query(table_vehicles,list,null,null,null,null,null);

            while(cursor.moveToNext()){
                veriler.add(cursor.getInt(0)+ ","
                        + cursor.getInt(1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return veriler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_vehicles + "(" + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_plate + " TEXT NOT NULL, "
                + col_driver + " TEXT NOT NULL, "
                + col_plate + " TEXT NOT NULL, "
                + col_driver + " TEXT NOT NULL, "
                + col_firstCity + " TEXT NOT NULL, "
                + col_endCity + " TEXT NOT NULL, "
                + col_startTime +" DATE NOT NULL, "
                + col_endTime +" DATE NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +table_vehicles);
        onCreate(db);
    }

    public static String getDatabase_name() {
        return database_name;
    }

    public static int getDb_version() {
        return db_version;
    }

    public static String getTable_vehicles() {
        return table_vehicles;
    }

    public static String getCol_id() {
        return col_id;
    }

    public static String getCol_plate() {
        return col_plate;
    }

    public static String getCol_driver() {
        return col_driver;
    }

    public static String getCol_firstCity() {
        return col_firstCity;
    }

    public static String getCol_endCity() {
        return col_endCity;
    }

    public static Date getCol_startTime() {
        return col_startTime;
    }

    public static Date getCol_endTime() {
        return col_endTime;
    }
}
