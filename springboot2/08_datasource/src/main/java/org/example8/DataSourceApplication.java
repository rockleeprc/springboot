package org.example8;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
@SpringBootApplication
public class DataSourceApplication {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws SQLException, InterruptedException {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(DataSourceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        HikariDataSource dataSource = context.getBean(HikariDataSource.class);
        System.out.println(dataSource);
        System.out.println("getMaximumPoolSize:" + dataSource.getMaximumPoolSize());
        System.out.println("getMaximumPoolSize:" + dataSource.getMinimumIdle());
//        for (int i = 0; i < 15; i++) {
//            Thread t = new Thread(() -> {
//                Connection conn = null;
//                try {
//                    conn = dataSource.getConnection();
//                    System.out.println("getConnection:" +conn);
//
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                } finally {
//                    try {
//                        if (conn != null)
//                            conn.close();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            });
//            t.start();
//            t.join();
//        }
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        System.out.println(jdbcTemplate);
        context.close();

    }
}
