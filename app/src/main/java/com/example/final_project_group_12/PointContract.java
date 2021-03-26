package com.example.final_project_group_12;

import android.provider.BaseColumns;

public final class PointContract {

    private PointContract(){}
       public static class PointEntity implements BaseColumns {
        public static final String TABLE_NAME = "Points_table";
        public static final String COLUMN_NAME_ROUTE_ID = "route_id";
        public static final String COLUMN_NAME_LONG = "longitude";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_DATE = "date";

//+ COLUMN_NAME_ROUTE_ID + " INTEGER, "
        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_ROUTE_ID + " INTEGER NOT NULL, "
        + COLUMN_NAME_LONG + " REAL, " + COLUMN_NAME_LAT + " REAL, " + COLUMN_NAME_DATE + " TEXT, "
                + " CONSTRAINT point_route_id FOREIGN KEY ("+ COLUMN_NAME_ROUTE_ID +") REFERENCES "+
        RouteContract.RouteEntity.TABLE_NAME +"("+ RouteContract.RouteEntity._ID+") ON DELETE CASCADE);";
// FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ADD RELATIONSHIP TO OTHER DATABASE.

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
