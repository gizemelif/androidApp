package com.example.ilkuygulama.dao_entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.ilkuygulama.BaseDao;
import com.example.ilkuygulama.Model.Bus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VehicleEntity extends BaseDao {
    public static final String table_vehicles = "Vehicle";
    public static final String col_id = "id";
    public static final String col_plate = "plate";
    public static final String col_driver = "driverName";
    public static final String col_firstCity = "firstCity";
    public static final String col_endCity = "endCity"; //varış noktası
    public static final Date col_startTime =  Date.valueOf("startTime");
    public static final Date col_endTime = Date.valueOf("endTime");

    public VehicleEntity(@Nullable Context context) {
        super(context);
    }
}
