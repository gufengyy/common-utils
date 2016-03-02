package com.gufengyy.common.util.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

public class CompletedThreadPoolTest extends TestCase{
    
    public void test(){
        CompletedThreadPoolExecutor pool = new CompletedThreadPoolExecutor(5, 5 * 2, 300,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        int[] arr = {1};
        
        pool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    arr[0] = 4;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pool.shutdown();
        try {
            pool.awaitTerminated();
            // synchronized
            assertEquals(4, arr[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
