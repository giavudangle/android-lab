package com.example.lab05.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.lab05.model.Student;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "students_db";
    public static final String TABLE_NAME = "student";
    public static final String ID = "_id";
    public static final String NAME = "student_name";
    public static final String CLASS = "student_class";

    private Context context;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryString = "CREATE TABLE " +
                TABLE_NAME + " (" +
                ID + " TEXT primary key, " +
                NAME + " TEXT, " +
                CLASS + " TEXT)";
        db.execSQL(queryString);
        Toast.makeText(context, "Created DB ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Dropped DB ", Toast.LENGTH_SHORT).show();

    }

    public DBManager(Context context){
        super(context,DATABASE_NAME,null,1);
        this.context=context;
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,student.getmStudentId());
        values.put(NAME,student.getmStudentName());
        values.put(CLASS,student.getmStudentClass());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }
}
