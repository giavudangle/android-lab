package com.example.databasecontentprovider.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.databasecontentprovider.helper.DBHelper;

import java.util.HashMap;

public class StaffProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.example.databasecontentprovider.providers.StaffProvider";

    public static final String URI = "content://" + PROVIDER_NAME + "/staffs";
    public static final Uri CONTENT_URI = Uri.parse(URI);
    
    private SQLiteDatabase db;
    private static HashMap<String,String> STAFF_PROJECTION_MAP; // key - value;
    
    // Query priority
    static final int STAFF = 1;
    static final int STAFF_ID = 2;
    
    // UriMathcer is URI TREE contains nodes are Uri
    // When we create , we must define root
    
    static final UriMatcher uriMatcher; // link bellow

    // Uri format like this : <prefix>://<authority>/<data_type>/<id>
    // Prefix : content:// (default)
    // Authority : contacts,browser (api android ) or custom provider like : com.vudang.selftraining
    // Data_type: type of data you want to get like : content://contacts/people -> get all people in contact
    // id : get by id

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"staffs",STAFF);
        uriMatcher.addURI(PROVIDER_NAME,"staffs/#",STAFF_ID);
    }

    
    @Override
    public boolean onCreate() {
        // Get current context and pass down to DBHelper
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context);

        // Read - Write DB
        db = dbHelper.getWritableDatabase();

        return db == null ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(DBHelper.STAFF_TABLE);

        switch (uriMatcher.match(uri)){
            case STAFF:
                qb.setProjectionMap(STAFF_PROJECTION_MAP);
                break;
            case STAFF_ID:
                qb.appendWhere(DBHelper._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknow URI " + uri);
        }
        if(sortOrder == null || sortOrder == "")
            sortOrder = DBHelper.NAME;

        Cursor c = qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);

        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case STAFF:
                return "vnd.android.cursor.dir/staffs";
            case STAFF_ID:
                return "vnd.android.cursor.item/staffs";
            default:
                throw  new IllegalArgumentException("Unsupport URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID = db.insert(DBHelper.STAFF_TABLE,"",contentValues);
        if(rowID > 0){
            // Return success
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw  new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;

        // Get data by uri
        switch (uriMatcher.match(uri)){
            case STAFF:
                // Remove by statement
                count = db.delete(DBHelper.STAFF_TABLE,selection,selectionArgs);
                break;
            case STAFF_ID:
                count = db.delete(DBHelper.STAFF_TABLE,
                        DBHelper._ID + "="
                                + uri.getPathSegments().get(1)
                                + (!TextUtils.isEmpty(selection) ? " AND ("
                                + selection + ')' : ""),selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // SYNC DATA

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case STAFF:
                count = db.update(DBHelper.STAFF_TABLE,contentValues,selection,selectionArgs);
                break;
            case STAFF_ID:
                count = db.update(DBHelper.STAFF_TABLE,contentValues,
                        DBHelper._ID + "=" + uri.getPathSegments().get(1)
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' :""),selectionArgs);
                break;
            default:
                throw  new IllegalArgumentException("Unknow URI "+ uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }


}
