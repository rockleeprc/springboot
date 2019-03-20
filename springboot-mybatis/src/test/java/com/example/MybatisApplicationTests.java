package com.example;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private SqlSession sqlSession;
//
//    @Autowired
//    private ClassMapper classMapper;
//
//    @Test
//    public void testOneToMany() {
////        Classes c1 = classMapper.getClass3(1);
////        System.out.println(c1);
//        Classes c2 = classMapper.getClass4(1);
//        System.out.println(c2.getName());
////        System.out.println(c2.getStudents());
////        c2.getStudents().forEach(s ->
////                System.out.println("s_name" + s.getName())
////        );
//    }
//
//    @Test
//    public void testOneToOne() {
//        Classes c1 = classMapper.getClass(1);
//        System.out.println(c1);
//        Classes c2 = classMapper.getClass2(1);
//        System.out.println(c2);
//    }
//
//
//    @Test
//    public void sqlSession() {
//        System.out.println(sqlSession);
//    }
//
//    @Test
//    public void dataSource() {
//        System.out.println(dataSource.getClass());
//    }
//
//    @Test
//    public void contextLoads() {
//    }

}
