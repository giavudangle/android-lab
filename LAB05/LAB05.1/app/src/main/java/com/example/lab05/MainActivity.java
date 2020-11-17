package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lab05.adapter.MyAdapter;
import com.example.lab05.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ListView listView;
    public static ArrayList<Student> studentArrayList;
    public static MyDatabase database;
    public static DBManager dbManager;
    public Button btnSave,btnEdit;
    private EditText editTextName,editTextClass;
    private String studentName,studentClass;
    private static long id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        database = new MyDatabase(this);
        dbManager.hello();
        initWidget();
        updateData();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editStudent();
            }
        });




    }

    public void initWidget(){
        editTextName = findViewById(R.id.edt_student_name);
        editTextClass= findViewById(R.id.edt_student_class);
        btnSave=findViewById(R.id.btn_add);
        btnEdit=findViewById(R.id.btn_edit_student);
        listView = findViewById(R.id.lv_student);

    }


    public void updateData(){
        if(studentArrayList == null){
            studentArrayList = new ArrayList<Student>();
        }else {
            studentArrayList.removeAll(studentArrayList);
        }

        Cursor cursor = database.getAllData();
        if(cursor!=null){
            while(cursor.moveToNext()){
                Student student = new Student();
                student.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.ID))));
                student.setStudentName(cursor.getString(cursor.getColumnIndex(DBManager.NAME)));
                student.setStudentClass(cursor.getString(cursor.getColumnIndex(DBManager.CLASS)));

                studentArrayList.add(student);
            }
        }

        if (studentArrayList !=null){
            listView.setAdapter(new
                    MyAdapter(getApplicationContext()));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editTextName.setText(studentArrayList.get(i).getStudentName());
                editTextClass.setText(studentArrayList.get(i).getStudentClass());
                MainActivity.id =l;
            }
        });
    }
    public Student getStudentInformation(){
        studentName = editTextName.getText().toString();
        studentClass = editTextClass.getText().toString();
        if(studentName.trim().length() ==0 || studentClass.trim().length() == 0) return null;
        Student student = new Student();
        student.set_id(id);
        student.setStudentName(studentName);
        student.setStudentClass(studentClass);
        return student;
    }

    public void addStudent(){
        Student student1 = getStudentInformation();
        Log.d("Student",student1.getStudentName() + " * "+ student1.getStudentClass());
        if(student1!=null){
            if(database.createStudent(student1) != -1){

                studentArrayList.add(student1);
                updateData();
                listView.invalidateViews();
                editTextName.setText(null);
                editTextClass.setText(null);
                id=-1;
            }
        }
    }

    public void editStudent( ){
        Student student = getStudentInformation();
        if(student!=null && id!=-1){
            database.editStudent(student);
            updateData();
            listView.invalidateViews();
            editTextName.setText(null);
            editTextClass.setText(null);
            id=-1;
        }
    }
}