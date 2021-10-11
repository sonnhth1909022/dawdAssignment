package com.fpt.t1911e.dawdassignment;

import com.fpt.t1911e.dawdassignment.daymodel.DailyForecasts;
import com.fpt.t1911e.dawdassignment.hourmodel.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    public static String main_url = "http://dataservice.accuweather.com/";
    @GET("forecasts/v1/daily/5day/353412?apikey=XddZEpIZKOOIgOjNlzlIk7KVbbEln5hZ&language=vi-vn&metric=true")
    Call<List<DailyForecasts>> getDay();

    @GET("forecasts/v1/hourly/12hour/353412?apikey=XddZEpIZKOOIgOjNlzlIk7KVbbEln5hZ&language=vi-vn&metric=true")
    Call<List<Root>> getHour();
}
