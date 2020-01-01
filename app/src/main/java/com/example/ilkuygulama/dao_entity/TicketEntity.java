package com.example.ilkuygulama.dao_entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.ilkuygulama.BaseDao;

public class TicketEntity extends BaseDao {
    public static final String table_ticketInfo = "TicketInfoEntity";
    public static final String col_id = "id";
    public static final String col_vehiclePlate = "plate";
    public static final String col_passengerName = "name";
    public static final String col_seatNumber = "seatNumber";
    public static final String col_startCity = "startCity";
    public static final String col_endCity = "endCity";

    public TicketEntity(@Nullable Context context) {
        super(context);
    }

    public void addCustomer(String username, String ad, String seatNum, String startCity, String endCity){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put(col_vehiclePlate, username);
            cv.put(col_passengerName, ad);
            cv.put(col_seatNumber, seatNum);
            cv.put(col_startCity, startCity);
            cv.put(col_endCity, endCity);

            db.insert(table_ticketInfo, null, cv);
        }catch (Exception e){}
        db.close();
    }
}
