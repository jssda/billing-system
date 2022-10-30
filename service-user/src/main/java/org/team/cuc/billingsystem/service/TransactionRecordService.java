package org.team.cuc.billingsystem.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.team.cuc.billingsystem.bean.bo.AjaxResponse;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.impl.TransactionRecordServiceImpl;

/**
 * @author jssd
 */
@FeignClient(value = "service-transaction", fallback = TransactionRecordServiceImpl.class)
public interface TransactionRecordService {

    /**
     * 增加一条流水信息
     *
     * @param recordPo 流水信息
     * @return 返回增加的流水信息
     */
    @PostMapping(value = "/transactions")
    AjaxResponse<TransactionRecordPo> saveRecord(@RequestBody TransactionRecordPo recordPo);

}
