package com.marvik.apps.jillfashions.database.transactions;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by victor on 7/30/2015.
 */
public class TransactionManager {

    class ClientOrders implements DataOperations {

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            return null;
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
}
