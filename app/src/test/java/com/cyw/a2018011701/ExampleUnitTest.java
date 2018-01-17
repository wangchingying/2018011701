package com.cyw.a2018011701;

import com.cyw.a2018011701.data.Student;
import com.cyw.a2018011701.data.StudentScoreDAO;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//  @Test純Java測試
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test1() throws Exception {
        Mytest1 a =new Mytest1();
        assertEquals(6, a.haha(2,4));
    }
    @Test
    public void test2()
    {
        StudentScoreDAO dao=new StudentScoreDAO();
        dao.add(new Student(1,"Jenny",100));
        dao.add(new Student(2,"Wanda",90));

        assertEquals(2,dao.getList().size());
        assertEquals(100,dao.getList().get(0).score);

    }
    @Test
    public void test_getStudent() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 90));
        assertEquals(90, dao.getStudent(2).score);
    }
    @Test
    public void test_getStudent1() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 90));
        assertEquals(null, dao.getStudent(3));
    }
    @Test
    public void test_updateStudent1() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 90));
       dao.updateStudent(1, "Bob1", 96);
//        dao.updateStudent(2, "Mary2", 95);
        assertEquals("Bob1", dao.getStudent(1).name);
        assertEquals(96, dao.getStudent(1).score);
    }
    @Test
    public void test_deleteStudent1() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 90));
        dao.deleteStudent(1);
        assertEquals(null,dao.getStudent(1));
//        assertEquals(true, dao.deleteStudent(1));

    }
}