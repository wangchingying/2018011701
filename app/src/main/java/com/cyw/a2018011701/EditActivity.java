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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id=getIntent().getIntExtra("id",0);
        Student s=MainActivity.dao.getStudent(id);
        Toast.makeText(this,s.name, Toast.LENGTH_SHORT).show();
        ed=(EditText)findViewById(R.id.editText);
        ed5=(EditText)findViewById(R.id.editText5);
        ed6=(EditText)findViewById(R.id.editText6);
        ed.setText(String.valueOf(s.id));
        ed5.setText(s.name);
        ed6.setText(String.valueOf(s.score));

    }
    public void clickUpdate(View v)
    {
        int id=Integer.valueOf(ed.getText().toString());
        String name=ed5.getText().toString();
        int score= Integer.valueOf(ed6.getText().toString());

            MainActivity.dao.update(new Student(id, name, score));
        Toast.makeText(this, "已更新", Toast.LENGTH_SHORT).show();
   }

    public void clickBack(View v)
    {
        Intent it=new Intent(EditActivity.this,MainActivity.class);
        startActivity(it);
    }

}
