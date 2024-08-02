package cn.kd;

import cn.kd.entity.Country;
import cn.kd.mapper.CountryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private ApplicationContext applicationContext;


    @Test
    public void testFindAll() {
//        DataSourceTransactionManager tx = applicationContext.getBean(DataSourceTransactionManager.class);
//        TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
//        System.out.println("-------"+tx);

        List<Country> list = countryMapper.findAll();
        List<Country> list1 = countryMapper.findAll();
        System.out.println(list);
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < 10; i++) {
            Country bj = new Country();
            bj.setName("BJ-" + i);
            bj.setCode("110-" + i);
            countryMapper.insert(bj);
            System.out.println(bj.getId());
        }
    }

    @Test
    public void testSelectById() {
        Long id = 631635383901224960L;
        Country country = countryMapper.selectById(id);
        System.out.println(country);
    }
}
