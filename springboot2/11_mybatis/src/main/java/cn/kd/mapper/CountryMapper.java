package cn.kd.mapper;

import cn.kd.entity.Country;

import java.util.List;

public interface CountryMapper {

    List<Country> findAll();

    void insert(Country country);

    Country selectById(Long id);

}
