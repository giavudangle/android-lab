package com.example.lab01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_LAB03 extends AppCompatActivity implements View.OnClickListener {

    EditText numOne,numTwo;
    Button btnAdd,btnMinus,btnProduct,btnDivide;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab03);

        numOne=findViewById(R.id.edit_number_one);
        numTwo=findViewById(R.id.edit_number_two);
        btnAdd=findViewById(R.id.btn_add);
        btnMinus=findViewById(R.id.btn_minus);
        btnProduct=findViewById(R.id.btn_product);
        btnDivide=findViewById(R.id.btn_divide);
        result=findViewById(R.id.txt_show_result);


        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnProduct.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(isValid()){
            switch (view.getId()){
                case R.id.btn_add:
                    if(isValid()){
                        Double x = add();
                        result.setText("A + B = " + x.toString());
                    }
                    break;
                case R.id.btn_minus:
                    if(isValid()){
                        Double y = minus();
                        result.setText("A - B = " +y.toString());
                    }
                    break;
                case R.id.btn_product:
                    if(isValid()){
                        Double  z = product();
                        result.setText("A * B = " +z.toString());
                    }
                    break;
                case R.id.btn_divide:
                    if(isValid() && canDivide()){
                        Double t = divide();
                        result.setText("A / B = " + t.toString());
                    }else {
                        result.setText("Cannot divide by zero");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }else {
            Toast.makeText(this,"Please fill all input",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValid(){
        return (numOne.getText().length() > 0 && numTwo.getText().length() > 0 ) ? true : false;
    }

    private double add(){
        return  Double.parseDouble(numOne.getText().toString())+ Double.parseDouble(numTwo.getText().toString());
    }

    private double minus(){
        return  Double.parseDouble(numOne.getText().toString()) - Double.parseDouble(numTwo.getText().toString());
    }

    private double product(){
        return  Double.parseDouble(numOne.getText().toString()) * Double.parseDouble(numTwo.getText().toString());
    }

    private double divide(){
        return  Double.parseDouble(numOne.getText().toString()) / Double.parseDouble(numTwo.getText().toString());
    }

    private boolean canDivide(){
        return (Double.parseDouble(numTwo.getText().toString()) == 0 ) ? false : true;

    }
}