package com.example.lab01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_LAB02 extends AppCompatActivity implements View.OnClickListener {
    TextView tvResult;
    Button btnHello,btnChao,btnClearText;
    EditText edInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab02);

        tvResult=findViewById(R.id.txtResult);
        btnHello=findViewById(R.id.btnHello);
        btnChao=findViewById(R.id.btnChao);
        btnClearText=findViewById(R.id.btnClearText);
        edInput=findViewById(R.id.edit_number_one);

        btnClearText.setOnClickListener(this);
        btnChao.setOnClickListener(this);
        btnHello.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHello:
                tvResult.setText("Hello " + edInput.getText().toString());
                break;
            case R.id.btnChao:
                tvResult.setText("Chao " + edInput.getText().toString());
                break;
            case R.id.btnClearText:
                tvResult.setText("");
                edInput.setText("");
                break;
        }
    }
}
