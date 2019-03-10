package com.springboot.hello.helloboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description: 数据库连接
 * @Slf4j lombak提供的注解，使用slf4j日，CommandLineRunner在spring beans初始化后执行
 * @author: HanZhonghua
 * @create: 2019-02-16 10:16
 */
//@SpringBootApplication
@Slf4j
public class DataSourceDemoApplicationBackup/* implements CommandLineRunner */{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DataSourceDemoApplicationBackup.class, args);
    }


    //@Override
    public void run(String... args) throws Exception {

        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        log.info("开始打印");
        log.info(dataSource.toString());
        Connection connection = dataSource.getConnection();
        log.info(connection.toString());
        connection.close();
    }

    private void showData() {
        jdbcTemplate.queryForList("select * from foo").forEach(row -> log.info(row.toString()));
    }
}
