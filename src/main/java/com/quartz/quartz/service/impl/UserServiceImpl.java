package com.quartz.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quartz.quartz.bean.User;
import com.quartz.quartz.mapper.UserMapper;
import com.quartz.quartz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author 45032
 * 2019/8/22 9:54
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
