package com.tx.task;

import com.tx.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时任务
 */
@Component
public class MyTask1 {

    @Autowired
    private UserMapper userMapper;
    @Scheduled(cron = "0/3 * * * * ?")
    public void run1(){

        System.out.println("肖凯强是猪吗？");
    }
}
