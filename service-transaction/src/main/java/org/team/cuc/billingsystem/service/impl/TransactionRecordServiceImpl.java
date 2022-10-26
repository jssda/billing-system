package org.team.cuc.billingsystem.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.bean.bo.RecordBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
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

    @Override
    public TransactionRecordPo saveRecord(TransactionRecordPo recordPo) {
        String code = RandomUtil.randomNumbers(6);
        recordPo.setCode(code);
        recordPo.setCreateTime(DateUtil.date());
        int id = transactionRecordMapper.insertRecordPo(recordPo);
        recordPo.setId(id);
        return recordPo;
    }

    @Override
    public PageVO<TransactionRecordPo> find(RecordBo recordBo) {
        List<TransactionRecordPo> list = transactionRecordMapper.selectByConditionPage(recordBo);
        int count = transactionRecordMapper.count(recordBo);
        return new PageVO<>(recordBo.getPageSize(), recordBo.getPageNum(), count, list);
    }
}




