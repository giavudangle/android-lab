package com.example.lab01;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_LAB06_Second extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab06_second);
        TextView tvRes = findViewById(R.id.txt_result_lab06_act2);

        Bundle b = getIntent().getExtras();
        String name = b.getString("strName");
        int age = b.getInt("intAge");
        tvRes.setText("Full name : " + name + "\n" + "Age: " + age);
    }
}
