package com.quartz.quartz.controller;

import com.quartz.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

/**
 * QuartzTest
 *
 * @author 45032
 * 2019/8/23 15:26
 **/
public class QuartzTest {
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "ddlib";
    private static String TRIGGER_GROUP_NAME = "ddlibTrigger";
    public static void main(String[] args) throws Exception {
        //startSchedule();
        //resumeJob();
    }
    /**
     * 开始一个simpleSchedule()调度
     */
    public static void startSchedule() {
        try {
            // 1、创建一个JobDetail实例，指定Quartz
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    // 任务执行类
                    .withIdentity("job1_1", "jGroup1")
                    // 任务名，任务组
                    .build();
            // 2、创建Trigger
            SimpleScheduleBuilder builder = SimpleScheduleBuilder
                    .simpleSchedule()
                    // 设置执行次数
                    .repeatSecondlyForTotalCount(100);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1_1", "tGroup1").startNow()
                    .withSchedule(builder).build();
            // 3、创建Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            // 4、调度执行
            scheduler.scheduleJob(jobDetail, trigger);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库中找到已经存在的job，并重新开户调度
     */
    public static void resumeJob() {
        try {

            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            // ①获取调度器中所有的触发器组
            List<String> triggerGroups = scheduler.getTriggerGroupNames();
            // ②重新恢复在tgroup1组中，名为trigger1_1触发器的运行
            for (int i = 0; i < triggerGroups.size(); i++) {
                List<String> triggers = scheduler.getTriggerGroupNames();
                for (int j = 0; j < triggers.size(); j++) {
                    Trigger tg = scheduler.getTrigger(new TriggerKey(triggers
                            .get(j), triggerGroups.get(i)));
                    // ②-1:根据名称判断
                    if (tg instanceof SimpleTrigger
                            && tg.getDescription().equals("tgroup1.trigger1_1")) {
                        // ②-1:恢复运行
                        scheduler.resumeJob(new JobKey(triggers.get(j),
                                triggerGroups.get(i)));
                    }
                }

            }
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
