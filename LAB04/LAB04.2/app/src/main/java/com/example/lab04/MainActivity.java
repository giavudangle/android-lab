package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.ImageButton;

import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {
    public TextView songName, startTimeField, endTimeField;
    // Lớp dùng để phát bài hát
    private MediaPlayer mediaPlayer;
    // Thời gian bắt đầu hát
    private double startTime = 0;
    // Thời gian kết thúc
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    // Thời gian để nhảy đến
    private int forwardTime = 3000;
    // Thời gian để quay lui lại
    private int backwardTime = 3000;
    // Seekbar dùng để cho biết thời gian bài hát đến vị trí nào private SeekBar seekbar;
    private SeekBar seekbar;
    private ImageButton playButton, pauseButton;
    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        songName = (TextView) findViewById(R.id.textView4);
        startTimeField = (TextView) findViewById(R.id.textView1);
        endTimeField = (TextView) findViewById(R.id.textView2);
        seekbar = (SeekBar) findViewById(R.id.seekBar1);
        playButton = (ImageButton) findViewById(R.id.imageButton1);
        pauseButton = (ImageButton) findViewById(R.id.imageButton2);
        songName.setText("GIAC MO CHI LA GIAC MO.mp3");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gmclgm);
        seekbar.setClickable(false);
        seekbar.setOnSeekBarChangeListener
                (new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch
                            (SeekBar seekBar) {
                    }

                    @Override
                    public void onStartTrackingTouch
                            (SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged
                            (SeekBar seekBar, int progress,
                             boolean fromUser) {
                        // Khi kéo SeekBar thì kiểm tra xem
                        // có trùng với vị trí đang hát không
                        // nếu không thì cho máy phát từ vị
                        // trí đó
                        if (progress != (int) startTime) {
                            mediaPlayer.seekTo(progress);
                        }
                    }
                });
        pauseButton.setEnabled(false);
    }

    public void play(View view) {
        // Phương thức bắt đầu chơi nhạc
        mediaPlayer.start();
        // Lấy tổng thời gian của bài hát
        finalTime = mediaPlayer.getDuration();
        // Lấy giây hiện tại
        startTime = mediaPlayer.getCurrentPosition();
        // Chỉ 1 lần thiết lập cho seekbar
        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }
        // Thiết lập thời gian kết thúc endTimeField.setText(String.format(
        endTimeField.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS
                        .toMinutes((long) finalTime), TimeUnit.MILLISECONDS
                        .toSeconds((long) finalTime)
                        - TimeUnit.MINUTES
                        .toSeconds(TimeUnit
                                .MILLISECONDS
                                .toMinutes
                                        ((long) finalTime))));
        // Thiết lập vị trí thời gian đang hát
        startTimeField.setText(String.format(
                "%d:%d",
                TimeUnit.MILLISECONDS
                        .toMinutes((long) startTime), TimeUnit.MILLISECONDS
                        .toSeconds((long) startTime)
                        - TimeUnit.MINUTES
                        .toSeconds(TimeUnit
                                .MILLISECONDS
                                .toMinutes
                                        ((long) startTime))));
        // Thiết lập vị trí thời gian đang hát
        seekbar.setProgress((int) startTime);
        // Cập nhật thời gian đang hát và
        // seekbar với thời gian chờ 0.1 giây
        myHandler.postDelayed(UpdateSongTime, 100);
        pauseButton.setEnabled(true);
        playButton.setEnabled(false);
    }


}
}
}

