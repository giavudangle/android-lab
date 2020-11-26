package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ((TextView) findViewById(R.id.textView)).setText(getIntent().getStringExtra("Wifi"));
    }
}
