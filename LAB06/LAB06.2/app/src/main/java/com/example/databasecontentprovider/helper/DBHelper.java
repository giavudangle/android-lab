package com.example.databasecontentprovider.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StaffManagement";


    /*TABLE STAFF*/
    public static final String STAFF_TABLE = "Staff";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DAY_OF_BIRTH = "date_of_birth";

    static final String CREATE_DATABASE_STRING = " CREATE TABLE " + STAFF_TABLE
            + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  NAME + " TEXT NOT NULL, "
            + DAY_OF_BIRTH + " TEXT NOT NULL)";


    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STAFF_TABLE);
        onCreate(sqLiteDatabase);
    }
}
