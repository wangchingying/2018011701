package com.cyw.a2018011701;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cyw.a2018011701.data.Student;
import com.cyw.a2018011701.data.StudentScoreDAO;

import static com.cyw.a2018011701.MainActivity.dao;

public class AddActivity extends AppCompatActivity {
    EditText ed2,ed3,ed4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    public void clickAdd(View v)
    {
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4= (EditText) findViewById(R.id.editText4);
        int id = Integer.valueOf(ed2.getText().toString());
        String name = ed3.getText().toString();
        int score = Integer.valueOf(ed4.getText().toString());
        MainActivity.dao.add(new Student(id, name, score));
//        Toast.makeText(this,String.valueOf(MainActivity.dao.getList().get(0).id) , Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,MainActivity.dao.getList().get(0).name , Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,String.valueOf(MainActivity.dao.getList().get(0).score) , Toast.LENGTH_SHORT).show();
        finish();


    }
}
