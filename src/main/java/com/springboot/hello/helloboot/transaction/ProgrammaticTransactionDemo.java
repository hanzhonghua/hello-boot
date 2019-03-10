package com.springboot.hello.helloboot.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

/**
 * @description: 编程式事物 TransactionTemplate
 * @author: HanZhonghua
 * @create: 2019-03-03 21:48
 */
@SpringBootApplication
@Slf4j
public class ProgrammaticTransactionDemo implements CommandLineRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammaticTransactionDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("begin transaction:" + getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.execute("insert into foo (id, bar) values(4, 'ddd')");
                log.info("in transaction:" + getCount());
                status.setRollbackOnly();
            }
        });
        log.info("after transaction:" + getCount());

    }

    private int getCount() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from foo ");

        int a = 0;
        for (Map<String, Object> m : maps) {
            a++;
            for (Map.Entry m1 : m.entrySet()){
                //System.out.println(m1);
            }
        }
        return a;
    }
}
