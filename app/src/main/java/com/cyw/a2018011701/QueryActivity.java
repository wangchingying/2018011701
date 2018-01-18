package com.cyw.a2018011701;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
boolean fastback=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        id=getIntent().getIntExtra("id",0);
        s = MainActivity.dao.getStudent(id);
        //生命週期, on create->on start->on resume-> 執行程式中, 若跳到另一畫面->on pause->on stop, 被finish()->on destroy
        //跳回上頁, 上頁又 on restart, on resume, 不會在執行on create
        //Toast.makeText(this,String.valueOf(id), Toast.LENGTH_SHORT).show();
        //            tv1.setText(String.valueOf(s.id));
        //            tv2.setText(s.name);
        //            tv3.setText(String.valueOf(s.score));

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(fastback){finish();}//使用者按了clickedit時fastback就變true
        s = MainActivity.dao.getStudent(id);
        //Toast.makeText(this,String.valueOf(id), Toast.LENGTH_SHORT).show();
        tv1.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));
    }

    //finish是將此頁消失
    public void clickBack(View v)
    {
        finish();
    }

    public void clickDelete(View v)
    {
        //加入AlertDialog 確認是否刪除
        AlertDialog.Builder builder=new AlertDialog.Builder(QueryActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("你確定要刪除此筆資料嗎?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.dao.deleteStudent(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothting
            }
        });
        builder.show();//每次都忘記寫這行
    }

    public void clickEdit(View v)
    {
        fastback=true;
        Intent it=new Intent(QueryActivity.this,EditActivity.class);
        it.putExtra("id",id);
        startActivity(it);
    }



}
