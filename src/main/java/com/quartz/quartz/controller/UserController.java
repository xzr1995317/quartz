package com.quartz.quartz.controller;

import com.quartz.quartz.bean.User;
import com.quartz.quartz.job.BaseJob;
import com.quartz.quartz.job.UserJob;
import com.quartz.quartz.service.UserService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author 45032
 * 2019/8/22 9:53
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Scheduler scheduler;

    @GetMapping("/getUser")
    public User getUser() {
        User user = userService.getById(1);
        return user;
    }

    @GetMapping("/getSimpleTrigger")
    public User get() throws Exception {

        //2.构建job信息
        JobDetail jobDetail = JobBuilder.newJob(UserJob.class).withIdentity("user", "userGroup").build();
        //3.表达式调度构建器
        //CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule()
        //SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        //4.构建trigger
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .build();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
        //1.启动调度器

        User user = userService.getById(1);
        return user;
    }

    @PostMapping(value = "/addjob")
    public void addjob(@RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        addJob(jobClassName, jobGroupName, cronExpression);
    }

    public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    public static BaseJob getClass(String classname) throws Exception
    {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.newInstance();
    }


}
