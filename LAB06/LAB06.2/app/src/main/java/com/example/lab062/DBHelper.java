package com.example.lab062;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TEN_DATABASE = "QuanLyNhanVien";
    public static final String TEN_BANG_NHANVIEN = "NhanVien";

    public static final String _ID = "_id";
    public static final String TEN = "ten";
    public static final String NGAYSINH = "ngaysinh";

    private Context context;
    public DBHelper(Context context) {
        super(context, TEN_DATABASE,null,1);
        this.context=context;
    }

    String createTableQuery = "CREATE TABLE " + TEN_BANG_NHANVIEN +" ("
            +  _ID + " integer primary key autoincrement ,"
            + TEN + " TEXT not null, "
            + NGAYSINH + " text not null)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableQuery);
        Toast.makeText(context, "Create successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TEN_BANG_NHANVIEN);
    }

}
