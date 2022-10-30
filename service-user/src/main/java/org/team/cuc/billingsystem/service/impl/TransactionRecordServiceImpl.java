package org.team.cuc.billingsystem.service.impl;

import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.bean.bo.AjaxResponse;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.TransactionRecordService;

/**
 * @author jssd
 */
@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {
    @Override
    public AjaxResponse<TransactionRecordPo> saveRecord(TransactionRecordPo recordPo) {
        return null;
    }
}
