package com.numsource.artproject.util.thread;

import com.numsource.artproject.util.RpcRequestUtil;

public class MyThread extends Thread {
    @Override
    public void run() {
        while (true){
            try {
                RpcRequestUtil.miner_start();
                sleep(5000);//挖矿五秒休眠时间
                RpcRequestUtil.miner_stop();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
