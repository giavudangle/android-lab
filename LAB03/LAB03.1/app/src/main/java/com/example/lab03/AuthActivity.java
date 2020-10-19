package com.example.lab03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username,password;
    final String USER_NAME="admin";
    final String PASS="admin";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        btnLogin=(Button)findViewById(R.id.button_login);
        username=(EditText)findViewById(R.id.input_username);
        password=(EditText)findViewById(R.id.input_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAuth();
            }
        });
    }

    private boolean isValid(){
        if(username.getText().toString().length() > 0 || password.getText().toString().length() > 0 ){
            if(username.getText().toString().equals(USER_NAME) && password.getText().toString().equals(PASS)){
                return true;
            }else {
                Toast.makeText(getApplicationContext(),"Invalid user or password",Toast.LENGTH_SHORT).show();

            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Please fill all form",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void handleAuth(){
        if(isValid()){
            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("username",username.getText().toString());
            intent.putExtra("password",password.getText().toString());
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Try again",Toast.LENGTH_SHORT).show();

        }
    }
}
