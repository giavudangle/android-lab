package com.example.lab03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        final EditText name = findViewById(R.id.input_customer_name);
        final EditText phone = findViewById(R.id.input_customer_phone);
        final EditText address = findViewById(R.id.input_customer_address);
        Button btnAddNewCustomer = findViewById(R.id.button_add_customer);

        btnAddNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ShowCustomerActivity.class);
                Bundle bundle = new Bundle();

                String cName = name.getText().toString();
                String cPhone = phone.getText().toString();
                String cAddress =address.getText().toString();

                bundle.putString("customer_name",cName);
                bundle.putString("customer_phone",cPhone);
                bundle.putString("customer_address",cAddress);

                intent.putExtra("customer_bundle",bundle);
                startActivity(intent);

            }
        });

    }


}
