package com.example.final_project_group_12;

import android.provider.BaseColumns;

public final class RouteContract {
    private RouteContract(){}
    public static class RouteEntity implements BaseColumns {

        public static final String TABLE_NAME = "Route_table";
        public static final String COLUMN_NAME_ROUTE_NAME = "route_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_DATE = "date";

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_ROUTE_NAME + " TEXT UNIQUE, "
                + COLUMN_NAME_DESCRIPTION + " TEXT, " + COLUMN_NAME_RATING + " REAL, "
                + COLUMN_NAME_DATE + " TEXT)";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
