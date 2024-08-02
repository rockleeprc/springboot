package org.example9.dao;

import org.example9.annotation.FirstRepository;

@FirstRepository("personDao")
public class PersonDao {

    public void println(){
        System.out.println("PersonDao");
    }
}
