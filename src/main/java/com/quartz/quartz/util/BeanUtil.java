package com.quartz.quartz.util;

import com.quartz.quartz.config.ApplicationContextProvider;
import com.quartz.quartz.mapper.UserMapper;

/**
 * BeanUtil
 *
 * @author 45032
 * 2019/8/23 10:28
 **/
public class BeanUtil {
    private static UserMapper userMapper = ApplicationContextProvider.getBean(UserMapper.class);
}
