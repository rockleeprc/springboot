package cn.kd.service;

import cn.kd.entity.UserRole;
import cn.kd.mapper.UserRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> findAll() {
        int page = 1;
        int pageSize = 10;
        PageHelper.startPage(page, pageSize);
        List<UserRole> list = userRoleMapper.findAll();
        PageInfo<UserRole> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        return list;
    }
}
