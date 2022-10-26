package org.team.cuc.billingsystem.servicesuit.suitMapper;

import org.apache.ibatis.annotations.Mapper;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.po.userservice.UserPo;

import java.util.List;

@Mapper
public interface SuitMapper {

    /**
     * 查询所有套装信息
     *
     * @return 返回查询到的套装信息
     */
    List<SuitPo> selectAllSuits();


    /**
     * 存储一个套装信息
     *
     * @param suitPo 套装实体
     * @return 套装信息
     */
    Integer saveOne(SuitPo suitPo);

    /**
     * 更新一个套装信息
     *
     * @param suitPo 套装实体
     * @return 套装信息
     */
    Integer updateById(SuitPo suitPo);

    /**
     * 查询单个套装信息
     *
     * @param id 套装id
     * @return 套装信息
     */
    SuitPo selectSuitById(Integer id);

    /**
     * 查询套装信息
     *
     * @param id 套装id
     * @return 套装信息
     */
    List<SuitPo> selectSuit(SuitPo suitPo);
}
