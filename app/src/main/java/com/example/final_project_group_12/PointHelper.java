package com.example.final_project_group_12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PointHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Points_table.db";
    public static final int DATABASE_VERSION = 1;
    public PointHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PointContract.PointEntity.SQL_CREATE);
        Log.d("DB","DB Points is successfully created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
