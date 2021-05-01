package cn.kd;

import cn.kd.entity.UserRole;
import cn.kd.mapper.UserRoleMapper;
import cn.kd.service.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void testServiceFindAll() {
        List<UserRole> list = userRoleService.findAll();
        System.out.println(list);
    }

    @Test
    public void testMapperFindAll() {
        List<UserRole> list = userRoleMapper.findAll();
        System.out.println(list);
    }
}
