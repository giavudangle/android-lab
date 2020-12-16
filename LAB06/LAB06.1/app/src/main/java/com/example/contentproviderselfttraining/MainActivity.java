package com.example.contentproviderselfttraining;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button mBtnContact,mBtnListCall;
    ListView mListContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidget();
        mBtnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllContact(view);
            }
        });

        mBtnListCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHistoryCalls(view);
            }
        });

    }

    private void initializeWidget(){
        mBtnContact = findViewById(R.id.btn_contact);
        mBtnListCall = findViewById(R.id.btn_list_call);
        mListContact = findViewById(R.id.listview_contact);
    }

    public void displayAllContact(View v){
        ArrayList<String> list = new ArrayList<String>();

        // Uri use for storing path
        Uri uri = null;

        // Path of contact
        uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // Get data through ContentResolvers
        Cursor c = getContentResolver().query(uri,null,null,null,null);

        // Fill data to list (data from cursor c)

        c.moveToFirst();
        while(!c.isAfterLast()){
            // Get ID
            String s = "";
            String id = ContactsContract.Contacts._ID;
            int idIndex = c.getColumnIndex(id);
            s = c.getString(idIndex) + " - ";

            // Get displayname
            String name = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = c.getColumnIndex(name);
            s+=c.getString(nameIndex);
            c.moveToNext();
            list.add(s);
        }
        c.close();

        // Display to list view

        System.out.println(list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        mListContact.setAdapter(adapter);
    }

    public void getHistoryCalls(View view){
        ArrayList<String> list = new ArrayList<>();
        Uri uri = CallLog.Calls.CONTENT_URI; // get URI of call history (call log)
        String[] projection = new String[]{CallLog.Calls.DATE, CallLog.Calls.NUMBER}; // fields you want to get
        Cursor c = getContentResolver().query(uri,projection,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            String s = "";
            for(int i=0;i<c.getColumnCount();i++)
                s+=c.getString(i) + "\n";
            list.add(s);
            c.moveToNext();
        }
        c.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        mListContact.setAdapter(adapter);

    }


}