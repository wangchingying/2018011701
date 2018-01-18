package com.cyw.a2018011701.data;

import android.content.Context;

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

public class StudentFileDAO implements StudentDAO {

    public Context context;
    public ArrayList<Student> mylist;

    //新增傳入Context建構式, 取得activity的資訊,才能有getFile可用
    public StudentFileDAO(Context context)
    {
        this.context=context;
        mylist = new ArrayList<>();
    }
    //存檔
    public void saveFile(){
        File f= new File(context.getFilesDir(),"mydata.txt");
        FileWriter fw=null;
        try {
            fw=new FileWriter(f);
            Gson gson=new Gson();
            String data=gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //讀檔
    public void loadFile(){

        File f=new File(context.getFilesDir(),"mydata.txt");
        FileReader fr=null;

        try {
            fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String str=br.readLine();
            Gson gson=new Gson();
            mylist=gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //新增 一個Student
    public boolean add(Student s)
    {
        mylist.add(s);
        saveFile();
        return true;
    }

    //取得Student陣列
    public ArrayList<Student> getList()
    {
        loadFile();
        return mylist;
    }

    //用id取得一個Student資料
    public Student getStudent(int id)
    {
        loadFile();
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
        loadFile();
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
        loadFile();
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
