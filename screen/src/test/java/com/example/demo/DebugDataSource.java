package com.example.demo;

import com.kd.ScreenApplicaton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class DebugDataSource {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ScreenApplicaton.class)
                .web(WebApplicationType.NONE)
                .run(args);

        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);

        context.close();
    }
}
