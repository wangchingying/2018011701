package com.cyw.a2018011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cyw.a2018011701.data.DBtype;
import com.cyw.a2018011701.data.Student;
import com.cyw.a2018011701.data.StudentDAO;
import com.cyw.a2018011701.data.StudentDAOFactory;
import com.cyw.a2018011701.data.StudentFileDAO;
import com.cyw.a2018011701.data.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> studentNames;
    DBtype dbType;
    ArrayAdapter<String> adapter;
    //宣告成只能new 一次
    //public static StudentFileDAO dao;

    //選告interface(父類別就好)
    public static StudentDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbType = DBtype.CLOUD; // 1:記憶體 2:檔案 3:SQLite 4:Firebase
        //用factory來控制要用記憶體還是檔案,這樣不用大改程式\
        dao = StudentDAOFactory.getDAOInstance(this, dbType);
        //將context, 此activity的資訊傳入StudentFileDAO建構式
        //dao=new StudentFileDAO(MainActivity.this);
        studentNames = new ArrayList<>();
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, studentNames);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent it = new Intent(MainActivity.this, QueryActivity.class);
                it.putExtra("id", dao.getList().get(position).id);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    //此副程式讓app在一開啟的時候能把資料先讀出來
    public void refreshData()
    {
        studentNames.clear();
        for (Student s : dao.getList())
        {
            studentNames.add(s.name);
        }
        adapter.notifyDataSetChanged();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add)
        {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
