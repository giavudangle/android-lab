package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toolbar;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    TextView textView;
    ProgressBar progressBar;
    MyAsysncTask myAsysncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.prbDemo);
        btnStart = findViewById(R.id.btn_Start);
        textView = findViewById(R.id.txtStatus);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
    }

    private void doStart(){
        myAsysncTask=new MyAsysncTask(this);
        myAsysncTask.execute();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }
}
