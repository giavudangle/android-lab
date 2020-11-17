package com.example.lab05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab05.database.DBManager;
import com.example.lab05.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mName ;
    private EditText mClass;
    private Button mAdd,mUpdate;
    private DBManager dbManager;
    private List<Student> studentList;
    private ListView lvStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = findViewById(R.id.edt_name);
        mClass = findViewById(R.id.edt_class);
        mAdd=findViewById(R.id.button_add);
        mUpdate=findViewById(R.id.button_update);
        lvStudent=findViewById(R.id.lv_student);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student()
                dbManager.addStudent();
            }
        });
    }

    private String generateId(){
        int rd = (int)Math.random()*999;
        return "TS" + rd.
    }
}
