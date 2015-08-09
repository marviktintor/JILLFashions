package com.marvik.apps.jillfashions.database.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.marvik.apps.jillfashions.models.orders.OrdersInfo;

import java.util.ArrayList;
import java.util.List;

import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_CLIENT_AVATAR_URI;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COLLECTED;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COLLECTED_DATE;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COLOR;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COMMIT_TIME;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COMPLETED;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COMPLETED_DATE;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_COST;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_DISCOUNT;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_EMAIL;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_FABRIC_AVATAR_URI;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_FIRSTNAME;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_LASTNAME;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_MATERIAL;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_ORDER_DESCRIPTION;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_PAID;
import static com.marvik.apps.jillfashions.database.tables.ClientOrders.COL_PHONE;

/**
 * Created by victor on 7/30/2015.
 */
public class TransactionManager {

    private Context context;


    public TransactionManager(Context context) {
        setContext(context);
    }

    public void commitNewOrder(String firstname, String lastname, String email,
                               String phonenumber, String color, String material, String orderDescription, String cost,
                               String discount, String paidAmount, int completed, long completedDate,
                               int collected, long collectedDate, String clientAvatarUri, String fabricAvatarUri) {

        ClientOrders clientOrders = new ClientOrders();
        ContentValues values = new ContentValues();


        values.put(COL_FIRSTNAME, firstname);
        values.put(COL_LASTNAME, lastname);
        values.put(COL_EMAIL, email);
        values.put(COL_PHONE, phonenumber);
        values.put(COL_COLOR, color);
        values.put(COL_MATERIAL, material);
        values.put(COL_ORDER_DESCRIPTION, orderDescription);
        values.put(COL_COST, cost);
        values.put(COL_DISCOUNT, discount);
        values.put(COL_PAID, paidAmount);
        values.put(COL_COMPLETED, completed);
        values.put(COL_COMPLETED_DATE, collectedDate);
        values.put(COL_COLLECTED, collected);
        values.put(COL_COLLECTED_DATE, collectedDate);
        values.put(COL_FABRIC_AVATAR_URI, fabricAvatarUri.toString());
        values.put(COL_CLIENT_AVATAR_URI, clientAvatarUri.toString());
        values.put(COL_COMMIT_TIME, System.currentTimeMillis());
        getContext().getContentResolver().insert(com.marvik.apps.jillfashions.database.tables.ClientOrders.CONTENT_URI, values);
    }

    public List<OrdersInfo> getOrdersAll() {
        List<OrdersInfo> clientOrders = new ArrayList<OrdersInfo>();
        Cursor cursor = getContext().getContentResolver().query(com.marvik.apps.jillfashions.database.tables.ClientOrders.CONTENT_URI, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String clientName = cursor.getString(cursor.getColumnIndex(COL_FIRSTNAME)) + " " + cursor.getString(cursor.getColumnIndex(COL_LASTNAME));
            String orderDescription = cursor.getString(cursor.getColumnIndex(COL_ORDER_DESCRIPTION));
            String clientAvatarUri = cursor.getString(cursor.getColumnIndex(COL_CLIENT_AVATAR_URI));
            int completed = cursor.getInt(cursor.getColumnIndex(COL_COMPLETED));
            int collected = cursor.getInt(cursor.getColumnIndex(COL_COLLECTED));
            clientOrders.add(new OrdersInfo(clientName, orderDescription, clientAvatarUri, completed, collected));
        }


        return clientOrders;
    }

    class ClientOrders implements DataOperations {

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            return uri;
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            return null;
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            return 0;
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
            return 0;
        }
    }


    interface DataOperations {
        public Uri insert(Uri uri, ContentValues values);

        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);

        public int delete(Uri uri, String selection, String[] selectionArgs);

        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
