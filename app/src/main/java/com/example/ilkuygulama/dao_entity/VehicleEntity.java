package com.example.ilkuygulama.dao_entity;

import android.content.Context;
import androidx.annotation.Nullable;
import com.example.ilkuygulama.BaseDao;

public class VehicleEntity extends BaseDao {
    public static final String table_vehicles = "Vehicle";
    public static final String col_id = "id";
    public static final String col_plate = "plate";
    public static final String col_driver = "driverName";
    public static final String col_firstCity = "firstCity";
    public static final String col_endCity = "endCity"; //varış noktası
    public static final String col_startTime = "startTime";
    public static final String col_endTime = "endTime";

    public VehicleEntity(@Nullable Context context) {
        super(context);
    }
}
