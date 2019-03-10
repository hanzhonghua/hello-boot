package com.springboot.hello.helloboot;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * @description: filter
 * @author: HanZhonghua
 * @create: 2019-02-17 21:11
 */
@Slf4j
public class DruidFilterDemo extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("BEFORE");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("AFTER");
    }
}
