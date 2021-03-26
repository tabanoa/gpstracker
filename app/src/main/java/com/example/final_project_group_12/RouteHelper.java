package com.example.final_project_group_12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RouteHelper extends SQLiteOpenHelper {

public static final int DATABASE_VERSION =1;
public static final String DATABASE_NAME = "Route_table.db";

    public RouteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(RouteContract.RouteEntity.SQL_CREATE);
        Log.d("DB","DB Route is successfully created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
