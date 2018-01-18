package com.cyw.a2018011701;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cyw.a2018011701.data.Student;

public class EditActivity extends AppCompatActivity {
EditText ed5,ed6;
TextView tv4;

int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id=getIntent().getIntExtra("id",0);
        Student s=MainActivity.dao.getStudent(id);
        //Toast.makeText(this,s.name, Toast.LENGTH_SHORT).show();
        tv4=(TextView) findViewById(R.id.textView4);
        ed5=(EditText)findViewById(R.id.editText5);
        ed6=(EditText)findViewById(R.id.editText6);
        tv4.setText(String.valueOf(s.id));
        ed5.setText(s.name);
        ed6.setText(String.valueOf(s.score));

    }
    public void clickUpdate(View v)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(EditActivity.this);
        builder.setTitle("更新資料");
        builder.setMessage("確定要更新此筆資料嗎?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id=Integer.valueOf(tv4.getText().toString());
                String name=ed5.getText().toString();
                int score= Integer.valueOf(ed6.getText().toString());

                MainActivity.dao.update(new Student(id, name, score));
                Toast.makeText(EditActivity.this, "已更新", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        builder.show();


        }

    public void clickBack(View v)
    {
        finish();
        //不要用intent, 用intent是把原畫面蓋住, 用finish是結束畫面
        //        Intent it=new Intent(EditActivity.this,MainActivity.class);
        //        startActivity(it);
    }




}
