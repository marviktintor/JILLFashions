package com.marvik.apps.jillfashions.database.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.marvik.apps.jillfashions.database.tables.ClientOrders;

/**
 * Created by victor on 7/29/2015.
 */
public class DatabaseEngine extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jillfashions";
    private static final int DATABASE_VERSION = 1;

    public DatabaseEngine(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String[] sql = {SQL.CLIENT_ORDERS_SQL};
        for (int i = 0; i < sql.length; i++) {
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private static class SQL {
        static String CLIENT_ORDERS_SQL = "create table if not exists " + ClientOrders.TABLE_NAME + "("
                + ClientOrders.COL_ID + " integer primary key autoincrement,"
                + ClientOrders.COL_FIRSTNAME + " text not null,"
                + ClientOrders.COL_LASTNAME + " text not null,"
                + ClientOrders.COL_EMAIL + " text not null, "
                + ClientOrders.COL_PHONE + " text not null,"
                + ClientOrders.COL_COLOR + " text not null,"
                + ClientOrders.COL_MATERIAL + " text not null,"
                + ClientOrders.COL_ORDER_DESCRIPTION + " text not null,"
                + ClientOrders.COL_COST + " float not null,"
                + ClientOrders.COL_DISCOUNT + " float not null, "
                + ClientOrders.COL_PAID + " float not null, "
                + ClientOrders.COL_COMPLETED + " integer not null,"
                + ClientOrders.COL_COMPLETED_DATE + " long not null, "
                + ClientOrders.COL_COLLECTED + " integer not null,"
                + ClientOrders.COL_COLLECTED_DATE + " long not null,"
                 + ClientOrders.COL_FABRIC_AVATAR_URI + " text not null,"
                + ClientOrders.COL_CLIENT_AVATAR_URI + " text not null,"
                + ClientOrders.COL_COMMIT_TIME + " long not null);";
    }
}
