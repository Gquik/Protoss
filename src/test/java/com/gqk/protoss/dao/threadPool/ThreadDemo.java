package com.gqk.protoss.dao.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo {
    public static void main(String[] args) {

    }

    private void fixedThreadPool(){
        //创建指定线程数的线程池
        //ExecutorService es = Executors.newFixedThreadPool(1);
        //创建缓存线程池 60s自动销毁
        //ExecutorService es = Executors.newCachedThreadPool();
        ExecutorService es = Executors.newSingleThreadExecutor();
    }
}
