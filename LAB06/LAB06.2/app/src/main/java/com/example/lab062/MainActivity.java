package com.example.lab062;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button mBtnAdd,mBtnShow;
    EditText mEdtName,mEdtBorn;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd = findViewById(R.id.btnThem);
        mBtnShow = findViewById(R.id.btnShow);
        mEdtName = findViewById(R.id.edtTen);
        mEdtBorn = findViewById(R.id.edtNgaysinh);
        lv = findViewById(R.id.listview);


        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues(); values.put(DBHelper.TEN,
                        mEdtName
                                .getText().toString()); values.put(DBHelper.NGAYSINH,
                        mEdtBorn
                                .getText().toString()); getContentResolver().insert(NhanVienProvider.CONTENT_URI,
                        values);
                hienThiDuLieu();

            }
        });
    }
    private void hienThiDuLieu() {
        String URI = "content://com.example.lab062" +
                "/NhanVien";
        Uri NhanVien = Uri.parse(URI);
        Cursor c = getContentResolver().query(NhanVien,
                null, null, null, null); if (c == null) {
            return; }
        List<String> list = new ArrayList<>(); if(c.moveToFirst()){
            while (!c.isAfterLast()) { list.add(c.getString(c.getColumnIndex
                    (DBHelper._ID)) +
                    "\r\n " + c.getString(c.getColumnIndex
                    (DBHelper.TEN)) +
                    "\r\n " + c.getString(c.getColumnIndex
                    (DBHelper.NGAYSINH))); c.moveToNext();
            } }
        ArrayAdapter<String> adapter = new ArrayAdapter<> (getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list); ((ListView) findViewById(R.id.listview)).setAdapter(adapter);
    }
}