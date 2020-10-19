package com.example.lab03;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ShowCustomerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_customer);

        TextView tName = findViewById(R.id.txt_show_name);
        TextView tPhone = findViewById(R.id.txt_show_phone);
        TextView tAddress = findViewById(R.id.txt_show_address);
        Button btnBack = findViewById(R.id.button_callback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("customer_bundle");

        String name = bundle.getString("customer_name");
        String phone = bundle.getString("customer_phone");
        String address = bundle.getString("customer_address");


        tName.setText(name);
        tPhone.setText(phone);
        tAddress.setText(address);



    }
}
