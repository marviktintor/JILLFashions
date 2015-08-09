package com.marvik.apps.jillfashions.database.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import static com.marvik.apps.jillfashions.database.tables.ClientOrders.*;

/**
 * Created by victor on 7/30/2015.
 */
public class TransactionManager {

    private Context context;

    public TransactionManager(Context context) {
        setContext(context);
    }

    public void commitNewOrder(String firstname, String lastname, String email,
                               String phonenumber, String color, String material, String cost,
                               String discount, String paidAmount,int completed, long completedDate,
                               int collected, long collectedDate, Uri clientAvatarUri, Uri fabricAvatarUri) {

        ClientOrders clientOrders = new ClientOrders();
        ContentValues values = new ContentValues();


        values.put(COL_FIRSTNAME,firstname);
        values.put(COL_LASTNAME,lastname);
        values.put( COL_EMAIL ,email);
        values.put(COL_PHONE ,phonenumber);
        values.put(COL_COLOR ,color);
        values.put(COL_MATERIAL,material);
        values.put(COL_COST,cost);
        values.put(COL_DISCOUNT ,discount);
        values.put(COL_PAID,paidAmount);
        values.put(COL_COMPLETED,completed);
        values.put(COL_COMPLETED_DATE ,collectedDate);
        values.put( COL_COLLECTED,collected);
        values.put(COL_COLLECTED_DATE,collectedDate);
        values.put(COL_FABRIC_AVATAR_URI,fabricAvatarUri.toString());
        values.put(COL_CLIENT_AVATAR_URI,clientAvatarUri.toString());
        getContext().getContentResolver().insert(com.marvik.apps.jillfashions.database.tables.ClientOrders.CONTENT_URI,values);
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
