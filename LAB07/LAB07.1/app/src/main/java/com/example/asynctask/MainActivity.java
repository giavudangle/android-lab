package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.AsyncTask;
import java.util.concurrent.TimeUnit;
import android.os.AsyncTask;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText edt;
    Button btn;
    CountDownTimer Timer;

    private static final String FORMAT = "%02d:%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv =findViewById(R.id.tvConlai);
        edt =findViewById(R.id.edtNhapso);
        btn =findViewById(R.id.btnBatdau);

        super.onStart();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timer = new CountDownTimer(Integer.parseInt(edt.getText().toString())*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tv.setText(String.valueOf(millisUntilFinished/1000));
                    }

                    @Override
                    public void onFinish() {
                        tv.setText("Hết giờ");
                    }
                }.start();
            }
        });

    }

}