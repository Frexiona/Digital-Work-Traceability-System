package com.numsource.artproject.service.impl;

import com.numsource.artproject.service.RpcService;
import com.numsource.artproject.util.RpcRequestUtil;
import com.numsource.artproject.util.thread.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RpcServiceImplTest {
    public static String testUserAddress = "0x1ae1cbacaf1a6933eefc05228d8da45126e4124f";

    @Autowired
    private RpcService rpcService;
    @Test
    public void createUser() {
        try {
            Object address = rpcService.createUser("123456");
            log.info("address:{}",address);
//            RpcRequestUtil.miner_start();
//            Thread.sleep(5);
//            RpcRequestUtil.miner_stop();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void uploadProduct() {
        try {
            Object upload = rpcService.uploadProduct("123456","星空",50000,testUserAddress);
            log.info("upload:{}",upload);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void changeProductStatus() {
        try {
            RpcRequestUtil.miner_start();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void transferProduct() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    @Test
    public void bidProduct() {
    }

    @Test
    public void getAllProductCodeList() {
    }

    @Test
    public void getAllProductInfo() {
    }
}