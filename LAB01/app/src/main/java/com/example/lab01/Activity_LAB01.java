package com.example.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_LAB01 extends AppCompatActivity {

    EditText mEditText;
    Button mButton;
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab01);


        mEditText=(EditText)findViewById(R.id.edit_number_one);
        mButton=(Button) findViewById(R.id.btnSayHello);
        mTextView=(TextView)findViewById(R.id.tvResult);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEditText.getText().toString();
                mTextView.setText("Hello " + name + "!");
            }
        });

    }
}