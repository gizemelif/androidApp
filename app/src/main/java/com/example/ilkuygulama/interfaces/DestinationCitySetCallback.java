package com.example.ilkuygulama.interfaces;

import android.content.Context;

import java.util.List;

public interface DestinationCitySetCallback {
    void onSetDestinationCity(String cityStart, int position, List<String> citiesStart, Context context);
}
