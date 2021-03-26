package com.example.final_project_group_12;

import androidx.annotation.NonNull;

public class Points {


    private int id;
    private int routeId;
    private double longitude;
    private double latitude;
    private String date;

    public Points(int routeId, double longitude, double latitude, String date) {
        this.routeId = routeId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(@NonNull int id) { this.id = id; }


    public int getRouteId() { return routeId; }
    public void setRouteId(@NonNull int routeId) { this.routeId = routeId; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
