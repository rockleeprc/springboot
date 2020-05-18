package org.example8;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
@SpringBootApplication
public class Application {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);

        Map<String, DataSource> map = context.getBeansOfType(DataSource.class);
        System.out.println(map);

        HikariDataSource ds = context.getBean(HikariDataSource.class);
        System.out.println(ds.getMaximumPoolSize());

        Connection connection = ds.getConnection();
        System.out.println(connection);

        context.close();

    }

//    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
//        ds.setMetricRegistry();
        return ds;
    }

}
