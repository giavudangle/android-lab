package com.example.asynctask_thread_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public static int numb;
    public static TextView tv;
    public static EditText edt;
    public static Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.txt_show);
        edt = (EditText) findViewById(R.id.edt_timer);
        btn = (Button) findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCountdownByAsyncTask();
                //startCountdownByThread();
            }


        });
    }

    private void startCountdownByAsyncTask() {
        new Timer(this).execute();
    }

    // LAB02
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                btn.setEnabled(false);
                edt.setEnabled(false);
            } else if (msg.what == 1) {
                int millis = msg.arg1 % 100;
                tv.setText(msg.arg1 / 100 + ":"
                        + ((millis + "").length() != 2 ? "0"
                        + millis : millis));
            } else {
                btn.setEnabled(true);
                edt.setEnabled(true);
            } }
    };

    public void startCountdownByThread() {
        numb = Integer.parseInt
                (edt.getText().toString()) * 100;
        new Thread() {

            public void run() {
                // 0 là trạng bắt đầu
                handler.obtainMessage(0).sendToTarget();
                while (true) {
                    try {
                        if (numb == 0) {
                        // 2 là trạng thái kết thúc
                            handler.obtainMessage(2)
                                    .sendToTarget();
                            return; }
                        Thread.sleep(10);
                        numb = numb - 1;
                    // 1 là trạng thái đang chạy
                        handler.obtainMessage(1,
                                numb, 0).sendToTarget();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } } }
                // Dùng start() để chạy ngầm, không làm đơ ứng dụng khi
                // đang xử lý tiến trình bên trong
        }.start();
    }

}