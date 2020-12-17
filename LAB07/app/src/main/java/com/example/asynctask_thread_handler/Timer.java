package com.example.asynctask_thread_handler;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Timer extends AsyncTask {

    static int num ;
    Activity mainAcitivy;

    EditText editText ;
    Button btn ;
    TextView textView  ;


    public Timer(Activity mainAcitivy) {
        this.mainAcitivy = mainAcitivy;
        editText = mainAcitivy.findViewById(R.id.edt_timer);
        btn = mainAcitivy.findViewById(R.id.btn_start);
        textView = mainAcitivy.findViewById(R.id.txt_show);
    }

    // This method will run SECOND !!!
    @Override
    protected Object doInBackground(Object[] objects) {
        while (true){
            try {
                if(num ==0){
                    return null;
                }
                Thread.sleep(10);
                num = num - 1;
                publishProgress(num);
            } catch (InterruptedException e){
                Log.d("doInBackgroundEX" ,e.getMessage().toString());
            }
        }
    }


    // This method will run FIRST !!!
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        try {
            num = Integer.parseInt(editText.getText().toString()) * 100;
            btn.setEnabled(false);
            textView.setEnabled(false);
        } catch (Exception e){
            Log.d("OnPreExcuteEx",e.getMessage().toString());
        }
    }


    // This method will run at the end!
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        btn.setEnabled(true);
        editText.setEnabled(true);
    }

    // This method will run SECOND !!! parallel with do in background
    // Do in background can send data to onPressUpdate through out publicProgress method
    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        int millis = (int)values[0] % 100;
        textView.setText(
                (int)values[0] / 100 + ":"
                + ((millis + "").length() != 2 ? "0"
                + millis : millis)
        );
    }
}
