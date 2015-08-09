package com.marvik.apps.jillfashions.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.marvik.apps.jillfashions.database.engine.DatabaseEngine;
import com.marvik.apps.jillfashions.database.tables.ClientOrders;

/**
 * Created by victor on 7/29/2015.
 */
public class DataProvider extends ContentProvider {

    private DatabaseEngine databaseEngine;
    private SQLiteDatabase sqLiteDatabase;
    private static final UriMatcher uriMatcher;

    private static final int URI_MATCHER_TABLE_CLIENT_ORDERS;
    private static final String AUTHORITY;

    @Override
    public boolean onCreate() {
        databaseEngine = new DatabaseEngine(getContext());
        return false;
    }


    @Override
    public String getType(Uri uri) {
        sqLiteDatabase = getDatabaseEngine().getWritableDatabase();
        return uri.getLastPathSegment();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sqLiteDatabase = getDatabaseEngine().getWritableDatabase();


        if( uriMatcher.match(uri) == URI_MATCHER_TABLE_CLIENT_ORDERS){
            getSqLiteDatabase().insert(ClientOrders.TABLE_NAME,null,values);
        }

        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        sqLiteDatabase = getDatabaseEngine().getReadableDatabase();

        if( uriMatcher.match(uri) == URI_MATCHER_TABLE_CLIENT_ORDERS){
            return getSqLiteDatabase().query(ClientOrders.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        }

        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        sqLiteDatabase = getDatabaseEngine().getWritableDatabase();

        if( uriMatcher.match(uri) == URI_MATCHER_TABLE_CLIENT_ORDERS){
            return  getSqLiteDatabase().delete(ClientOrders.TABLE_NAME,selection,selectionArgs);
        }

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        sqLiteDatabase = getDatabaseEngine().getWritableDatabase();

        if( uriMatcher.match(uri) == URI_MATCHER_TABLE_CLIENT_ORDERS){
            return  getSqLiteDatabase().update(ClientOrders.TABLE_NAME,values,selection,selectionArgs);
        }

        return 0;
    }

    /**
     * *****************************************************************
     */

    private DatabaseEngine getDatabaseEngine() {
        return databaseEngine;
    }

    private SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    static {
        URI_MATCHER_TABLE_CLIENT_ORDERS = 1;
        AUTHORITY = "com.marvik.apps.jillfashions.database.provider.DataProvider";
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ClientOrders.TABLE_NAME, URI_MATCHER_TABLE_CLIENT_ORDERS);
    }
}
