package org.team.cuc.billingsystem.service;

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
}
