package org.team.cuc.billingsystem.mapper;


import org.apache.ibatis.annotations.Mapper;
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
}




