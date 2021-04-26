package cn.kd;

import cn.kd.mapper.AdviserWorkloadMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdviserWorkloadMapperTest {
    @Autowired
    private AdviserWorkloadMapper adviserWorkloadMapper;

    @Test
    public void testSelectAdviserWorkload() {
        String startTime = "2021-04-26";
        String endTime = "2021-04-26";
        Long[] groupIds = {1L, 2L};
        Long[] adviserIds = {11L, 22L};
        List<Serializable> list = adviserWorkloadMapper.selectAdviserWorkload(startTime, endTime, groupIds, adviserIds);
        System.out.println(list);
    }
}
