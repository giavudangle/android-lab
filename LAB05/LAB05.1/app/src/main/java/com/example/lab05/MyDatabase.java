package com.example.lab05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.lab05.models.Student;

public class MyDatabase {
    SQLiteDatabase database;
    DBManager helper;

    public MyDatabase(Context context) {

        helper = new DBManager(context);
        database = helper.getWritableDatabase();
    }

    public Cursor getAllData(){
        String[] columns = {
                DBManager.ID,
                DBManager.NAME,
                DBManager.CLASS
        };
        Cursor cursor = null;
         cursor = database.query(DBManager.TABLE_NAME,columns,
                null,null,null,null,DBManager.ID + " DESC");

        return cursor;
    }

    public long createStudent(Student student){

        ContentValues values = new ContentValues();
        Log.d("student",student.getStudentName() + " * " + student.getStudentClass());
        values.put(DBManager.NAME,student.getStudentName());
        values.put(DBManager.CLASS,student.getStudentClass());
        return database.insert(DBManager.TABLE_NAME,null,values);
    }

    public long deleteStudent(Student student){
        return database.delete(DBManager.TABLE_NAME,DBManager.NAME
                + " = " + "'" + student.getStudentName() +"'",null);
    }

    public long editStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(DBManager.NAME,student.getStudentName());
        values.put(DBManager.CLASS,student.getStudentClass());

        return  database.update(DBManager.TABLE_NAME,values,
                DBManager.ID + " = " + student.get_id(),null);
    }

}
