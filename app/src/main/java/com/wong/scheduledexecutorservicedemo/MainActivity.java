package com.wong.scheduledexecutorservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(5);
        // 延时任务
        mScheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                Log.d("延时任务","ScheduledExecutorService#Hello world");
            }
        },2, TimeUnit.SECONDS);

        // 循环任务 按照上一次任务的发起时间计算下一次任务的开始时间,
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("循环任务", "scheduleAtFixedRate#first:" + System.currentTimeMillis() / 1000);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1,1,TimeUnit.SECONDS);
        // mScheduledExecutorService.scheduleAtFixedRate测试结果
        //2019-08-23 11:39:19.158 6496-6524/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531559
        //2019-08-23 11:39:22.160 6496-6526/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531562
        //2019-08-23 11:39:25.162 6496-6526/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531565
        //2019-08-23 11:39:28.164 6496-6526/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531568
        //2019-08-23 11:39:31.168 6496-6526/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531571
        //2019-08-23 11:39:34.172 6496-6526/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531574
        //2019-08-23 11:39:37.176 6496-6526/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleAtFixedRate#first:1566531577
        // 上一个任务的开始时间 + 延迟时间 = 下一个任务的开始时间。



        // 循环任务，以上一次任务的结束时间计算下一次任务的开始时间
        mScheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Log.d("循环任务", "scheduleWithFixedDelay:" + System.currentTimeMillis() / 1000);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1,1,TimeUnit.SECONDS);
        //2019-08-23 11:53:31.918 11260-11357/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532411
        //2019-08-23 11:53:35.920 11260-11355/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532415
        //2019-08-23 11:53:39.922 11260-11355/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532419
        //2019-08-23 11:53:43.924 11260-11355/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532423
        //2019-08-23 11:53:47.927 11260-11355/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532427
        //2019-08-23 11:53:51.930 11260-11355/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532431
        //2019-08-23 11:53:55.932 11260-11355/com.wong.scheduledexecutorservicedemo D/循环任务: scheduleWithFixedDelay:1566532435
        // 上一次任务的结束时间 + 延迟时间 = 下一次任务的开始时间。

    }
}
