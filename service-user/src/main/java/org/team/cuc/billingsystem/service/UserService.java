package org.team.cuc.billingsystem.service;


import org.team.cuc.billingsystem.bean.dto.UserAmountDto;
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

    /**
     * 更新用户信息
     *
     * @param userPo 用户实体
     * @return 返回更新后的用户对象
     */
    UserPo updateUserById(UserPo userPo);

    /**
     * 获取用户余额
     *
     * @param userId 用户id
     * @return 数据传输对象
     */
    UserAmountDto getUserAmountDto(Integer userId);

    /**
     * 更改用户余额接口
     *
     * @param userAmountDto 用户余额传输对象
     */
    void updateAmount(UserAmountDto userAmountDto);

    /**
     * 添加一个用户信息
     *
     * @param userPo 用户实体
     * @return 返回添加后的用户信息
     */
    UserPo saveUser(UserPo userPo);

}
