package org.team.cuc.billingsystem.service;


import org.team.cuc.billingsystem.po.userservice.UserPo;

import java.util.List;

/**
 * @author root
 * 针对表【t_user(用户表)】的数据库操作Service
 * 2022-10-21 20:26:06
 */
public interface UserService {

    /**
     * 查询所有用户信息
     *
     * @return 返回查询到的用户信息
     */
    List<UserPo> listUsers();

    /**
     * 获取用户信息
     *
     * @param token token
     * @return 返回查询到的用户信息
     */
    UserPo getUserInfo(String token);

    /**
     * 通过id主键获取用户信息
     *
     * @param id id主键
     * @return 返回查询到的用户信息
     */
    UserPo getUserById(Integer id);
}
