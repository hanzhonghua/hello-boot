package com.springboot.hello.helloboot.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 声明式事物-注解方式
 * 以下有三个方法，都是带事物标签的，第一个正常写入，第二个方法抛异常回滚，第三个方法直接调用第二个方法
 * 前两个方法结果都很明显，第一个执行写入成功，第二个一场回滚，那么第三个呢？是回滚还是写入成功？运行结果成功，为什么呢？
 * Spring的事物是使用AOP原理为我们生成的代理类，操作的是代理类中增强过的方法，而第三个方法是直接调用本来的方法，
 * 所以事物没有起到作用，不会回滚，如何才能成功回滚呢？只需要从Spring中获取实例即可
 * @author: HanZhonghua
 * @create: 2019-03-03 23:07
 */
@Service
public class FooServiceImpl implements FooService {



    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insert() {
        jdbcTemplate.execute("insert into foo (bar) values('eee')");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertThenRollback() {
        jdbcTemplate.execute("insert into foo (bar) values('fff')");
        throw new RuntimeException();
    }

    @Override
    public void invokeInsertThenRollback() {
        // 直接调用不会回滚
        //insertThenRollback();
        fooService.insertThenRollback();
    }

}
