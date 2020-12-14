package com.example.lab06_contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button danhba,allcuocgoi;
    ListView lvall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        danhba=(Button) findViewById(R.id.btndanhba);
        allcuocgoi=(Button) findViewById(R.id.btnallcuocgoi);
        lvall= findViewById(R.id.lvcuocgoi);


        danhba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hienThiDanhBa();}
        });
        allcuocgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhatKyCuocGoi();
            }
        });


    }
    public void hienThiDanhBa() {
        ArrayList<String> list = new ArrayList<String>();
        // Uri dùng để lưu trữ đường dẫn truy xuất
        Uri uri = null;
        // Đường dẫn trỏ đến bảng dữ liệu people
        uri = ContactsContract.CommonDataKinds.Phone
                .CONTENT_URI;
        // Lấy dữ liệu thông qua ContentResolver
        Cursor c = getContentResolver().query(uri, null, null, null, null);
        // Lấy dữ liệu từ Cursor đổ vào list
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String s = "";
            String idColumnName = ContactsContract
                    .Contacts._ID;
            int idIndex = c.getColumnIndex(idColumnName);
            s = c.getString(idIndex) + " - ";
            String nameColumnName = ContactsContract
                    .Contacts.DISPLAY_NAME;
            int nameIndex = c.getColumnIndex
                    (nameColumnName);
            s += c.getString(nameIndex);
            c.moveToNext();
            list.add(s);
        }
        c.close();
        // Hiển thị lên listView
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        lvall.setAdapter(adapter);
    }
    public void nhatKyCuocGoi() {
        ArrayList<String> list = new ArrayList<String>();
        Uri uri = CallLog.Calls.CONTENT_URI;
        String[] projection = new String[]
                { CallLog.Calls.DATE, CallLog.Calls.NUMBER};
        Cursor c = getContentResolver().query(uri,
                projection, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String s = "";
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getString(i) + "\n";
            }
            list.add(s);
            c.moveToNext();
        }
        c.close();
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        lvall.setAdapter(adapter);
    }
}
