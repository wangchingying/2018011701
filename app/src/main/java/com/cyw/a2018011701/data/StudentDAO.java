package com.cyw.a2018011701.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

// 所有DAO需follow此interface的規定, 還有雲端DAO及資料庫DAO可以寫

public interface StudentDAO {

    public boolean add(Student s);
    public ArrayList<Student> getList();
    public Student getStudent(int id);
    public boolean deleteStudent(int id);
    public boolean update(Student s);

}
