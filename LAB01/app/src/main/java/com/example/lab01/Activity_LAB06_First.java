package com.example.lab01;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_LAB06_First  extends AppCompatActivity {
     EditText edName,edAge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab06_first);
        Button btn = findViewById(R.id.btnStartAcvitity_LAB06);
          edName = findViewById(R.id.edtName_LAB06);
          edAge = findViewById(R.id.edtAge_LAB06);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    Intent i = new Intent(getApplicationContext(),Activity_LAB06_Second.class);
                    Bundle b = new Bundle();
                    b.putString("strName",edName.getText().toString());
                    b.putInt("intAge",Integer.parseInt(edAge.getText().toString()));
                    i.putExtras(b);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(), "Please fill all form", Toast.LENGTH_LONG).show();


                }

            }
        });
    }

    private boolean isValid(){
        return ( edAge.length() > 0 && edName.length()>0 ) ? true : false;
    }
}
