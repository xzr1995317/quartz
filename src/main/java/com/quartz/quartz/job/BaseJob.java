package com.quartz.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * BaseJob
 *
 * @author 45032
 * 2019/8/23 16:38
 **/
public interface BaseJob extends Job {
   void execute(JobExecutionContext context) throws JobExecutionException;

}
