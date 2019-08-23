package com.quartz.quartz.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SchedulerConfig
 *
 * @author 45032
 * 2019/8/22 14:58
 **/
@Configuration
public class SchedulerConfig {
//    /**
//     * 调度器初始化
//     * @return
//     * @throws SchedulerException
//     */
//    @Bean
//    public Scheduler Scheduler() throws SchedulerException {
//        SchedulerFactory s = new StdSchedulerFactory();
//        Scheduler scheduler = s.getScheduler();
//        return scheduler;
//    }

    /**
     * quartz监听器
     * @return
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }


}

