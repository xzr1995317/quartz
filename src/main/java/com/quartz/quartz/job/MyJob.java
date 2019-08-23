package com.quartz.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MyJob
 *
 * @author 45032
 * 2019/8/23 14:47
 **/
public class MyJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello quzrtz  "+
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
    }
}
