package com.quartz.quartz.job;

import com.quartz.quartz.bean.User;
import com.quartz.quartz.config.ApplicationContextProvider;
import com.quartz.quartz.mapper.UserMapper;
import com.quartz.quartz.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * UserJob
 *
 * @author 45032
 * 2019/8/22 15:02
 **/
@Component
public class UserJob implements BaseJob {
//    @Autowired
//    private UserMapper userMapper;

//    public static UserJob userJob;

//    @PostConstruct
//    public void init() {
//        userJob = this;
//        userJob.userMapper = this.userMapper;
//    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        User user = new User();
        user.setId(1);
        user.setStaust(1);
        UserMapper userMapper = ApplicationContextProvider.getBean(UserMapper.class);
        userMapper.updateById(user);
    }
}
