package com.example.ilkuygulama;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.ilkuygulama.dao_entity.CustomerEntity;
import com.example.ilkuygulama.dao_entity.ReservationEntity;
import com.example.ilkuygulama.dao_entity.TicketEntity;
import com.example.ilkuygulama.dao_entity.VehicleEntity;

public class BaseDao extends SQLiteOpenHelper {

    private static final String database_name = "bkTurizm";
    private static final int db_version = 1;

    public BaseDao(@Nullable Context context){
        super(context,database_name,null,db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        createCustomerDao(db);
        createReservationDao(db);
        createVehicleDao(db);
        createTicketDao(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        upgradeCustomerDao(db);
        upgradeReservationDao(db);
        upgradeVehicleDao(db);
        upgradeTicketDao(db);
    }

    private void upgradeVehicleDao(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " +VehicleEntity.table_vehicles);
        onCreate(db);
    }

    private void upgradeReservationDao(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " +ReservationEntity.table_reservation);
        onCreate(db);
    }

    private void upgradeCustomerDao(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " +CustomerEntity.tablo_kisiler);
        onCreate(db);
    }
    private void upgradeTicketDao(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TicketEntity.table_ticketInfo);
        onCreate(db);
    }

    public void createCustomerDao(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + CustomerEntity.tablo_kisiler + "("
                + CustomerEntity.col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CustomerEntity.col_username + " TEXT NOT NULL, "
                + CustomerEntity.col_name + " TEXT NOT NULL, "
                + CustomerEntity.col_surname + " TEXT NOT NULL, "
                + CustomerEntity.col_email + " TEXT NOT NULL, "
                + CustomerEntity.col_phoneNum + " TEXT NOT NULL, "
                + CustomerEntity.col_password + " TEXT NOT NULL)");
    }

    public void createReservationDao(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + ReservationEntity.table_reservation + "("
                + ReservationEntity.col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ReservationEntity.col_username + " TEXT NOT NULL, "
                + ReservationEntity.col_plate + " TEXT NOT NULL, "
                + ReservationEntity.col_firstCity + " TEXT NOT NULL, "
                + ReservationEntity.col_endCity + " TEXT NOT NULL, "
                + ReservationEntity.col_startTime +" DATE NOT NULL, "
                + ReservationEntity.col_endTime +" DATE NOT NULL)");
    }

    public void createVehicleDao(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + VehicleEntity.table_vehicles + "("
                + VehicleEntity.col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VehicleEntity.col_plate + " TEXT NOT NULL, "
                + VehicleEntity.col_driver + " TEXT NOT NULL, "
                + VehicleEntity.col_firstCity + " TEXT NOT NULL, "
                + VehicleEntity.col_endCity +" TEXT NOT NULL, "
                + VehicleEntity.col_startTime + " TEXT NOT NULL, "
                + VehicleEntity.col_endTime + " TEXT NOT NULL)");

    }
    public void createTicketDao(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TicketEntity.table_ticketInfo + "("
                + TicketEntity.col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TicketEntity.col_vehiclePlate +" TEXT NOT NULL, "
                + TicketEntity.col_passengerName + " TEXT NOT NULL, "
                + TicketEntity.col_seatNumber + " TEXT NOT NULL, "
                + TicketEntity.col_startCity + " TEXT NOT NULL, "
                + TicketEntity.col_endCity+" TEXT NOT NULL)");
    }
}
