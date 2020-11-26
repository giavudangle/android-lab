package com.example.progressbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.Date;

public class MyAsysncTask extends AsyncTask<Void, Integer, Void> {


    Activity contextParent;

    public MyAsysncTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Hàm này sẽ chạy đầu tiên khi AsyncTask này được gọi
        //Ở đây mình sẽ thông báo quá trình load bắt đâu "Start"
        Toast.makeText(contextParent, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Hàm được được hiện tiếp sau hàm onPreExecute()
        //Hàm này thực hiện các tác vụ chạy ngầm
        //Tuyệt đối k vẽ giao diện trong hàm này
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            //khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //Hàm thực hiện update giao diện khi có dữ liệu từ hàm doInBackground gửi xuống
        super.onProgressUpdate(values);
        //Thông qua contextCha để lấy được control trong MainActivity
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.prbDemo);
        //vì publishProgress chỉ truyền 1 đối số
        //nên mảng values chỉ có 1 phần tử
        int number = values[0];
        //tăng giá trị của Progressbar lên
        progressBar.setProgress(number);
        //đồng thời hiện thị giá trị là % lên TextView
        TextView textView = (TextView) contextParent.findViewById(R.id.txtStatus);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Hàm này được thực hiện khi tiến trình kết thúc
        //Ở đây mình thông báo là đã "Finshed" để người dùng biết
        Toast.makeText(contextParent, "Okie, Finished", Toast.LENGTH_SHORT).show();
    }
}
