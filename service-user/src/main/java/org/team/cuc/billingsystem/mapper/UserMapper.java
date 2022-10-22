package org.team.cuc.billingsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.team.cuc.billingsystem.po.userservice.UserPo;

import java.util.List;

/**
 * @author root
 * <p>
 * 针对表【t_user(用户表)】的数据库操作Mapper
 * 2022-10-21 20:26:06
 * org.team.cuc.billingsystem.po.userservice.UserPo
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return 返回查询到的用户信息
     */
    List<UserPo> selectAllUsers();


    /**
     * 查询用户
     *
     * @param userCode 用户唯一编码
     * @return 用户信息
     */
    UserPo selectUserByUserCode(@Param("userCode") String userCode);

    /**
     * 存储一个用户信息
     *
     * @param userPo 用户实体
     * @return 用户信息
     */
    Integer saveOne(UserPo userPo);

    /**
     * 通过主键查询用户信息
     *
     * @param id 用户id
     * @return 返回查询到的用户信息， 如果没有查询到  返回null
     */
    UserPo selectUserById(@Param("id") int id);
}




