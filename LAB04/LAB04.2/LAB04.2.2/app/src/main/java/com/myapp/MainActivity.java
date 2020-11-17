package com.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder myRecorder;
    private MediaPlayer myPlayer;
    private String outputFile = null;
    private Button start;
    private Button stop;
    private Button play;
    private Button stopPlay;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text1);
        // Đường dẫn lưu file
        outputFile =  Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/RecordName .3gp";
        myRecorder = new MediaRecorder();
        // Thiết lập nguồn âm thanh để ghi âm
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //Thiết lập loại file
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // Thiết lập âm thanh mã hóa cần ghi âm
        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        // Lưu lại theo đường dân outputFile
        myRecorder.setOutputFile(outputFile);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(v -> stop(v));
        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
            }
        });
        stopPlay = (Button) findViewById(R.id.stopPlay);
        stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlay(v);
            }
        });
    }

    public void start(View view) {
        try {
            // Chuẩn bị ghi âm
            myRecorder.prepare();
            // Bắt đầu ghi âm
            myRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText("Bắt đầu ghi âm...");
        start.setEnabled(false);
        stop.setEnabled(true);
    }

    public void stop(View view) {
        try {
            // Dừng ghi âm.
            myRecorder.stop();
            // Giải phóng tài nguyên của đối tượng
            myRecorder.release();
            myRecorder = null;
            stop.setEnabled(false);
            play.setEnabled(true);
            text.setText("Dừng ghi âm.");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void play(View view) {
        try {
            myPlayer = new MediaPlayer();
// Lấy tài nguyên ghi âm qua đường dẫn
            myPlayer.setDataSource(outputFile);
            myPlayer.prepare();
// Phát
            myPlayer.start();
            play.setEnabled(false);
            stopPlay.setEnabled(true);
            text.setText("Đang nghe...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPlay(View view) {
        try {
            if (myPlayer != null) {
                myPlayer.stop();
                myPlayer.release();
                myPlayer = null;
                play.setEnabled(true);
                stopPlay.setEnabled(false);
                text.setText("Dừng nghe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}