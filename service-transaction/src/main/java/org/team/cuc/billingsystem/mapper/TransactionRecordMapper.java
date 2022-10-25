package org.team.cuc.billingsystem.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.team.cuc.billingsystem.bean.bo.RecordBo;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;

import java.util.List;

/**
 * @author root
 */
@Mapper
public interface TransactionRecordMapper {

    /**
     * 查询所有交易流水信息
     *
     * @return 返回查询到的所有交易流水信息
     */
    List<TransactionRecordPo> selectAll();

    /**
     * 增加一条数据
     *
     * @param recordPo 增加的交易流水信息
     * @return 返回增加的id
     */
    int insertRecordPo(TransactionRecordPo recordPo);

    /**
     * 根据条件分页查询
     *
     * @param recordBo 流水信息
     * @return 返回查询到的数据， list
     */
    List<TransactionRecordPo> selectByConditionPage(RecordBo recordBo);

    /**
     * 查询全部数据条数
     *
     * @param recordBo 条件查询信息
     * @return 返回查询到的数据条数
     */
    int count(RecordBo recordBo);
}




