package org.team.cuc.billingsystem.servicesuit.suitMapper;

import org.apache.ibatis.annotations.Mapper;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.po.suitservice.ToolPo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.ToolBo;

import java.util.List;

/**
* @author happyelements
* @description 针对表【t_tool】的数据库操作Mapper
* @createDate 2022-10-25 17:48:07
* @Entity generator.domain.Tool
*/
@Mapper
public interface ToolMapper {

    /**
     * 查询所有组件信息
     *
     * @return 返回查询到的组件信息
     */
    List<ToolPo> selectAllTools();

    /**
     * 存储一个组件信息
     *
     * @param toolPo 组件实体
     * @return 组件信息
     */
    Integer saveOne(ToolPo toolPo);

    /**
     * 更新一个组件信息
     *
     * @param toolPo 套装实体
     * @return 套装信息
     */
    Integer updateById(ToolPo toolPo);

    /**
     * 查询单个组件信息
     *
     * @param ids 套装id
     * @return 套装信息
     */
    List<ToolPo> selectToolByIds(List<Integer> ids);

    /**
     * 查询组件信息
     *
     * @param toolBo toolBo
     * @return 套装信息
     */
    List<ToolPo> selectTool(ToolBo toolBo);

    /**
     * 查询组件信息
     *
     * @return 套装信息
     */
    Integer count(ToolBo toolBo);

}




