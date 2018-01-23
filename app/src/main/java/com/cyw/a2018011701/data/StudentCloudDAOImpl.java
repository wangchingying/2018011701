package com.cyw.a2018011701.data;

import android.content.Context;

import com.cyw.a2018011701.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentCloudDAOImpl implements StudentDAO {

    public Context context;
    public ArrayList<Student> mylist;
    FirebaseDatabase database;
    DatabaseReference myRef;

    //新增傳入Context建構式, 取得activity的資訊,才能有getFile可用
    public StudentCloudDAOImpl(final Context context)
    {
        this.context=context;

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<Student>>(){}.getType());
                ((MainActivity) context).refreshData();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }
    }
    //存檔
    public void saveFile(){
        // Write a message to the database
        Gson gson = new Gson();
        String data = gson.toJson(mylist);
        myRef.setValue(data);
    }



    //新增 一個Student
    public boolean add(Student s)
    {
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }
        mylist.add(s);
        saveFile();
        return true;
    }

    //取得Student陣列
    public ArrayList<Student> getList()
    {

        return mylist;
    }

    //用id取得一個Student資料
    public Student getStudent(int id)
    {

        for (Student s : mylist)
        {
            if (s.id == id)
            {
                return s;
            }
        }
        return null;
    }

    //用id刪除一個Student資料
    public boolean deleteStudent(int id)
    {

        for (Student s : mylist)
        {
            if (s.id == id)
            {
                mylist.remove(s);
                saveFile();
                return true;
            }
        }
        return false;
    }


    //傳入一個Student物件並更新
    public boolean update(Student s)
    {

        for (Student t : mylist)
        {
            if (t.id == s.id)
            {
                t.name = s.name;
                t.score = s.score;
                saveFile();
                return true;
            }
        }
        return false;
    }
}
