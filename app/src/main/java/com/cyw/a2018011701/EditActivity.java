package com.cyw.a2018011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cyw.a2018011701.data.Student;

public class EditActivity extends AppCompatActivity {
EditText ed,ed5,ed6;
int id;
Student s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id=getIntent().getIntExtra("id",0);
        s=MainActivity.dao.getStudent(id);
        Toast.makeText(this,s.name, Toast.LENGTH_SHORT).show();
        ed=(EditText)findViewById(R.id.editText);
        ed5=(EditText)findViewById(R.id.editText5);
        ed6=(EditText)findViewById(R.id.editText6);
        ed.setText(String.valueOf(s.id));
        ed5.setText(s.name);
        ed6.setText(String.valueOf(s.score));

    }
    public String clickUpdate(View v)
    {
        MainActivity.dao.update(s);
        return "改玩了";
    }
    public void clickBack(View v)
    {
        finish();
    }
}
