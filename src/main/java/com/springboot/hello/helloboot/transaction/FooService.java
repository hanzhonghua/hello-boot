package com.springboot.hello.helloboot.transaction;

/**
 * @description: 声明式事物
 * @author: HanZhonghua
 * @create: 2019-03-03 23:06
 */
public interface FooService {

    void insert();
    void insertThenRollback();
    void invokeInsertThenRollback();
}
