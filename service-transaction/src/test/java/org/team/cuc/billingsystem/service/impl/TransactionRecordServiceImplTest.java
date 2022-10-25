package org.team.cuc.billingsystem.service.impl;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.team.cuc.billingsystem.ServiceTransactionApplication;
import org.team.cuc.billingsystem.bean.bo.RecordBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.TransactionRecordService;

import javax.annotation.Resource;

/**
 * @author jssd
 */
@Slf4j
@Transactional
@SpringBootTest(classes = ServiceTransactionApplication.class)
class TransactionRecordServiceImplTest {

    @Resource
    private TransactionRecordService recordService;

    @Test
    @Commit
    void saveRecord() {
        for (int i = 0; i < 10; i++) {
            TransactionRecordPo recordPo = new TransactionRecordPo();
            recordPo.setAmount(10.0 * i);
            if (i % 2 == 0) {
                recordPo.setChannel("微信");
            } else {
                recordPo.setChannel("支付宝");
            }
            if (i % 3 == 0) {
                recordPo.setType("收入");
            } else {
                recordPo.setType("支出");
            }
            TransactionRecordPo transactionRecordPo = recordService.saveRecord(recordPo);
            log.info("transactionRecord: {}", transactionRecordPo);
        }
    }

    @Test
    void find() {
        for (int i = 0; i < 3; i++) {
            RecordBo recordBo = new RecordBo();
            if (i == 0) {
                recordBo.setAmount(10.0 * i);
            }
            if (i % 2 == 0) {
                recordBo.setStartTime(DateUtil.parseDate(DateUtil.today()));
                recordBo.setEndTime(DateUtil.tomorrow());
            }
            if (i % 3 == 0) {
                recordBo.setPageSize(2);
                recordBo.setPageNum(2);
            }
            PageVO<TransactionRecordPo> transactionRecordPoPageVO = recordService.find(recordBo);
            log.info("records: {}", transactionRecordPoPageVO);
        }


    }
}