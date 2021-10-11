package com.fpt.t1911e.dawdassignment.daymodel;

public class DailyForecasts {
    private String Date;
    private Temperature Temperature;
    private Day Day;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public com.fpt.t1911e.dawdassignment.daymodel.Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(com.fpt.t1911e.dawdassignment.daymodel.Temperature temperature) {
        Temperature = temperature;
    }

    public com.fpt.t1911e.dawdassignment.daymodel.Day getDay() {
        return Day;
    }

    public void setDay(com.fpt.t1911e.dawdassignment.daymodel.Day day) {
        Day = day;
    }
}
