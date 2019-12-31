package com.example.ilkuygulama.dao_entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.ilkuygulama.BaseDao;
import com.example.ilkuygulama.Model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationEntity extends BaseDao {
    public static String table_reservation = "ReservationEntity";
    public static final String col_id = "id";
    public static final String col_username = "username";
    public static final String col_plate = "plate";
    public static final String col_firstCity = "firstCity";
    public static final String col_endCity = "endCity"; //varış noktası
    public static final String col_startTime = "startTime";
    public static final String col_endTime = "endTime";

    public ReservationEntity(@Nullable Context context) {
        super(context);
    }

    public void addReservation(String username, String plaka, String firstCity, String endCity, String startTime, String endTime){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put(col_username, username);
            cv.put(col_plate, plaka);
            cv.put(col_firstCity, firstCity);
            cv.put(col_endCity, endCity);

            db.insert(table_reservation, null, cv);
        }catch (Exception e){}
        db.close();
    }

    public List<Reservation> getVehicleList(){
        List<Reservation> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        try{
            String[] list = {col_id, col_username, col_plate, col_firstCity, col_endCity};
            Cursor cursor = db.query(table_reservation,list,null,null,null,null,null);

            while(cursor.moveToNext()){
                Reservation reservation = new Reservation();
                reservation.setId(cursor.getString(cursor.getColumnIndex(ReservationEntity.col_id)));
                reservation.setPlate(cursor.getString(cursor.getColumnIndex(ReservationEntity.col_plate)));
                reservation.setUsername(cursor.getString(cursor.getColumnIndex(ReservationEntity.col_username)));
                reservation.setFirstCity(cursor.getString(cursor.getColumnIndex(ReservationEntity.col_firstCity)));
                reservation.setEndCity(cursor.getString(cursor.getColumnIndex(ReservationEntity.col_endCity)));
                veriler.add(reservation);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return veriler;
    }
}
