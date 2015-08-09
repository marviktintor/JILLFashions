package com.marvik.apps.jillfashions.database.tables;

import android.net.Uri;

/**
 * Created by victor on 7/30/2015.
 */
public class ClientOrders {


    public static final String TABLE_NAME = "client_orders";
    public static final String COL_ID= "_id";
    public static final String COL_FIRSTNAME = "firstname";
    public static final String COL_LASTNAME = "lastname";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";
    public static final String COL_COLOR = "color";
    public static final String COL_MATERIAL = "material";
    public static final String COL_ORDER_DESCRIPTION = "order_description";
    public static final String COL_COST = "cost";
    public static final String COL_DISCOUNT = "discount";
    public static final String COL_PAID = "paid";
    public static final String COL_COMPLETED = "completed";
    public static final String COL_COMPLETED_DATE = "completed_date";
    public static final String COL_COLLECTED = "collected";
    public static final String COL_COLLECTED_DATE = "collected_date";
    public static final String COL_FABRIC_AVATAR_URI = "fabric_avatar";
    public static final String COL_CLIENT_AVATAR_URI = "client_avatar";
    public static final String COL_COMMIT_TIME = "commit_time";


    public static final Uri CONTENT_URI = Uri.parse("content://com.marvik.apps.jillfashions.database.provider.DataProvider/" +TABLE_NAME);
}
