package com.example.lab05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student_db";
    public static final String TABLE_NAME = "student";

    public static final String ID = "_id";
    public static final String NAME = "student_name";
    public static final String CLASS = "student_class";

    private Context context;
    public DBManager(Context context) {
        super(context, DATABASE_NAME,null,1);
        this.context=context;
    }

    String createTableQuery = "CREATE TABLE " + TABLE_NAME +" ("
            +  ID + " integer primary key autoincrement ,"
            + NAME + " TEXT not null, "
            + CLASS + " text not null)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableQuery);
        Toast.makeText(context, "Create successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void hello(){
        Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
    }









}
