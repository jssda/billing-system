package org.team.cuc.billingsystem.service.impl;

import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.mapper.UserMapper;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.service.UserService;

import java.util.List;

/**
 * @author root
 * 针对表【t_user(用户表)】的数据库操作Service实现
 * 2022-10-21 20:26:06
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserPo> listUsers() {
        return userMapper.selectAllUsers();
    }
}




