package com.example.lab01;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
public class Activity_LAB05_First extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab05_first);

        Button btnStart = findViewById(R.id.btn_start_activity_second);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(getApplicationContext(),Activity_LAB05_Second.class);
                startActivity(i);
            }
        });

    }
}
