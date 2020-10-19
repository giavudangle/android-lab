package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageProfile =findViewById(R.id.image_profile);
        ImageView imageCustomer = findViewById(R.id.image_customer);
        Button btnExit = (Button) findViewById(R.id.button_exit);

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEditProfile();
            }
        });

        imageCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCustomerActivity.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
               System.exit(0);
            }
        });
    }

    private void handleEditProfile(){
        Intent receive = getIntent();
        String username = receive.getStringExtra("username");
        String password = receive.getStringExtra("password");

        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);


        startActivity(intent);
    }
}