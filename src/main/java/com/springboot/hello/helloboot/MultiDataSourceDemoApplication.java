package com.springboot.hello.helloboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @description:  配置多数据源
 * 有两种方式，1：使用@Primary注解Bean
 *           2：排除springboot自动配置，如下
 * @author: HanZhonghua
 * @create: 2019-02-16 22:19
 */
// 使用默认的Hikari连接池才可以
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@Slf4j
public class MultiDataSourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceDemoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("fooDataSource")
    @Primary
    public DataSource fooDataSource(){

        DataSourceProperties dataSourceProperties = fooDataSourceProperties();
        log.info("fooDataSource:{}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTransactionManager(DataSource fooDataSource) {
        return new DataSourceTransactionManager(fooDataSource);
    }


    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("barDataSource")
    public DataSource barDataSource(){

        DataSourceProperties dataSourceProperties = barDataSourceProperties();
        log.info("barDataSource:{}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTransactionManager(DataSource barDataSource) {
        return new DataSourceTransactionManager(barDataSource);
    }
}
