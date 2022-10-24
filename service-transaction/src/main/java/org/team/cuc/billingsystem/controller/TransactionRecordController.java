package org.team.cuc.billingsystem.controller;

import org.springframework.web.bind.annotation.*;
import org.team.cuc.billingsystem.bean.bo.RecordBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.TransactionRecordService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jssd
 */
@RestController
@RequestMapping("/transactions")
public class TransactionRecordController {

    private final TransactionRecordService transactionRecordService;

    public TransactionRecordController(TransactionRecordService transactionRecordService) {
        this.transactionRecordService = transactionRecordService;
    }

    @GetMapping()
    public List<TransactionRecordPo> listRecords() {
        return transactionRecordService.listRecords();
    }

    @PostMapping
    public TransactionRecordPo saveRecord(@RequestBody @NotNull(message = "data is null") TransactionRecordPo recordPo) {
        return transactionRecordService.saveRecord(recordPo);
    }

    @PostMapping("/find")
    public PageVO<TransactionRecordPo> find(@RequestBody @NotNull(message = "data is null") RecordBo recordBo) {
        return transactionRecordService.find(recordBo);
    }
}
