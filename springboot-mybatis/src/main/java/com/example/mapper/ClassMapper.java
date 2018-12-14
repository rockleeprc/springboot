package com.example.mapper;


import com.example.domain.Classes;
import com.example.domain.Teacher;

public interface ClassMapper {
    /**
     * 一对一
     *
     * @param id
     * @return
     */
    public Classes getClass(int id);

    /**
     * 一对一
     *
     * @param id
     * @return
     */
    public Classes getClass2(int id);

    /**
     * 一对多
     *
     * @param id
     * @return
     */
    public Classes getClass3(int id);

    /**
     * 一对多
     *
     * @param id
     * @return
     */
    public Classes getClass4(int id);

    public Teacher getTeacher(int id);
}
