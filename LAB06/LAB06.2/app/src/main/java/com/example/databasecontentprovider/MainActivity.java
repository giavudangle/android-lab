package com.example.databasecontentprovider;

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

import com.example.databasecontentprovider.helper.DBHelper;
import com.example.databasecontentprovider.providers.StaffProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mBtnAdd = findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAdd(view);
            }
        });

        Button mBtnShowList = findViewById(R.id.btn_show);
        mBtnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayListStaff();
            }
        });
    }

    public void onClickAdd(View view){
        ContentValues values = new ContentValues();
        values.put(DBHelper.NAME,((EditText)findViewById(R.id.edt_name)).getText().toString());
        values.put(DBHelper.DAY_OF_BIRTH,((EditText)findViewById(R.id.edt_dob)).getText().toString());
        getContentResolver().insert(StaffProvider.CONTENT_URI,values);
        displayListStaff();
    }


    private void displayListStaff() {
        String URI = "content://com.example.databasecontentprovider.providers.StaffProvider" +"/staffs";
        Uri staff = Uri.parse(URI);
        Cursor c = getContentResolver().query(staff,null,null,null,null);
        if ( c== null) return;
        List<String> list = new ArrayList<>();
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                list.add(c.getString(c.getColumnIndex(DBHelper._ID))
                        + "\r\n" + c.getString(c.getColumnIndex(DBHelper.NAME))
                        + "\r\n" + c.getString(c.getColumnIndex(DBHelper.DAY_OF_BIRTH)));
                c.moveToNext();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,list);

        ((ListView)findViewById(R.id.listview_information)).setAdapter(adapter);
    }
}