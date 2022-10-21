package org.team.cuc.billingsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
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
}




