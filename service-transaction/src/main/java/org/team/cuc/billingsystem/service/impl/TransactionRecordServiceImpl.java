package org.team.cuc.billingsystem.service.impl;

import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.mapper.TransactionRecordMapper;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.TransactionRecordService;

import java.util.List;

/**
 * @author root
 */
@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    private final TransactionRecordMapper transactionRecordMapper;

    public TransactionRecordServiceImpl(TransactionRecordMapper transactionRecordMapper) {
        this.transactionRecordMapper = transactionRecordMapper;
    }

    @Override
    public List<TransactionRecordPo> listRecords() {
        return transactionRecordMapper.selectAll();
    }
}




