package org.team.cuc.billingsystem.service;

import org.team.cuc.billingsystem.bean.bo.RecordBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;

import java.util.List;

/**
 * @author root
 */
public interface TransactionRecordService {

    /**
     * 查询所有交易流水信息
     *
     * @return 返回查询到的交易流水信息
     */
    List<TransactionRecordPo> listRecords();

    /**
     * 增加一条数据
     *
     * @param recordPo 增加的交易流水信息
     * @return 返回创建之后携带id的交易流水信息
     */
    TransactionRecordPo saveRecord(TransactionRecordPo recordPo);

    /**
     * 根据条件分页查询
     *
     * @param recordBo 查询到的流水信息
     * @return 返回查询到的流水信息
     */
    PageVO<TransactionRecordPo> find(RecordBo recordBo);
}
