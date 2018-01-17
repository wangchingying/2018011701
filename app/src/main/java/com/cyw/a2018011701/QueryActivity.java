package com.cyw.a2018011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cyw.a2018011701.data.Student;

public class QueryActivity extends AppCompatActivity {
TextView tv1,tv2,tv3;
Student s;
int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        id=getIntent().getIntExtra("id",0);
        s = MainActivity.dao.getStudent(id);
        //Toast.makeText(this,String.valueOf(id), Toast.LENGTH_SHORT).show();
            tv1.setText(String.valueOf(s.id));
            tv2.setText(s.name);
            tv3.setText(String.valueOf(s.score));

    }
    public void clickBack(View v)
    {
        finish();
    }

    public void clickDelete(View v)
    {
        MainActivity.dao.deleteStudent(id);
        finish();
    }

    public void clickEdit(View v)
    {
        Intent it=new Intent(QueryActivity.this,EditActivity.class);
        it.putExtra("id",id);
        startActivity(it);
    }

}
