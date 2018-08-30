package com.jkgl.admin.service.impl;

import org.springframework.stereotype.Service;

import com.jkgl.admin.entity.User;
import com.jkgl.admin.mapper.UserMapper;
import com.jkgl.admin.service.UserService;
import com.jkgl.common.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
