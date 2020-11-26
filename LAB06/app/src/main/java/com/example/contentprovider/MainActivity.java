package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnList,btnCall;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall=findViewById(R.id.btnCuocGoi);
        btnList=findViewById(R.id.btnDanhBa);
        listView=findViewById(R.id.listView);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiDanhBa();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhatKyCuocGoi();
            }
        });
    }
    public void hienThiDanhBa(){
        ArrayList<String> list=new ArrayList<String>();
        Uri uri=null;
        uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor=getContentResolver().query(uri,null,null
        ,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String s="";
            String idColumnName=ContactsContract.Contacts._ID;
            int idIndex= cursor.getColumnIndex((idColumnName));
            s=cursor.getString(idIndex)+" - ";
            String nameColumnName=ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex=cursor.getColumnIndex(nameColumnName);
            s+=cursor.getString(nameIndex);
            cursor.moveToNext();
            list.add(s);
        }
        cursor.close();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
    public void nhatKyCuocGoi(){
        ArrayList<String> list=new ArrayList<String>();
        Uri uri = CallLog.Calls.CONTENT_URI;
        String[]projection=new String[]{CallLog.Calls.DATE, CallLog.Calls.NUMBER};
        Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String s="";
            for(int i=0;i< cursor.getColumnCount();i++){
                s+=cursor.getString(i)+"\n";
            }
            list.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}