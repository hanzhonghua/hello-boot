package com.springboot.hello.helloboot.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * @description: controller
 * @author: HanZhonghua
 * @create: 2019-03-07 22:12
 */
@SpringBootApplication
@Slf4j
@Controller
public class FooController implements CommandLineRunner {

    @Autowired
    private FooService fooService;

    public static void main(String[] args) {
        SpringApplication.run(FooController.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insert();
        fooService.insertThenRollback();
        fooService.invokeInsertThenRollback();
    }
}
